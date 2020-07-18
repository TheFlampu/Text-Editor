package editor;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Next extends SwingWorker<Matcher, String> {
    JTextArea area;
    JTextField pattern;
    public Next(JTextArea area, JTextField pattern) {
        this.area = area;
        this.pattern = pattern;
    }
    @Override
    protected Matcher doInBackground() {
        if(!TextEditor.lastNext) {
            TextEditor.iterator.next();
            TextEditor.lastNext = true;
        }

        if(!TextEditor.iterator.hasNext()) {
            while (TextEditor.iterator.hasPrevious()) {
                TextEditor.iterator.previous();
            }
        }
        Pattern pattern = Pattern.compile(this.pattern.getText(), TextEditor.useRegular ? Pattern.LITERAL : 0);
        Matcher matcher = pattern.matcher(this.area.getText());

        int i = TextEditor.iterator.next();
        if (matcher.find(i)) {
            return matcher;
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            Matcher matcher = get();
            area.setCaretPosition(matcher.start() + matcher.end() - matcher.start());
            area.select(matcher.start(), matcher.start() + matcher.end() - matcher.start());
            area.grabFocus();
        } catch (Exception ignored) {
        }
    }
}
