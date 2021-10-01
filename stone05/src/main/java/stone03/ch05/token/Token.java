package stone.ch05.token;

import stone.ch05.exception.StoneException;

public abstract class Token {

    public static final Token EOF = new Token(-1) {
    };

    public static final String EOL = "\\n";

    private final int lineNum;

    public Token(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getLineNum() {
        return this.lineNum;
    }

    public boolean isId() {
        return false;
    }

    public boolean isNum() {
        return false;
    }

    public boolean isStr() {
        return false;
    }

    public int getNum() {
        throw new StoneException("not num token");
    }

    public String getStr() {
        return "";
    }

    public boolean isEOF() {
        return this == Token.EOF;
    }

    public boolean nonEOF() {
        return !isEOF();
    }

    public boolean isEOL() {
        return this.isId() && Token.EOL.equals(this.getStr());
    }

    public boolean nonEOL() {
        return !isEOL();
    }

}
