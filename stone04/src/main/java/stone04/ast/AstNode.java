package stone04.ast;

import java.util.Iterator;

public abstract class AstNode implements Iterable<AstNode> {

    public abstract AstNode child(int idx);

    public abstract int childrenNum();

    public abstract Iterator<AstNode> children();

    public Iterator<AstNode> iterator() {
        return children();
    }

    public int lineNum() {
        return -1;
    }

}
