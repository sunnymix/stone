package stone05.token;

public class NumToken extends Token {
    private final int num;

    public NumToken(int lineNum, int num) {
        super(lineNum);
        this.num = num;
    }

    @Override
    public boolean isNum() {
        return true;
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public String getStr() {
        return Integer.toString(this.num);
    }
}
