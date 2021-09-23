package stone.ch04.ast.branch;

import stone.ch04.ast.AstNode;
import stone.ch04.ast.leaf.AstLeaf;

import java.util.List;

public class BinaryExpr extends AstBranch {

    public BinaryExpr(List<AstNode> children) {
        super(children);
    }

    public AstNode left() {
        return this.child(0);
    }

    public String op() {
        return ((AstLeaf) this.child(1)).token().getStr();
    }

    public AstNode right() {
        return this.child(2);
    }

}
