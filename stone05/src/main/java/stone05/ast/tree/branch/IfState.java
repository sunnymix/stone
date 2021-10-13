package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.List;

public class IfState extends Branch {
    public IfState(List<Tree> children) {
        super(children);
    }

    public Tree condition() {
        return child(0);
    }

    public Tree thenBlock() {
        return child(1);
    }

    public Tree elseBlock() {
        return childrenNum() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return String.format("(if %s %s else %s)", condition(), thenBlock(), elseBlock());
    }
}
