package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.List;

public class WhileState extends Branch {
    public WhileState(List<Tree> children) {
        super(children);
    }

    public Tree condition() {
        return child(0);
    }

    public Tree body() {
        return child(1);
    }

    public String toString() {
        return String.format("(while %s %s)", condition(), body());
    }
}
