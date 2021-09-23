package stone.ch04.ast.leaf;

import stone.ch04.token.Token;

public class NumValue extends AstLeaf {

    public NumValue(Token token) {
        super(token);
    }

    public int value() {
        return this.token.getNum();
    }

}
