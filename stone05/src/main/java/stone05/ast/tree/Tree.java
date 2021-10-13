package stone05.ast.tree;

import java.util.Iterator;

public abstract class Tree implements Iterable<Tree> {
    public abstract Tree child(int idx);

    public abstract int childrenNum();

    public abstract Iterator<Tree> children();

    public Iterator<Tree> iterator() {
        return children();
    }

    public int lineNum() {
        return -1;
    }
}
