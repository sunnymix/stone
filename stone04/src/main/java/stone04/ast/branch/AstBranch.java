package stone04.ast.branch;

import stone04.ast.AstNode;

import java.util.Iterator;
import java.util.List;

public class AstBranch extends AstNode {

    private final List<AstNode> children;

    public AstBranch(List<AstNode> children) {
        this.children = children;
    }

    @Override
    public AstNode child(int idx) {
        return this.children.get(idx);
    }

    @Override
    public int childrenNum() {
        return this.children.size();
    }

    @Override
    public Iterator<AstNode> children() {
        return this.children.iterator();
    }

    @Override
    public int lineNum() {
        for (AstNode nod : this.children) {
            int lineNum = nod.lineNum();
            if (lineNum >= 0) {
                return lineNum;
            }
        }
        return -1;
    }

}
