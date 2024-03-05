package com.seatrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyAppGui2  extends JFrame {
        // Constructor to set up the GUI components
        public CompanyAppGui2() {
            setTitle("Company Application");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Panel for input fields and labels
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(3, 2)); // Rows, Columns

            // Labels
            JLabel companyNameLabel = new JLabel("Company Name:");
            JLabel cargoLabel = new JLabel("Cargo:");

            // Text Fields
            JTextField companyNameTextField = new JTextField();
            JTextField cargoTextField = new JTextField();

            // Adding components to inputPanel
            inputPanel.add(companyNameLabel);
            inputPanel.add(companyNameTextField);
            inputPanel.add(cargoLabel);
            inputPanel.add(cargoTextField);

            // Panel for buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            // Buttons
            JButton registerButton = new JButton("Register");
            JButton getInfoButton = new JButton("Get Info");
            JButton exitButton = new JButton("Exit");

            // Adding buttons to buttonPanel
            buttonPanel.add(registerButton);
            buttonPanel.add(getInfoButton);
            buttonPanel.add(exitButton);

            // Panel for lists (harbour and cargo)
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new GridLayout(1, 2)); // Rows, Columns

            // Lists
            JList<String> harbourList = new JList<>();
            JList<String> cargoList = new JList<>();
            listPanel.add(new JScrollPane(harbourList)); // Enables scrolling
            listPanel.add(new JScrollPane(cargoList));

            // Action listeners for buttons
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle registration logic here
                    System.out.println("Registering: " + companyNameTextField.getText());
                }
            });

            getInfoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Fetch and display harbour and cargo information
                    System.out.println("Fetching Info...");
                    // This is where you would fetch data and update the harbourList and cargoList models
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); // Exit the application
                }
            });

            // Adding panels to the frame
            add(inputPanel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.SOUTH);
            add(listPanel, BorderLayout.CENTER);

            // Make the frame visible
            setVisible(true);
        }

        public static void main(String[] args) {
            // Run the GUI construction in the Event-Dispatching thread for thread-safety
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new CompanyAppGUI();
                }
            });
        }
    }


