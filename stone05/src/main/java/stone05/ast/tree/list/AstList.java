package stone05.ast.tree.list;

import stone05.ast.tree.AstTree;

import java.util.Iterator;
import java.util.List;

public class AstList extends AstTree {
    private final List<AstTree> children;

    public AstList(List<AstTree> children) {
        this.children = children;
    }

    @Override
    public AstTree child(int idx) {
        return this.children.get(idx);
    }

    @Override
    public int childrenNum() {
        return this.children.size();
    }

    @Override
    public Iterator<AstTree> children() {
        return this.children.iterator();
    }

    @Override
    public int lineNum() {
        for (AstTree nod : this.children) {
            int lineNum = nod.lineNum();
            if (lineNum >= 0) {
                return lineNum;
            }
        }
        return -1;
    }
}
