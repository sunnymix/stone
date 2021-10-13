package stone05.ast.parser.ele;

import stone05.ast.tree.leaf.Leaf;
import stone05.token.Token;

import java.util.HashSet;

public class IdTokenEle extends TokenEle {
    HashSet<String> reserved;

    public IdTokenEle(Class<? extends Leaf> clazz, HashSet<String> reserved) {
        super(clazz);
        this.reserved = reserved != null ? reserved : new HashSet<>();
    }

    @Override
    public boolean test(Token token) {
        return token.isId() && !reserved.contains(token.getStr());
    }
}
