package stone05.ast.parser;

public class Precedence {
    public final int value;
    public final boolean leftAssoc; // 左结合

    public Precedence(int value, boolean leftAssoc) {
        this.value = value;
        this.leftAssoc = leftAssoc;
    }
}
