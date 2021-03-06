package Yandex;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Arrays;

public class Window extends JFrame {
    String[] names = {"companyName", "company", "cOmpa", "comp", "com", "co", "c", "ax", "axel", "akel", "aKa", "aka47", "aku"};
    String[] names1 = {"abcd", "bcda", "cdab", "dabc"};
    SuggestService suggestService = new SuggestService(Arrays.asList(names));

    public Window() throws HeadlessException {
        super("Suggester");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(2, 20);
        textArea.setEditable(false);

        JTextField textField = new JTextField(5);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textArea.setText(String.valueOf(suggestService.suggest(textField.getText(), 3)));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textArea.setText(String.valueOf(suggestService.suggest(textField.getText(), 3)));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textArea.setText(String.valueOf(suggestService.suggest(textField.getText(), 3)));
            }
        });

        add(textArea);
        add(textField);
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.setVisible(true);
    }
}
