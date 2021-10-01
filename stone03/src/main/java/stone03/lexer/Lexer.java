package stone03.lexer;

import stone03.exception.ParseException;
import stone03.token.IdToken;
import stone03.token.NumToken;
import stone03.token.StrToken;
import stone03.token.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    public static final String PAT = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";

    private final Pattern pat = Pattern.compile(PAT);

    private final ArrayList<Token> que = new ArrayList<>();

    private boolean hasMore;

    private final LineNumberReader reader;

    public Lexer(Reader reader) {
        hasMore = true;
        this.reader = new LineNumberReader(reader);
    }

    public Token read() throws ParseException {
        // FIXME: 读循环难以理解。
        if (_fillQue(0)) {
            return que.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        // FIXME: 这个函数的作用是什么。
        if (_fillQue(i)) {
            return que.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean _fillQue(int i) throws ParseException {
        // FIXME: 循环终止条件比较晦涩。
        while (i >= que.size()) {
            if (hasMore) {
                _readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    private void _readLine() throws ParseException {
        String line;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNum = this.reader.getLineNumber();
        Matcher matcher = pat.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                _addToken(lineNum, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException(String.format("bad token at line %d", lineNum));
            }
        }
        que.add(new IdToken(lineNum, Token.EOL));
    }

    private void _addToken(int lineNum, Matcher matcher) {
        String mat = matcher.group(1);
        if (mat != null) { // not a space
            if (matcher.group(2) == null) { // not a comment
                Token token;
                if (matcher.group(3) != null) { // a num
                    token = new NumToken(lineNum, Integer.parseInt(mat));
                } else if (matcher.group(4) != null) { // a str
                    token = new StrToken(lineNum, _toStrLiteral(mat));
                } else { // a id
                    token = new IdToken(lineNum, mat);
                }
                que.add(token);
            }
        }
    }

    private String _toStrLiteral(String str) {
        StringBuilder bud = new StringBuilder();
        int len = str.length() - 1;
        for (int idx = 1; idx < len; idx++) {
            char cha = str.charAt(idx);
            if (cha == '\\' && idx + 1 < len) {
                char cha2 = str.charAt(idx + 1);
                if (cha2 == '"' || cha2 == '\\') {
                    cha = str.charAt(++idx);
                } else if (cha2 == 'n') {
                    ++idx;
                    cha = '\n';
                }
            }
            bud.append(cha);
        }
        return bud.toString();
    }

}
