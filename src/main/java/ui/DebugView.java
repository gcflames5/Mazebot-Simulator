package ui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;
import java.util.List;

public class DebugView extends JFrame {

    public static List<String> buffer = new ArrayList<String>();
    public static DebugView singleton;

    //UI elements
    JScrollPane scrollPane;
    JTextArea textArea;

    public DebugView(){
        textArea = new JTextArea(30, 30);
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(scrollPane);

        for (String s : buffer)
            write(s);
        singleton = this;
    }

    public void write(String msg){
        textArea.append(msg + "\n");
    }

    public static void d(String msg){
        if (singleton == null)
            buffer.add(msg);
        else
            singleton.write(msg);
    }

}
