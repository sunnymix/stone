package stone05.ast.parser;

import stone05.ast.parser.ele.*;
import stone05.ast.tree.Tree;
import stone05.ast.tree.leaf.Leaf;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Parser {
    protected List<Ele> eles;

    protected Factory factory;

    public static Parser rule(Class<? extends Tree> clazz) throws ParseException {
        return new Parser(clazz);
    }

    public static Parser rule() throws ParseException {
        return rule(null);
    }

    public Parser(Class<? extends Tree> clazz) throws ParseException {
        reset(clazz);
    }

    protected Parser(Parser parser) {
        this.eles = parser.eles;
        this.factory = parser.factory;
    }

    public Parser reset(Class<? extends Tree> clazz) throws ParseException {
        eles = new ArrayList<>();
        factory = Factory.forAstList(clazz);
        return this;
    }

    public Parser reset() {
        eles = new ArrayList<>();
        return this;
    }

    public Tree parse(Lexer lexer) {
        ArrayList<Tree> trees = new ArrayList<>();
        for (Ele ele : eles) ele.parse(lexer, trees);
        return factory.make(trees);
    }

    public boolean match(Lexer lexer) throws ParseException {
        if (eles.isEmpty()) return true;
        else return eles.get(0).match(lexer);
    }

    /* ================ DSL ================ */

    public Parser num(Class<? extends Leaf> clazz) {
        eles.add(new NumTokenEle(clazz));
        return this;
    }

    public Parser num() {
        return num(null);
    }

    public Parser id(Class<? extends Leaf> clazz, HashSet<String> reserved) {
        eles.add(new IdTokenEle(clazz, reserved));
        return this;
    }

    public Parser id(HashSet<String> reserved) {
        return id(null, reserved);
    }

    public Parser str(Class<? extends Leaf> clazz) {
        eles.add(new StrTokenEle(clazz));
        return this;
    }

    public Parser str() {
        return str(null);
    }

    public Parser token(String... pattern) {
        eles.add(new LeafEle(pattern));
        return this;
    }

    public Parser sep(String... pattern) {
        eles.add(new SkipLeafEle(pattern));
        return this;
    }

    public Parser ast(Parser parser) {
        eles.add(new TreeEle(parser));
        return this;
    }

    public Parser or(Parser... parsers) {
        eles.add(new OrTreeEle(parsers));
        return this;
    }

    public Parser maybe(Parser parser) {
        Parser newParser = new Parser(parser);
        newParser.reset();
        eles.add(new OrTreeEle(new Parser[]{parser, newParser}));
        return this;
    }

    public Parser option(Parser parser) {
        eles.add(new RepeatEle(parser, true));
        return this;
    }

    public Parser repeat(Parser parser) {
        eles.add(new RepeatEle(parser, false));
        return this;
    }

    public Parser expr(Class<? extends Tree> clazz, Parser subExpr, Operators operators) {
        eles.add(new ExprEle(clazz, subExpr, operators));
        return this;
    }

    public Parser expr(Parser subExpr, Operators operators) {
        return expr(null, subExpr, operators);
    }

    public Parser insertChoice(Parser parser) {
        Ele ele = eles.get(0);
        if (ele instanceof OrTreeEle) ((OrTreeEle) ele).insert(parser);
        else {
            Parser otherwise = new Parser(this);
            reset(null);
            or(parser, otherwise);
        }
        return this;
    }
}
