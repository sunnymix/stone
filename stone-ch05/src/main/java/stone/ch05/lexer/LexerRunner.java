package stone.ch05.lexer;

import stone.ch05.exception.ParseException;
import stone.ch05.token.Token;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        run();
    }

    public static void run() throws ParseException {
        Lexer lex = new Lexer(new LexerReader());
        for (Token token; (token = lex.read()) != Token.EOF; ) {
            String out = String.format("%s·", token.getStr());
            if (token.isEOL()) {
                out += "\n";
            }
            System.out.print(out);
        }
        System.out.print("\nLexer exit.\n\n");
    }
}
