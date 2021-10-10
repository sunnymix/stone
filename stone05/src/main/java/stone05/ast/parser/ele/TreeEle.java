package stone05.ast.parser.ele;

import stone05.ast.parser.Parser;
import stone05.ast.tree.AstTree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.List;

public class TreeEle extends Ele {
    protected Parser parser;

    TreeEle(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void parse(Lexer lexer, List<AstTree> res) throws ParseException {
        res.add(parser)
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return false;
    }
}
