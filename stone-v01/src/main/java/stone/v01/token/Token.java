package stone.v01.token;

import stone.v01.exception.StoneException;

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
        throw new StoneException("Not num token");
    }

    public String getStr() {
        return "";
    }

}
