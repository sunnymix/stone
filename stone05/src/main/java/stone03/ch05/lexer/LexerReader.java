package stone.ch05.lexer;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;

public class LexerReader extends Reader {

    private String buf = null;

    private int pos = 0;

    @Override
    public int read(char[] cha, int off, int len) throws IOException {
        if (buf == null) {
            String in = _showDlg();
            if (in == null) {
                return -1;
            } else {
                System.out.printf("\n\nLexer read:\n----------\n%s\n----------\n\n", in);
                buf = in + "\n";
                pos = 0;
            }
        }

        int size = 0;
        int length = buf.length();
        while (pos < length && size < len) {
            cha[off + size++] = buf.charAt(pos++);
        }
        if (pos == length) {
            buf = null;
        }
        return size;
    }

    @Override
    public void close() throws IOException {
    }

    private String _showDlg() {
        JTextArea txt = new JTextArea(20, 40);
        JScrollPane pan = new JScrollPane(txt);
        int res = JOptionPane.showOptionDialog(null, pan, "Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, null, null);
        if (res == JOptionPane.OK_OPTION) {
            return txt.getText();
        } else {
            return null;
        }
    }

}
