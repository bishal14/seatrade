package com.seatrade;

import com.seatrade.dao.CompanyDaoImplementation;
import com.seatrade.dao.HarbourDaoImplementation;
import com.seatrade.dao.ShipDaoImplementation;
import com.seatrade.entity.Company;
import com.seatrade.entity.CompanyApp;
import com.seatrade.entity.Harbour;
import com.seatrade.entity.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import  java.util.*;
import java.util.List;

public class ShipAppGUI extends JFrame {
    private JTextArea responseTextArea;

    public ShipAppGUI() throws SQLException {
        // Erstelle das Hauptfenster
        JFrame frame = new JFrame("ShipApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        // Server and Client Labels
        JLabel serverLabel = new JLabel("Server");
        JLabel clientLabel = new JLabel("Client");
        JLabel companyNameLabel = new JLabel("Company Name: ");

        // Server and Client Lists


        // Top panel for labels and lists
        JPanel topPanel = new JPanel();
        topPanel.add(serverLabel);
         topPanel.add(clientLabel);
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
        JPanel comboPanel = updateShipComboBoxes();

        // Add components to frame
        frame.getContentPane().add(comboPanel,BorderLayout.WEST);
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
                JOptionPane.showMessageDialog(null, "cargo is unloaded!");

            }
        });


        moveToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                String harbourName = "Example Harbour"; // Replace this with actual company name retrieval logic, if necessary.

                // if position of harbour and ship is same,
                JOptionPane.showMessageDialog(null, "ship is arrived at " + harbourName);
            }

        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "cargo is loaded" );

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        //create diaglog, where company is echtzeit in list addiert
        //on clicking launch, it should open dialog with company, harbour list and  details for ship
        // after clicking lauch, it should be created on hafen with dir and cost


        //with company, there should be list of ships also available and move to button should reach to harbour
        // if harbour x,y position is equal to ship position,then it is reached
        //loadcargo, can only be done,if it is in hafen. otherweise fehler meldung
        //after cargo is loaded, it should print quittung der erfolgreich ladung
        //unloaded cargo must update balance of company

            launchCompanyHarbourButton.addActionListener(new ActionListener() {
                JComboBox<Company> companyComboBox = new JComboBox<>();
                JComboBox<Ship> shipComboBox = new JComboBox<>();
                CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
                ShipDaoImplementation shipDaoImplementation = new ShipDaoImplementation();
                @Override
                public void actionPerformed(ActionEvent e) {
                    CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
                    try {
                        List<Company> companyList = companyDaoImplementation.listAll();
                        companyComboBox.removeAllItems(); // Entferne vorherige Einträge
                        for (Company company : companyList) {
                            companyComboBox.addItem(company);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace(); // Oder zeige eine Fehlermeldung an
                    }
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
        SwingUtilities.invokeLater(() -> {
            try {
                new ShipAppGUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public JPanel updateShipComboBoxes() {
        JPanel panel = new JPanel();

        JLabel companyList = new JLabel("Company Names");
        JLabel shipList = new JLabel("Ship Names");
        JLabel harbourList = new JLabel("Harbour Names");
        CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
        ShipDaoImplementation shipDaoImplementation = new ShipDaoImplementation();
        HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();

        JComboBox<String> companyComboBox = new JComboBox<>();
        JComboBox<String> shipComboBox = new JComboBox<>();
        JComboBox<String> harbourCombobox = new JComboBox<>();
        List<Company> companies = null;
        Map<String, Integer> companyNameToIdMap = new HashMap<>();

        // Populate companyComboBox
        try {
              companies = companyDaoImplementation.listAll();
            for (Company company : companies) {

                companyComboBox.addItem(company.getName());
                companyNameToIdMap.put(company.getName(),company.getCompanyId());

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider better error handling here
        }
        companies.forEach(company -> System.out.println(company.getName() + " -> " + company.getCompanyId()));

        // Populate shipComboBox
        List<Ship> ships = shipDaoImplementation.listAll();
        for (Ship ship : ships) {
            shipComboBox.addItem(ship.getName());
        }


        List<Harbour> harbours= harbourDaoImplementation.listAll();
        System.out.println("size is ---> "+harbours.size());
        for (Harbour harbour:harbours){
            harbourCombobox.addItem(harbour.getName());
        }


        companyComboBox.addActionListener(e -> {

            shipComboBox.removeAllItems();
            String selectedCompanyName = (String) companyComboBox.getSelectedItem();
            if (selectedCompanyName != null) {
                Integer companyId = companyNameToIdMap.get(selectedCompanyName);
                if (companyId != null) {
                    try {
                        List<Ship> shipsUpdated = shipDaoImplementation.listByCompany(companyId);
                        for (Ship ship : shipsUpdated) {
                            shipComboBox.addItem(ship.getName());
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace(); // Consider better error handling here
                    }
                } else {
                    System.out.println("No mapping found for selected company name: " + selectedCompanyName);
                }
            }
        });
        panel.add(companyList);
        panel.add(companyComboBox);

        panel.add(shipList);
        panel.add(shipComboBox);

        panel.add(harbourList);
        panel.add(harbourCombobox);

        return panel;
    }


    public List<Ship> updateShipComboBox(Company company){
        int id =company.getCompanyId();
        ShipDaoImplementation shipDaoImplementation = new ShipDaoImplementation();
        List<Ship> ships = shipDaoImplementation.getShipsByCompanyId(id);
        return ships;


    }


}
