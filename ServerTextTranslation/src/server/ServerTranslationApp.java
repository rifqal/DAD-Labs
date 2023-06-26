package server;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTranslationApp {

    private JTextArea logTextArea;
    private JLabel requestCountLabel;
    private List<RequestDetails> requestDetailsList;
    private int requestCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerTranslationApp server = new ServerTranslationApp();
            server.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Translation Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);

        requestCountLabel = new JLabel("Request Count: 0");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(requestCountLabel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);

        frame.setVisible(true);

        appendToLog("Waiting for request from client side");

        requestDetailsList = new ArrayList<>();
        requestCount = 0;

        startServer();
    }

    private void startServer() {
        ServerSocket serverSocket = null;

        try {
            int portNo = 4229;
            serverSocket = new ServerSocket(portNo);

            TranslationApp translationApp = new TranslationApp();

            while (true) {
                Socket clientSocket = serverSocket.accept();

                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                String text = dis.readUTF();
                int language = dis.readInt();

                String translatedText = translationApp.translateText(text, language);

                String log = "Text received from client: " + text + " | Translated: " + translatedText;
                appendToLog(log);

                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                dos.writeUTF(translatedText);

                dis.close();
                dos.close();

                clientSocket.close();

                // Keep track of request details and response
                RequestDetails requestDetails = new RequestDetails(text, translatedText);
                requestDetailsList.add(requestDetails);
                requestCount++;

                // Update request count label
                updateRequestCountLabel();
            }
        } catch (IOException e) {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    private void appendToLog(String log) {
        SwingUtilities.invokeLater(() -> {
            logTextArea.append(log + "\n");
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        });
    }

    private void updateRequestCountLabel() {
        SwingUtilities.invokeLater(() -> {
            requestCountLabel.setText("Request Count: " + requestCount);
        });
    }

    private class RequestDetails {
        private String text;
        private String translatedText;

        public RequestDetails(String text, String translatedText) {
            this.text = text;
            this.translatedText = translatedText;
        }

        public String getText() {
            return text;
        }

        public String getTranslatedText() {
            return translatedText;
        }
    }
}
