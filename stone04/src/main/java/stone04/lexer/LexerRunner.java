package stone04.lexer;

import stone04.exception.ParseException;
import stone04.token.Token;

public class LexerRunner {

    public static void main(String[] args) throws ParseException {
        run();
    }

    public static void run() throws ParseException {
        Lexer lex = new Lexer(new LexerReader());
        for (Token tok; (tok = lex.read()) != Token.EOF; ) {
            System.out.printf("%s·", tok.getStr());
        }
    }

}
