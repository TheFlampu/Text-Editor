package editor;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadFile extends SwingWorker<String, String> {
    JTextArea area;

    public LoadFile(JTextArea area) {
        this.area = area;
    }

    @Override
    protected String doInBackground(){

        int returnValue = TextEditor.jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = TextEditor.jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            area.setText(new String(Files.readAllBytes(Paths.get(get()))));
        } catch (Exception ignored) {
            area.setText("");
        }
    }
}
