package stone05.ast.parser;

import stone05.ast.parser.ele.Ele;
import stone05.ast.tree.AstTree;
import stone05.exception.ParseException;
import stone05.lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    protected List<Ele> eles;

    protected

    public AstTree parse(Lexer lexer) throws ParseException {
        ArrayList<AstTree> res = new ArrayList<>();
        for (Ele ele : eles) {
            ele.parse(lexer, res);
        }

    }
}
