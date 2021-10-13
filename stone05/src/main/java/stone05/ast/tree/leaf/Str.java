package stone05.ast.tree.leaf;

import stone05.token.Token;

public class Str extends Leaf {
    public Str(Token token) {
        super(token);
    }

    public String str() {
        return token.getStr();
    }

    @Override
    public String toString() {
        return str();
    }
}
