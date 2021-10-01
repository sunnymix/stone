package stone05.ast.tree.leaf;

import stone05.token.Token;

public class NumValue extends AstLeaf {
    public NumValue(Token token) {
        super(token);
    }

    public int value() {
        return this.token.getNum();
    }
}
