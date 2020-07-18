package editor;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TextEditor extends JFrame {
    public static JFileChooser jfc = new JFileChooser("./");
    public static ListIterator<Integer> iterator;
    public static boolean lastNext = true;
    public static boolean useRegular = true;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Text Editor");
        setVisible(true);

        JPanel controls = new JPanel(new FlowLayout(){{setAlignment(FlowLayout.LEFT);}});

        JTextField search = new JTextField("", 20);
        JButton loadBtn = new JButton(new ImageIcon("open.png"));
        JButton saveBtn = new JButton(new ImageIcon("save.png"));
        JButton findBtn = new JButton(new ImageIcon("search.png"));
        JButton nextBtn = new JButton(new ImageIcon("next.png"));
        JButton previousBtn = new JButton(new ImageIcon("previous.png"));
        JCheckBox checkBox = new JCheckBox("Use regex");

        loadBtn.setPreferredSize(new Dimension(30, 30));
        saveBtn.setPreferredSize(new Dimension(30, 30));
        findBtn.setPreferredSize(new Dimension(30, 30));
        nextBtn.setPreferredSize(new Dimension(30, 30));
        previousBtn.setPreferredSize(new Dimension(30, 30));

        controls.add(loadBtn);
        controls.add(saveBtn);
        controls.add(search);
        controls.add(findBtn);
        controls.add(previousBtn);
        controls.add(nextBtn);
        controls.add(checkBox);

        add(controls, BorderLayout.NORTH);
        add(jfc);

        JTextArea textArea = new JTextArea("");
        JScrollPane scrollableTextArea = new JScrollPane(textArea);

        add(scrollableTextArea, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenuItem fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Open");
        JMenuItem closeMenuItem = new JMenuItem("Close");

        JMenuItem searchMenu = new JMenu("Search");
        JMenuItem startMenuItem = new JMenuItem("Start search");
        JMenuItem previousMenuItem = new JMenuItem("Previous search");
        JMenuItem nextMenuItem = new JMenuItem("Next match");
        JMenuItem useRegularMenuItem = new JMenuItem("Use regular expressions");

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(closeMenuItem);

        searchMenu.add(startMenuItem);
        searchMenu.add(previousMenuItem);
        searchMenu.add(nextMenuItem);
        searchMenu.add(useRegularMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        setJMenuBar(menuBar);

        search.setName("SearchField");
        loadBtn.setName("OpenButton");
        saveBtn.setName("SaveButton");
        textArea.setName("TextArea");
        scrollableTextArea.setName("ScrollPane");
        fileMenu.setName("MenuFile");
        saveMenuItem.setName("MenuSave");
        loadMenuItem.setName("MenuOpen");
        closeMenuItem.setName("MenuExit");
        jfc.setName("FileChooser");
        findBtn.setName("StartSearchButton");
        nextBtn.setName("NextMatchButton");
        previousBtn.setName("PreviousMatchButton");
        searchMenu.setName("MenuSearch");
        startMenuItem.setName("MenuStartSearch");
        previousMenuItem.setName("MenuPreviousMatch");
        nextMenuItem.setName("MenuNextMatch");
        useRegularMenuItem.setName("MenuUseRegExp");
        checkBox.setName("UseRegExCheckbox");

        revalidate();



        saveBtn.addActionListener(actionEvent -> new SaveFile(textArea).execute());

        loadBtn.addActionListener(actionEvent -> new LoadFile(textArea).execute());

        loadMenuItem.addActionListener(actionEvent -> new LoadFile(textArea).execute());

        saveMenuItem.addActionListener(actionEvent -> new SaveFile(textArea).execute());

        findBtn.addActionListener(actionEvent -> new StartSearch(textArea, search).execute());
        startMenuItem.addActionListener(actionEvent -> new StartSearch(textArea, search).execute());

        nextBtn.addActionListener(actionEvent -> new Next(textArea, search).execute());
        nextMenuItem.addActionListener(actionEvent -> new Next(textArea, search).execute());

        previousBtn.addActionListener(actionEvent -> new Previous(textArea, search).execute());
        previousMenuItem.addActionListener(actionEvent -> new Previous(textArea, search).execute());

        checkBox.addActionListener(actionEvent -> useRegular = !useRegular);
        useRegularMenuItem.addActionListener(actionEvent -> {
            useRegular = false;
            checkBox.setSelected(true);
        });
    }
}
