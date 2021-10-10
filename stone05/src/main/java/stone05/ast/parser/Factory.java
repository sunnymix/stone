package stone05.ast.parser;

import stone05.ast.tree.AstTree;
import stone05.ast.tree.list.AstList;
import stone05.exception.ParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public abstract class Factory {
    public static final String CREATE = "create";

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

    public static Factory forAstTree(Class<? extends AstTree> eleClazz, Class<?> argClazz) throws ParseException {
        if (eleClazz == null) return null;
        try {
            final Method createMethod = eleClazz.getMethod(CREATE, argClazz);
            return new Factory() {
                @Override
                protected AstTree doMake(Object arg) throws Exception {
                    return (AstTree) createMethod.invoke(null, arg);
                }
            };
        } catch (NoSuchMethodException e) {
        }
        try {
            final Constructor<? extends AstTree> constructor = eleClazz.getConstructor(argClazz);
            return new Factory() {
                @Override
                protected AstTree doMake(Object arg) throws Exception {
                    return constructor.newInstance(arg);
                }
            };
        } catch (NoSuchMethodException e) {
            throw new ParseException(
                    String.format("class<%s> has no 'create' or 'constructor' method", eleClazz));
        }
    }

    public static Factory forAstList(Class<? extends AstTree> eleClazz) throws ParseException {
        Factory factory = forAstTree(eleClazz, List.class);
        if (factory == null) {
            factory = new Factory() {
                @Override
                protected AstTree doMake(Object arg) throws Exception {
                    List<AstTree> argList = (List<AstTree>) arg;
                    if (argList.size() == 1) return argList.get(0);
                    else return new AstList(argList);
                }
            };
        }
        return factory;
    }
}
