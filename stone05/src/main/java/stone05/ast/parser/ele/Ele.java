package stone05.ast.parser.ele;

import stone05.ast.tree.Tree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.List;

public abstract class Ele {
    public abstract void parse(Lexer lexer, List<Tree> res) throws ParseException;

    public abstract boolean match(Lexer lexer) throws ParseException;
}
