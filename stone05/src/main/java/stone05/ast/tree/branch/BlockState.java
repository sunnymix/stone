package stone05.ast.tree.branch;

import stone05.ast.tree.Tree;

import java.util.List;

public class BlockState extends Branch {
    public BlockState(List<Tree> children) {
        super(children);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{ ");
        if (childrenNum() > 0) {
            s.append(child(0));
            for (int i = 1; i < childrenNum(); i++) {
                s.append("; ").append(child(i));
            }
        }
        s.append(" }");
        return s.toString();
    }
}
