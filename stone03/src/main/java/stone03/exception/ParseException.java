package stone.ch03.exception;

import stone.ch03.token.Token;

import java.io.IOException;

public class ParseException extends Exception {

    public ParseException(IOException e) {
        super(e);
    }

    public ParseException(Token token) {
        this("", token);
    }

    public ParseException(String msg) {
        super(msg);
    }

    public ParseException(String msg, Token token) {
        super(String.format("syntax error around %s. %s", _location(token), msg));
    }

    private static String _location(Token token) {
        if (token == Token.EOF) {
            return "the last line";
        } else {
            return String.format("\"%s\" at line %d", token.getStr(), token.getLineNum());
        }
    }

}
