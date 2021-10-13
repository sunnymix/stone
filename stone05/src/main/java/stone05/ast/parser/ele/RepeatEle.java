package stone05.ast.parser.ele;

import stone05.ast.parser.Parser;
import stone05.ast.tree.Tree;
import stone05.ast.tree.branch.Branch;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.List;

public class RepeatEle extends Ele {
    protected Parser parser;

    protected boolean onlyOnce;

    public RepeatEle(Parser parser, boolean onlyOnce) {
        this.parser = parser;
        this.onlyOnce = onlyOnce;
    }

    @Override
    public void parse(Lexer lexer, List<Tree> res) throws ParseException {
        while (parser.match(lexer)) {
            Tree tree = parser.parse(lexer);
            if (tree.getClass() != Branch.class || tree.childrenNum() > 0) res.add(tree);
            if (onlyOnce) break;
        }
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
