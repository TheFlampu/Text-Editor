package editor;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;


public class SaveFile extends SwingWorker<String, String>{
    JTextArea area;

    public SaveFile(JTextArea area) {
        this.area = area;
    }

    @Override
    protected String doInBackground(){
            int returnValue = TextEditor.jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = TextEditor.jfc.getSelectedFile();
                return selectedFile.getAbsolutePath();
            }
            return null;
        }

    @Override
    protected void done() {
        try (FileWriter writer = new FileWriter(new File(get()))) {
            writer.write(area.getText());
        } catch (Exception ignored) {
        }
    }
}
