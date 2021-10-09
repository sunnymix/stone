package stone05.ast.parser;

import stone05.ast.tree.AstTree;

public abstract class Factory {
    protected abstract AstTree doMake(Object arg) throws Exception;

    public AstTree make(Object arg) {
        try {
            return doMake(arg);
        } catch (IllegalArgumentException e1) {
            throw e1;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Factory forAstTree(Class<? extends AstTree> eleClazz, Class<?> argClazz) {

    }

    public static Factory forAstList(Class<? extends AstTree> eleClazz) {
        if (eleClazz == null) {
            return null;
        }
        try {

        }
    }
}
