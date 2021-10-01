package stone04.ast.leaf;

import stone04.token.Token;

public class NumValue extends AstLeaf {

    public NumValue(Token token) {
        super(token);
    }

    public int value() {
        return this.token.getNum();
    }

}
