package editor;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Previous extends SwingWorker<Matcher, String>{
    JTextArea area;
    JTextField pattern;
    public Previous(JTextArea area, JTextField pattern) {
        this.area = area;
        this.pattern = pattern;
    }
    @Override
    protected Matcher doInBackground() {
        if(TextEditor.lastNext) {
            TextEditor.iterator.previous();
            TextEditor.lastNext = false;
        }

        if(!TextEditor.iterator.hasPrevious()) {
            while (TextEditor.iterator.hasNext()) {
                TextEditor.iterator.next();
            }
        }


        Pattern pattern = Pattern.compile(this.pattern.getText(), TextEditor.useRegular ? Pattern.LITERAL : 0);
        Matcher matcher = pattern.matcher(this.area.getText());

        int i = TextEditor.iterator.previous();
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
