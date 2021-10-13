package stone05.ast.parser;

import stone05.ast.tree.Tree;
import stone05.ast.tree.leaf.Name;
import stone05.ast.tree.leaf.Num;
import stone05.ast.tree.branch.*;
import stone05.lexer.Lexer;
import stone05.token.Token;

import java.util.HashSet;

public class BasicParser {
    HashSet<String> reserved = new HashSet<>();

    Operators operators = new Operators();

    Parser expr0 = Parser.rule();

    Parser primary = Parser.rule(PrimaryExpr.class).or(
            Parser.rule().sep("(").ast(expr0).sep(")"),
            Parser.rule().num(Num.class),
            Parser.rule().id(Name.class, reserved),
            Parser.rule().str()
    );

    Parser factor = Parser.rule().or(
            Parser.rule(NegativeExpr.class).sep("-").ast(primary),
            primary
    );

    Parser expr = expr0.expr(BinaryExpr.class, factor, operators);

    Parser state0 = Parser.rule();

    Parser block = Parser.rule(BlockState.class).sep("{")
            .option(state0)
            .repeat(Parser.rule().sep(";", Token.EOL).option(state0))
            .sep("}");

    Parser simple = Parser.rule(PrimaryExpr.class).ast(expr);

    Parser state = state0.or(
            Parser.rule(IfState.class).sep("if").ast(expr).ast(block).option(Parser.rule().sep("else").ast(block)),
            Parser.rule(WhileState.class).sep("while").ast(expr).ast(block),
            simple
    );

    Parser program = Parser.rule().or(
            state,
            Parser.rule(NullState.class)
    ).sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("{");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public Tree parser(Lexer lexer) {
        return program.parse(lexer);
    }
}
