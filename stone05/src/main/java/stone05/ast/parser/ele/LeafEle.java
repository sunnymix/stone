package stone05.ast.parser.ele;

import stone05.ast.tree.AstTree;
import stone05.ast.tree.leaf.AstLeaf;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;
import stone05.token.Token;

import java.util.List;

public class LeafEle extends Ele {
    protected String[] patterns;

    public LeafEle(String[] patterns) {
        this.patterns = patterns;
    }

    @Override
    public void parse(Lexer lexer, List<AstTree> res) throws ParseException {
        Token token = lexer.read();
        if (token.isId()) {
            for (String pattern : patterns) {
                if (pattern.equals(token.getStr())) {
                    found(res, token);
                    return;
                }
            }
        }
        if (patterns.length > 0) throw new ParseException(patterns[0] + " expected.", token);
        else throw new ParseException(token);
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        Token token = lexer.peek(0);
        if (token.isId()) {
            for (String pattern : patterns) {
                if (pattern.equals(token.getStr())) return true;
            }
        }
        return false;
    }

    protected void found(List<AstTree> res, Token token) {
        res.add(new AstLeaf(token));
    }
}
