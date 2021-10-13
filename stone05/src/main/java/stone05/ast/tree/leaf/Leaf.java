package stone05.ast.tree.leaf;

import stone05.ast.tree.Tree;
import stone05.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class Leaf extends Tree {
    private static final ArrayList<Tree> empty = new ArrayList<>();

    protected Token token;

    public Leaf(Token token) {
        this.token = token;
    }

    public Token token() {
        return this.token;
    }

    @Override
    public Tree child(int idx) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int childrenNum() {
        return 0;
    }

    @Override
    public Iterator<Tree> children() {
        return empty.iterator();
    }

    @Override
    public int lineNum() {
        return this.token.getLineNum();
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
