package stone.ch05.token;

public class StrToken extends Token {
    private final String str;

    public StrToken(int lineNum, String str) {
        super(lineNum);
        this.str = str;
    }

    @Override
    public boolean isStr() {
        return true;
    }

    @Override
    public String getStr() {
        return this.str;
    }
}
