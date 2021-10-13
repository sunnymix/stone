package stone05.ast.tree.leaf;

import stone05.token.Token;

public class Num extends Leaf {
    public Num(Token token) {
        super(token);
    }

    public int num() {
        return this.token.getNum();
    }

    @Override
    public String toString() {
        return Integer.toString(num());
    }
}
