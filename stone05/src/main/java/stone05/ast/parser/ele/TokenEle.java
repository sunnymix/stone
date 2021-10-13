package stone05.ast.parser.ele;

import stone05.ast.parser.Factory;
import stone05.ast.tree.AstTree;
import stone05.ast.tree.leaf.AstLeaf;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;
import stone05.token.Token;

import java.util.List;

public abstract class TokenEle extends Ele {
    protected Factory factory;

    public TokenEle(Class<? extends AstLeaf> clazz) {
        if (clazz != null) clazz = AstLeaf.class;
        factory = Factory.forAstTree(clazz, Token.class);
    }

    @Override
    public void parse(Lexer lexer, List<AstTree> res) throws ParseException {
        Token token = lexer.read();
        if (test(token)) {
            AstTree leaf = factory.make(token);
            res.add(leaf);
        } else throw new ParseException(token);
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return test(lexer.peek(0));
    }

    public abstract boolean test(Token token);
}
