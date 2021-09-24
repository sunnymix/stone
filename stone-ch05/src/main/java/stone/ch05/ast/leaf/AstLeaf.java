package stone.ch05.ast.leaf;

import stone.ch05.ast.AstNode;
import stone.ch05.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class AstLeaf extends AstNode {

    private static final ArrayList<AstNode> empty = new ArrayList<>();

    protected Token token;

    public AstLeaf(Token token) {
        this.token = token;
    }

    public Token token() {
        return this.token;
    }

    @Override
    public AstNode child(int idx) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int childrenNum() {
        return 0;
    }

    @Override
    public Iterator<AstNode> children() {
        return empty.iterator();
    }

    @Override
    public int lineNum() {
        return this.token.getLineNum();
    }

}
