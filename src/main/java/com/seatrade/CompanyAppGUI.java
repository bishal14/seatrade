package com.seatrade;

import com.seatrade.entity.CompanyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class CompanyAppGUI {
    private CompanyApp companyApp;
    private JTextArea responseTextArea;

    public CompanyAppGUI() {
        this.companyApp = companyApp;

        // Erstelle das Hauptfenster
        JFrame frame = new JFrame("CompanyApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Erstelle ein Textfeld für die Befehlseingabe
        JTextField commandTextField = new JTextField();
        commandTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandTextField.getText();
                sendCommand(command);
                commandTextField.setText("");
            }
        });

        // Erstelle einen Button zum Senden des Befehls
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandTextField.getText();
                sendCommand(command);
                commandTextField.setText("");
            }
        });

        // Erstelle ein Panel für die Befehlseingabe
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Enter command: "), BorderLayout.WEST);
        inputPanel.add(commandTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Erstelle ein Textfeld für die Anzeige der Antworten
        responseTextArea = new JTextArea();
        responseTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseTextArea);

        // Füge die Komponenten zum Hauptfenster hinzu
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Zentriere das Fenster auf dem Bildschirm
        frame.setLocationRelativeTo(null);

        // Zeige das Fenster an
        frame.setVisible(true);
    }

    private void sendCommand(String command) {
        companyApp.sendMessage(command);
        String response;
        while ((response = companyApp.receiveMessage()) != null) {
            appendResponse(response);
            if (response.startsWith("endinfo")) {
                break;
            }
        }
    }

    private void appendResponse(String response) {
        responseTextArea.append(response + "\n");
    }

    public static void main(String[] args) throws IOException {
        CompanyApp companyApp = new CompanyApp(new Socket(), "TestCompany");
        CompanyAppGUI companyAppGUI = new CompanyAppGUI();
        companyAppGUI.start();
    }

    private void start() {
        new Thread(companyApp::run).start();
    }
}
