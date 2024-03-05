package com.seatrade;

import com.seatrade.entity.CompanyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ShipAppGUI extends JFrame {
    private JTextArea responseTextArea;

    public ShipAppGUI() {
        // Erstelle das Hauptfenster
        JFrame frame = new JFrame("ShipApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        // Server and Client Labels
        JLabel serverLabel = new JLabel("Server");
        JLabel clientLabel = new JLabel("Client");
        JLabel companyNameLabel = new JLabel("Company Name: ");

        // Server and Client Lists
        String[] serverArray = {"Sea Trade", "CompanyApp"};
        String[] clientArray = {"Sea Trade", "CompanyApp"};
        JList<String> serverList = new JList<>(serverArray);
        JList<String> clientList = new JList<>(clientArray);
        JScrollPane scrollPaneServer = new JScrollPane(serverList);
        JScrollPane scrollPaneClient = new JScrollPane(clientList);

        // Top panel for labels and lists
        JPanel topPanel = new JPanel();
        topPanel.add(serverLabel);
        topPanel.add(scrollPaneServer);
        topPanel.add(clientLabel);
        topPanel.add(scrollPaneClient);
        topPanel.add(companyNameLabel);

        // Command Input Panel
        JTextField commandTextField = new JTextField(20);
        JPanel commandPanel = new JPanel();
        commandPanel.add(new JLabel("Enter command: "));
        commandPanel.add(commandTextField);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2)); // Adjust GridLayout rows, cols as needed
        JButton unloadButton = new JButton("unload");
        JButton moveToButton = new JButton("moveTo");
        JButton loadButton = new JButton("load");
        JButton exitButton = new JButton("exit");
        JButton launchCompanyHarbourButton = new JButton("launchCompanyHarbour");
 
        // Adding action listeners to buttons (you need to implement sendCommand and other methods)
        unloadButton.addActionListener(e -> sendCommand(commandTextField.getText()));

        // Add buttons to panel
        buttonsPanel.add(unloadButton);
        buttonsPanel.add(moveToButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(exitButton);
        buttonsPanel.add(launchCompanyHarbourButton);
 
        // Response Text Area
        responseTextArea = new JTextArea();
        responseTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseTextArea);

        // Add components to frame
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(commandPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(scrollPane, BorderLayout.EAST); // Adjust this if needed

        // Display the window
        frame.pack(); // Adjusts window size to fit components
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);

        unloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        moveToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        launchCompanyHarbourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
    }
    

    private void sendCommand(String command) {
        // Dummy implementation - Replace this with actual functionality
        System.out.println("Command sent: " + command);
        appendResponse("Response for: " + command);
    }

    private void appendResponse(String response) {
        responseTextArea.append(response + "\n");
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> new ShipAppGUI());
    }
}
