package stone05.ast.parser;

import java.util.HashMap;

public class Operators extends HashMap<String, Precedence> {
    protected static boolean LEFT = true, RIGHT = false;

    public void add(String name, int precedence, boolean leftAssoc) {
        put(name, new Precedence(precedence, leftAssoc));
    }
}
