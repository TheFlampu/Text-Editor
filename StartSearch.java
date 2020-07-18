package editor;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartSearch extends SwingWorker<ListIterator<Integer>, String> {
    JTextArea area;
    JTextField pattern;
    public StartSearch(JTextArea area, JTextField pattern) {
        this.area = area;
        this.pattern = pattern;
    }

    @Override
    protected ListIterator<Integer> doInBackground() {
        Pattern pattern = Pattern.compile(this.pattern.getText(), TextEditor.useRegular ? Pattern.LITERAL : 0);
        Matcher matcher = pattern.matcher(this.area.getText());
        List<Integer> resultSearch = new ArrayList<>();

        while (matcher.find()) {
            resultSearch.add(matcher.start());
        }
        return resultSearch.listIterator();
    }

    @Override
    protected void done() {
        try {
            TextEditor.iterator = get();
            TextEditor.lastNext = true;
            new Next(area, pattern).execute();
        } catch (Exception ignored) {
        }
    }
}
