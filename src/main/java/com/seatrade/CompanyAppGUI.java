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
        frame.setSize(700, 700);


        JLabel server = new JLabel("Server");
        JLabel client = new JLabel("Client");
        JLabel companyName =new JLabel("company Name: ");
        String[] serverarray = {"Sea Trade", "CompanyApp"};
        String[] clientarray = {"Sea Trade", "CompanyApp"};

        JList<String> serverList = new JList(serverarray);


        JList<String> clientList = new JList<>(clientarray);

        JScrollPane scrollPaneTopClient = new JScrollPane(clientList);
        JScrollPane scrollPaneServer = new JScrollPane(serverList);
        serverList.setLayoutOrientation(JList.VERTICAL);
        clientList.setLayoutOrientation(JList.VERTICAL);


        JPanel top = new JPanel();
        top.add(server);
        top.add(client);
        top.add(companyName);

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
        JButton getInfoHarbourButton = new JButton("getInfoHarbour");
        JButton register = new JButton("Register");
        JButton newCargo= new JButton("newCargo");
        JButton getInfoCargo = new JButton("getInfoCargo");
        JButton exit = new JButton("exit");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandTextField.getText();
                sendCommand(command);
                commandTextField.setText("");
            }
        });

        getInfoHarbourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // command send??
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add company in company database,
            }
        });
        newCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add cargo in cargo database?
            }
        });

        getInfoCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //print about cargos of all of that company?
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //programm beenden!!!
            }
        });

        // Erstelle ein Panel für die Befehlseingabe
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Enter command: "), BorderLayout.WEST);
        inputPanel.add(commandTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(getInfoHarbourButton,BorderLayout.EAST);
        inputPanel.add(register,BorderLayout.EAST);
        inputPanel.add(newCargo,BorderLayout.EAST);
        inputPanel.add(getInfoCargo,BorderLayout.EAST);
        inputPanel.add(exit,BorderLayout.EAST);

        // Erstelle ein Textfeld für die Anzeige der Antworten
        responseTextArea = new JTextArea();
        responseTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseTextArea);

        // Füge die Komponenten zum Hauptfenster hinzu
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(scrollPaneServer,BorderLayout.CENTER);
        frame.getContentPane().add(scrollPaneTopClient,BorderLayout.CENTER);

        // Zentriere das Fenster auf dem Bildschirm
        frame.setLocationRelativeTo(null);

        // Zeige das Fenster an
        frame.setVisible(true);

        // kann sein, dass visible nicht richtig ist!
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
