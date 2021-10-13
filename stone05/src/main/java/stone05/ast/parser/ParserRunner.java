package stone05.ast.parser;

import stone05.ast.tree.Tree;
import stone05.lexer.Lexer;
import stone05.lexer.LexerReader;
import stone05.token.Token;

public class ParserRunner {
    public static void main(String[] args) {
        Lexer lexer = new Lexer(new LexerReader());
        BasicParser parser = new BasicParser();
        while (lexer.peek(0) != Token.EOF) {
            Tree tree = parser.parse(lexer);
            System.out.println("=> " + tree.toString());
        }
        System.out.print("\nParserRunner exit.\n\n");
    }
}
