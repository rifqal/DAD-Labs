package client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTranslationApp {

    private JFrame frame;
    private JTextArea resultTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientTranslationApp client = new ClientTranslationApp();
            client.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Translation Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(e -> translateButtonClicked());
        panel.add(translateButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    private void translateButtonClicked() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4229);

            String text = chooseText();
            int language = chooseLanguage();

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(text);
            dos.writeInt(language);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String translatedText = dis.readUTF();

            displayTranslationResult(translatedText);

            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int chooseLanguage() {
        String[] options = {"Bahasa Malaysia", "Arabic", "Korean"};
        int choice = JOptionPane.showOptionDialog(frame, "Choose language:", "Language Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return choice + 1; // Language codes start from 1
    }

    private String chooseText() {
        String[] options = {"Good morning", "Good night", "How are you?", "Thank you", "Goodbye", "What's up?"};
        int choice = JOptionPane.showOptionDialog(frame, "Choose text to translate:", "Text Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return options[choice];
    }

    private void displayTranslationResult(String translatedText) {
        resultTextArea.append("Translated text: " + translatedText + "\n");
    }
}
