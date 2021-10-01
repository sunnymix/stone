package stone.ch04.ast.leaf;

import stone.ch04.token.Token;

public class Name extends AstLeaf {

    public Name(Token token) {
        super(token);
    }

    public String name() {
        return this.token.getStr();
    }

}
