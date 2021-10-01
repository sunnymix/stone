package stone.ch05.ast.tree;

import java.util.Iterator;

public abstract class AstTree implements Iterable<AstTree> {

    public abstract AstTree child(int idx);

    public abstract int childrenNum();

    public abstract Iterator<AstTree> children();

    public Iterator<AstTree> iterator() {
        return children();
    }

    public int lineNum() {
        return -1;
    }

}
