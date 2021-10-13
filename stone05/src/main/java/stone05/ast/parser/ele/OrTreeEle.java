package stone05.ast.parser.ele;

import stone05.ast.parser.Parser;
import stone05.ast.tree.AstTree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.List;

public class OrTreeEle extends Ele {
    protected Parser[] parsers;

    public OrTreeEle(Parser[] parsers) {
        this.parsers = parsers;
    }

    @Override
    public void parse(Lexer lexer, List<AstTree> res) throws ParseException {
        Parser parser = _choose(lexer);
        if (parser == null) throw new ParseException(lexer.peek(0));
        else res.add(parser.parse(lexer));
    }

    @Override
    public boolean match(Lexer lexer) throws ParseException {
        return _choose(lexer) != null;
    }

    public void insert(Parser parser) {
        Parser[] newParsers = new Parser[parsers.length + 1];
        newParsers[0] = parser;
        System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
        parsers = newParsers;
    }

    private Parser _choose(Lexer lexer) {
        for (Parser parser : parsers) {
            if (parser.match(lexer)) return parser;
        }
        return null;
    }
}
