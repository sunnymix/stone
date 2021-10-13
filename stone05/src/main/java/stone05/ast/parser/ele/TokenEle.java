package stone05.ast.parser.ele;

import stone05.ast.tree.Tree;
import stone05.ast.tree.TreeFactory;
import stone05.ast.tree.leaf.Leaf;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;
import stone05.token.Token;

import java.util.List;

public abstract class TokenEle extends Ele {
    protected TreeFactory treeFactory;

    public TokenEle(Class<? extends Leaf> clazz) {
        if (clazz != null) clazz = Leaf.class;
        treeFactory = TreeFactory.forTree(clazz, Token.class);
    }

    @Override
    public void parse(Lexer lexer, List<Tree> res) throws ParseException {
        Token token = lexer.read();
        if (test(token)) {
            Tree leaf = treeFactory.make(token);
            res.add(leaf);
        } else throw new ParseException(token);
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return test(lexer.peek(0));
    }

    public abstract boolean test(Token token);
}
