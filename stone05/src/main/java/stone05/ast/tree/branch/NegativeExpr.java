package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;
import stone05.ast.tree.leaf.Leaf;
import stone05.token.Token;

public class NegativeExpr extends Leaf {
    public NegativeExpr(Token token) {
        super(token);
    }

    public Tree operand() {
        return child(0);
    }

    public String toString() {
        return "-" + operand();
    }
}
