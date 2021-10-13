package stone05.ast.parser.ele;

import stone05.ast.tree.leaf.Leaf;
import stone05.token.Token;

public class StrTokenEle extends TokenEle {
    public StrTokenEle(Class<? extends Leaf> clazz) {
        super(clazz);
    }

    @Override
    public boolean test(Token token) {
        return token.isStr();
    }
}
