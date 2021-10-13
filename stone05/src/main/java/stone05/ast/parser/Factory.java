package stone05.ast.parser;

import stone05.ast.tree.Tree;
import stone05.ast.tree.branch.Branch;
import stone05.exception.ParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public abstract class Factory {
    public static final String CREATE = "create";

    protected abstract Tree doMake(Object arg) throws Exception;

    public Tree make(Object arg) {
        try {
            return doMake(arg);
        } catch (IllegalArgumentException e1) {
            throw e1;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Factory forAstTree(Class<? extends Tree> eleClazz, Class<?> argClazz) throws ParseException {
        if (eleClazz == null) return null;
        try {
            final Method createMethod = eleClazz.getMethod(CREATE, argClazz);
            return new Factory() {
                @Override
                protected Tree doMake(Object arg) throws Exception {
                    return (Tree) createMethod.invoke(null, arg);
                }
            };
        } catch (NoSuchMethodException e) {
        }
        try {
            final Constructor<? extends Tree> constructor = eleClazz.getConstructor(argClazz);
            return new Factory() {
                @Override
                protected Tree doMake(Object arg) throws Exception {
                    return constructor.newInstance(arg);
                }
            };
        } catch (NoSuchMethodException e) {
            throw new ParseException(
                    String.format("class<%s> has no 'create' or 'constructor' method", eleClazz));
        }
    }

    public static Factory forAstList(Class<? extends Tree> eleClazz) throws ParseException {
        Factory factory = forAstTree(eleClazz, List.class);
        if (factory == null) {
            factory = new Factory() {
                @Override
                protected Tree doMake(Object arg) throws Exception {
                    List<Tree> argList = (List<Tree>) arg;
                    if (argList.size() == 1) return argList.get(0);
                    else return new Branch(argList);
                }
            };
        }
        return factory;
    }
}
