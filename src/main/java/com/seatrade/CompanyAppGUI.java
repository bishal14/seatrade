package com.seatrade;

import com.seatrade.dao.CompanyDaoImplementation;
import com.seatrade.dao.HarbourDaoImplementation;
import com.seatrade.entity.Company;
import com.seatrade.entity.CompanyApp;
import com.seatrade.entity.Harbour;
import com.seatrade.util.database.DatabaseUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
public class CompanyAppGUI {
    private CompanyApp companyApp;
    private JTextArea responseTextArea;

    public CompanyAppGUI() {
        JFrame frame = new JFrame("CompanyApp");
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

        JLabel companyName = new JLabel();
        JTextField textField = new JTextField(30);
        JPanel companyNamePannel = new JPanel();
        companyNamePannel.add(companyName);
        commandPanel.add(textField);



        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2)); // Adjust GridLayout rows, cols as needed
        JButton sendButton = new JButton("Send");
        JButton getInfoHarbourButton = new JButton("getInfoHarbour");
        JButton registerButton = new JButton("Register");
        JButton newCargoButton = new JButton("newCargo");
        JButton getInfoCargoButton = new JButton("getInfoCargo");
        JButton exitButton = new JButton("exit");

        // Adding action listeners to buttons (you need to implement sendCommand and other methods)
        sendButton.addActionListener(e -> sendCommand(commandTextField.getText()));

        // Add buttons to panel
        buttonsPanel.add(sendButton);
        buttonsPanel.add(getInfoHarbourButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(newCargoButton);
        buttonsPanel.add(getInfoCargoButton);
        buttonsPanel.add(exitButton);

        // Response Text Area
        responseTextArea = new JTextArea();
        responseTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseTextArea);

        // Add components to frame
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
       //frame.getContentPane().add(companyNamePannel,BorderLayout.PAGE_START);
        frame.getContentPane().add(commandPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(scrollPane, BorderLayout.EAST); // Adjust this if needed

        // Display the window
        frame.pack(); // Adjusts window size to fit components
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);

        // Erstelle einen Button zum Senden des Befehls

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

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = "Example Company"; // Replace this with actual company name retrieval logic, if necessary.
                Company company = new Company(companyName);
                 // This line displays the dialog.
                /*
                    Harbour harbour1 = new Harbour(25, 9, "NONE", "lissabon");
                    Harbour harbour2 = new Harbour(24, 16, "NONE", "dakar");
                    Harbour harbour3 = new Harbour(29, 13, "NONE", "algier");
                    Harbour harbour4 = new Harbour(29, 18, "NONE", "cotonau");
                    Harbour harbour5 = new Harbour(2, 3, "NONE", "halifax");
                    Harbour harbour6 = new Harbour(29, 0, "NONE", "plymouth");
                    Harbour harbour7 = new Harbour(28, 5, "NONE", "brest");
                    Harbour harbour8 = new Harbour(0, 10, "NONE", "ney york");
                    Harbour harbour9 = new Harbour(2, 18, "NONE", "carracas");

                    HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();
                    harbourDaoImplementation.add(harbour1);
                    harbourDaoImplementation.add(harbour2);
                    harbourDaoImplementation.add(harbour3);
                    harbourDaoImplementation.add(harbour4);
                    harbourDaoImplementation.add(harbour5);
                    harbourDaoImplementation.add(harbour6);
                    harbourDaoImplementation.add(harbour7);
                    harbourDaoImplementation.add(harbour8);
                    harbourDaoImplementation.add(harbour9);
                    JOptionPane.showMessageDialog(null, "Company with name " + companyName + " with default size, width, height, and deposit is registered.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                */
                showHarbourList();
            }

        });
        newCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add cargo in cargo database?
            }
        });

        getInfoCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //print about cargos of all of that company?
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //programm beenden!!!
            }
        });




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
     //   CompanyApp companyApp = new CompanyApp(new Socket(), "TestCompany");
       // CompanyAppGUI companyAppGUI = new CompanyAppGUI();
        //companyAppGUI.start();
        SwingUtilities.invokeLater(() -> new CompanyAppGUI());


    }


    public void showCargosList(){

    }


    public void showHarbourList(){
        HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();
        List<Harbour> harbours = harbourDaoImplementation.listAll();
        System.out.println("size of harbours is "+harbours.size());

        String[] columnNames = {"Harbour ID", "X Position", "Y Position", "Direction", "Name"};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate the table model with harbour data
        for (Harbour harbour : harbours) {
            model.addRow(new Object[]{harbour.getHarbourId(), harbour.getxPosition(), harbour.getyPosition(), harbour.getDirection(), harbour.getName()});
        }

        // Create the table and add it to a scroll pane
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Display the table within a JOptionPane dialog
        JOptionPane.showMessageDialog(null, scrollPane, "List of Harbours", JOptionPane.INFORMATION_MESSAGE);
    }
}
