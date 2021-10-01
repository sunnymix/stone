package stone05.ast.tree.leaf;

import stone05.ast.tree.AstTree;
import stone05.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class AstLeaf extends AstTree {
    private static final ArrayList<AstTree> empty = new ArrayList<>();

    protected Token token;

    public AstLeaf(Token token) {
        this.token = token;
    }

    public Token token() {
        return this.token;
    }

    @Override
    public AstTree child(int idx) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int childrenNum() {
        return 0;
    }

    @Override
    public Iterator<AstTree> children() {
        return empty.iterator();
    }

    @Override
    public int lineNum() {
        return this.token.getLineNum();
    }
}
