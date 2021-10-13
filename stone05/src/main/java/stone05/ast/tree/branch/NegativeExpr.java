package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.List;

public class NegativeExpr extends Branch {
    public NegativeExpr(List<Tree> children) {
        super(children);
    }

    public Tree operand() {
        return child(0);
    }

    public String toString() {
        return "-" + operand();
    }
}
