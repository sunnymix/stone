package stone04.ast.leaf;

import stone04.token.Token;

public class Name extends AstLeaf {

    public Name(Token token) {
        super(token);
    }

    public String name() {
        return this.token.getStr();
    }

}
