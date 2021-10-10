package stone05.ast.parser;

import stone05.ast.parser.ele.Ele;
import stone05.ast.tree.AstTree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    protected List<Ele> eles;

    protected Factory factory;

    public Parser(Class<? extends AstTree> clazz) throws ParseException {
        reset(clazz);
    }

    protected Parser(Parser parser) {
        this.eles = parser.eles;
        this.factory = parser.factory;
    }

    public Parser reset(Class<? extends AstTree> clazz) throws ParseException {
        eles = new ArrayList<>();
        factory = Factory.forAstList(clazz);
        return this;
    }

    public Parser reset() {
        eles = new ArrayList<>();
        return this;
    }

    public AstTree parse(Lexer lexer) throws ParseException {
        ArrayList<AstTree> trees = new ArrayList<>();
        for (Ele ele : eles) ele.parse(lexer, trees);
        return factory.make(trees);
    }

    protected boolean match(Lexer lexer) throws ParseException {
        if (eles.isEmpty()) return true;
        else return eles.get(0).match(lexer);
    }

    public static Parser rule(Class<? extends AstTree> clazz) throws ParseException {
        return new Parser(clazz);
    }

    public static Parser rule() throws ParseException {
        return rule(null);
    }
}
