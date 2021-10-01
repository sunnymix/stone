package stone.ch05.ast.tree.leaf;

import stone.ch05.token.Token;

public class Name extends AstLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return this.token.getStr();
    }
}
