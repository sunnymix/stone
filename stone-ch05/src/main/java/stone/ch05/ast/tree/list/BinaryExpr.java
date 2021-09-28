package stone.ch05.ast.tree.list;

import stone.ch05.ast.tree.AstTree;
import stone.ch05.ast.tree.leaf.AstLeaf;

import java.util.List;

public class BinaryExpr extends AstList {

    public BinaryExpr(List<AstTree> children) {
        super(children);
    }

    public AstTree left() {
        return this.child(0);
    }

    public String op() {
        return ((AstLeaf) this.child(1)).token().getStr();
    }

    public AstTree right() {
        return this.child(2);
    }

}
