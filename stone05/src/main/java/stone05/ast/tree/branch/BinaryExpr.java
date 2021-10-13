package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;
import stone05.ast.tree.leaf.Leaf;

import java.util.List;

public class BinaryExpr extends Branch {
    public BinaryExpr(List<Tree> children) {
        super(children);
    }

    public Tree left() {
        return this.child(0);
    }

    public String op() {
        return ((Leaf) this.child(1)).token().getStr();
    }

    public Tree right() {
        return this.child(2);
    }
}
