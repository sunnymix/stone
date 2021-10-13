package stone05.ast.parser.ele;

import stone05.ast.tree.AstTree;
import stone05.token.Token;

import java.util.List;

public class SkipLeafEle extends LeafEle {
    public SkipLeafEle(String[] pat) {
        super(pat);
    }

    @Override
    protected void found(List<AstTree> res, Token token) {
    }
}
