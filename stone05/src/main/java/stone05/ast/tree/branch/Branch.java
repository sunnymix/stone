package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.Iterator;
import java.util.List;

public class Branch extends Tree {
    private final List<Tree> children;

    public Branch(List<Tree> children) {
        this.children = children;
    }

    @Override
    public Tree child(int idx) {
        return this.children.get(idx);
    }

    @Override
    public int childrenNum() {
        return this.children.size();
    }

    @Override
    public Iterator<Tree> children() {
        return this.children.iterator();
    }

    @Override
    public int lineNum() {
        for (Tree nod : this.children) {
            int lineNum = nod.lineNum();
            if (lineNum >= 0) {
                return lineNum;
            }
        }
        return -1;
    }
}
