package stone05.ast.parser.ele;

import stone05.ast.parser.Factory;
import stone05.ast.parser.Operators;
import stone05.ast.parser.Parser;
import stone05.ast.parser.Precedence;
import stone05.ast.tree.AstTree;
import stone05.ast.tree.leaf.AstLeaf;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;
import stone05.token.Token;

import java.util.ArrayList;
import java.util.List;

public class ExprEle extends Ele {
    protected Factory factory;
    protected Parser factor;
    protected Operators ops;

    public ExprEle(Class<? extends AstTree> clazz, Parser factor, Operators ops) {
        this.factory = Factory.forAstList(clazz);
        this.factor = factor;
        this.ops = ops;
    }

    @Override
    public void parse(Lexer lexer, List<AstTree> res) throws ParseException {
        AstTree right = factor.parse(lexer);
        Precedence precedence = null;
        while ((precedence = _nextOperator(lexer)) != null) right = _doShift(lexer, right, precedence.value);
        res.add(right);
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return factor.match(lexer);
    }

    private Precedence _nextOperator(Lexer lexer) {
        Token token = lexer.peek(0);
        if (token.isId()) return ops.get(token.getStr());
        else return null;
    }

    private static boolean _rightIsExpr(int precedence, Precedence nextPrecedence) {
        if (nextPrecedence.leftAssoc) return precedence < nextPrecedence.value;
        else return precedence <= nextPrecedence.value;
    }

    private AstTree _doShift(Lexer lexer, AstTree left, int precedence) {
        ArrayList<AstTree> trees = new ArrayList<>();
        trees.add(left);
        trees.add(new AstLeaf(lexer.read()));
        AstTree right = factor.parse(lexer);
        Precedence nextPrecedence;
        while ((nextPrecedence = _nextOperator(lexer)) != null && _rightIsExpr(precedence, nextPrecedence)) {
            right = _doShift(lexer, right, nextPrecedence.value);
        }
        trees.add(right);
        return factory.make(trees);
    }
}