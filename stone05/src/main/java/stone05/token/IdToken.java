package stone05.token;

public class IdToken extends Token {
    private final String id;

    public IdToken(int lineNum, String id) {
        super(lineNum);
        this.id = id;
    }

    @Override
    public boolean isId() {
        return true;
    }

    @Override
    public String getStr() {
        return this.id;
    }
}
