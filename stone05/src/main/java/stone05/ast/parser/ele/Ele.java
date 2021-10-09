package stone05.ast.parser.ele;

import stone05.ast.tree.AstTree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.List;

public abstract class Ele {
    public abstract void parse(Lexer lexer, List<AstTree> res) throws ParseException;

    public abstract boolean match(Lexer lexer) throws ParseException;
}
