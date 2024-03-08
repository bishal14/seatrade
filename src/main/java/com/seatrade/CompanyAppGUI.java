package com.seatrade;

import com.seatrade.dao.CargoDaoImplementation;
import com.seatrade.dao.CompanyDaoImplementation;
import com.seatrade.dao.HarbourDaoImplementation;
import com.seatrade.entity.Cargo;
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
                showHarbourList();
             }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = "Example Company"; // Replace this with actual company name retrieval logic, if necessary.
                Company company = new Company(companyName);
                 // This line displays the dialog.

                JFrame frame = new JFrame("Company Register");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 400);

                // Server and Client Labels
                JLabel name = new JLabel("Company Name: ");
                JTextField companyNameTextfield = new JTextField(30);
                JButton registerButton = new JButton("Register");


                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                // Füge einige Komponenten hinzu
                panel.add( name);
                panel.add(Box.createRigidArea(new Dimension(5, 0))); // Füge einen Abstand zwischen den Buttons hinzu
                panel.add( companyNameTextfield);
                panel.add(Box.createHorizontalGlue()); // Füge elastischen Platz hinzu
                panel.add(registerButton);

                // Füge das Panel zum Fenster hinzu und zeige es an
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null); // Zentriere das Fenster
                frame.setVisible(true);

                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = companyNameTextfield.getText().toString();
                        Company newCompany = new Company(companyName);


                        CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
                        companyDaoImplementation.add(newCompany);

                        JOptionPane.showMessageDialog(null, "Cargo with name " + companyName + " with default size, width, height, and deposit is registered.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();


                    }
                });


            }

        });
        newCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add cargo in cargo database?
                showHarbourList();
            }
        });

        getInfoCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //print about cargos of all of that company?
                showCargoList();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //programm beenden!!!
                //TO-DO socket close and program stop.
                frame.dispose();

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

    public void showCargoList(){
        CargoDaoImplementation cargoDaoImplementation = new CargoDaoImplementation();
        List<Cargo> cargos = cargoDaoImplementation.listAll();

        System.out.println("size of cargos ist "+ cargos.size());

        String [] columnNames= {"sourceHarbour","destinationHarbour","value","cargoId"};

        DefaultTableModel model = new DefaultTableModel(columnNames,0);

        for(Cargo cargo:cargos){
            model.addRow(new Object[]{cargo.getSourceHarbour(),cargo.getDestinationHarbour(),cargo.getValue(),cargo.getCargoId()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null,scrollPane,"List of cargos",JOptionPane.INFORMATION_MESSAGE);
    }

    //ship app, sea trade
    //lauch, then ship added with company at company position??
    //movetoharobur
    //unload cargo, company balance erhöht und loss aktualisieren!

}
