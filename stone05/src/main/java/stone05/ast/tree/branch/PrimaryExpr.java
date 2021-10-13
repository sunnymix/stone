package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.List;

public class PrimaryExpr extends Branch {
    public PrimaryExpr(List<Tree> children) {
        super(children);
    }

    public static Tree create(List<Tree> children) {
        return children.size() == 1 ? children.get(0) : new PrimaryExpr(children);
    }
}
