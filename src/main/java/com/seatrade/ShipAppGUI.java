package com.seatrade;

import com.seatrade.dao.CargoDaoImplementation;
import com.seatrade.dao.CompanyDaoImplementation;
import com.seatrade.dao.HarbourDaoImplementation;
import com.seatrade.dao.ShipDaoImplementation;
import com.seatrade.entity.Cargo;
import com.seatrade.entity.Company;
import com.seatrade.entity.Harbour;
import com.seatrade.entity.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

        JLabel shipNameLabel = new JLabel("Ship Name: ");
        TextField shipNameTextfield = new TextField(20);

        // Server and Client Lists


        // Top panel for labels and lists
        JPanel topPanel = new JPanel();

        topPanel.add(shipNameLabel);
        topPanel.add(shipNameTextfield);

        // Command Input Panel


        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2)); // Adjust GridLayout rows, cols as needed
        JButton unloadButton = new JButton("unload");
        JButton moveToButton = new JButton("moveTo");
        JButton loadButton = new JButton("load");
        JButton exitButton = new JButton("exit");
        JButton launchCompanyHarbourButton = new JButton("launchCompanyHarbour");

        // Adding action listeners to buttons (you need to implement sendCommand and other methods)

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

        JPanel panel = new JPanel();

        JLabel companyList = new JLabel("Server Company Names");
        JLabel shipList = new JLabel("Client Ship Names");
        JLabel harbourList = new JLabel("Harbour Names");
        JLabel cargoList = new JLabel("Cargos");
        JLabel moveToHarbour = new JLabel("move To Harbour: ");
        CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
        ShipDaoImplementation shipDaoImplementation = new ShipDaoImplementation();
        HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();
        CargoDaoImplementation cargoDaoImplementation = new CargoDaoImplementation();

        JComboBox<String> companyComboBox = new JComboBox<>();
        JComboBox<String> shipComboBox = new JComboBox<>();
        JComboBox<String> harbourCombobox = new JComboBox<>();
        JComboBox<Integer> cargoComboBox = new JComboBox<>();
        JComboBox<String> moveToHarbourComboBox = new JComboBox<>();


        List<Company> companies = companyDaoImplementation.listAll();


        Map<String, Integer> companyNameToIdMap = new HashMap<>();
        Map<String, Integer> harbourNameToIdMap = new HashMap<>();


        // Populate companyComboBox
        for (Company company : companies) {

            companyComboBox.addItem(company.getName());
            companyNameToIdMap.put(company.getName(), company.getCompanyId());

        }


         companies.forEach(company -> System.out.println(company.getName() + " -> " + company.getCompanyId()));

        // Populate shipComboBox
        List<Ship> ships = shipDaoImplementation.listAll();
        for (Ship ship : ships) {
            shipComboBox.addItem(ship.getName());
        }


        List<Harbour> harbours = harbourDaoImplementation.listAll();
        for (Harbour harbour : harbours) {
            harbourCombobox.addItem(harbour.getName());
            harbourNameToIdMap.put(harbour.getName(),harbour.getId());
            moveToHarbourComboBox.addItem(harbour.getName());
        }

        List<Cargo> cargos = cargoDaoImplementation.listAll();
        for(Cargo cargo:cargos){
            cargoComboBox.addItem(cargo.getCargoId());
         }

        companyComboBox.addActionListener(e -> {

            shipComboBox.removeAllItems();
            String selectedCompanyName =  companyComboBox.getSelectedItem().toString();
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


        harbourCombobox.addActionListener(e -> {
            cargoComboBox.removeAllItems();
            String selectedHarbourName= harbourCombobox.getSelectedItem().toString();
             if(selectedHarbourName!=null){
                 System.out.println();

                Integer harbourId = harbourNameToIdMap.get(selectedHarbourName);
                int id = (int )harbourId;
                 if(harbourId != null){
                    try {

                        List<Cargo> cargoUpdated =   cargoDaoImplementation.listByHarnbourNameS(id);
                        System.out.println("cargo size  is "+cargoUpdated.size());

                        for(Cargo cargo:cargoUpdated) {
                        cargoComboBox.addItem(cargo.getCargoId());
                        System.out.println("cargoid is "+cargo.getCargoId());

                    } } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                }else{
              JOptionPane.showMessageDialog(null,"No Matching found for selected Harbour Name: "+ selectedHarbourName);
                }
            }
        });
        panel.add(companyList);
        panel.add(companyComboBox);

        panel.add(shipList);
        panel.add(shipComboBox);

        panel.add(harbourList);
        panel.add(harbourCombobox);

        panel.add(cargoList);
        panel.add(cargoComboBox);

        panel.add(moveToHarbour);
        panel.add(moveToHarbourComboBox);

        // Add components to frame
        frame.getContentPane().add(panel, BorderLayout.WEST);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(scrollPane, BorderLayout.EAST); // Adjust this if needed

        // Display the window
        frame.pack(); // Adjusts window size to fit components
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);


        launchCompanyHarbourButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String direction ="NONE";
                    double cost =300;
                    String companySelectedName= companyComboBox.getSelectedItem().toString();
                    Company companySelected = iterateListCompanies(companies,companySelectedName);
                    int companyId = companySelected.getCompanyId();
                    System.out.println("company selected name is --->"+companySelectedName);

                    String SelectedName= harbourCombobox.getSelectedItem().toString();
                    Harbour harbourSelected = iterateHarbour(harbours,SelectedName);
                    int xHarbourPosition= harbourSelected.getxPosition();
                    int yHarbourPosition = harbourSelected.getyPosition();
                    String shipName = shipNameTextfield.getText().toString();
                    System.out.println("shipname in textfield is "+shipName);
                    if(shipName.length()!=0){



                    Ship shipToAdd= new Ship(xHarbourPosition,yHarbourPosition,direction,cost, shipName,companyId);

                    shipDaoImplementation.add(shipToAdd);}else{
                        JOptionPane.showMessageDialog(null,"shipName is empty, which is not allowed");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Oder zeige eine Fehlermeldung an
                }
            }
        });


        unloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 unload cargo button check before, if the ship is loaded or not.
                 if s,then unload and update, else throw error!
                 if ship is at destination harbour,then checks position, and if loaded, then unloaded
                 */
               String shipName= giveCurrentNameOfComboBox(shipComboBox);
               Ship ship = shipDaoImplementation.getShipByName(shipName);

               String harbourName = giveCurrentNameOfComboBox(harbourCombobox);
               Harbour harbour = harbourDaoImplementation.getHarbourByName(harbourName);

               String companyName = giveCurrentNameOfComboBox(companyComboBox);
               Company company =companyDaoImplementation.getCompanyByName(companyName);
               double balance = company.getCompanyBalance();
//
                boolean condition = isShipHarbourArrived(ship,harbour);
                boolean count = true;
                if( harbour.isFree()&&condition==true && count){

                    ship.unloadCargo();

                        balance += ship.getCargo().getValue();
                        double balanceCompany= balance +company.getCompanyBalance();

                        company.setCompanyBalance(balanceCompany);
                        count = false;
                    /**
                     *  if harbour is not full, then unload, otherwise wait.
                      */
                } else {
                     ship.setState(ShipState.WAIT);
                    JOptionPane.showMessageDialog(null,"Harbour is not free now! ");
                }
            }
        });




        moveToButton.addActionListener(new ActionListener() {
            String shipName= giveCurrentNameOfComboBox(shipComboBox);
            Ship ship = shipDaoImplementation.getShipByName(shipName);

            String harbourName = giveCurrentNameOfComboBox(moveToHarbourComboBox);
            Harbour destinationHarbour = harbourDaoImplementation.getHarbourByName(harbourName);

            String companyName = giveCurrentNameOfComboBox(companyComboBox);
            Company company =companyDaoImplementation.getCompanyByName(companyName);

            boolean shipWaitState= isShipHarbourArrived(ship, destinationHarbour);

            @Override
            public void actionPerformed(ActionEvent e) {
                //If ship is parket at harbour, then it can move. it can move to other harbour only.
                System.out.println("ship name is "+ship.getName()+", x position "+ship.getxPosition()+", y position "+ship.getyPosition());

                System.out.println("ship name is "+ship.findHarbour(ship.getxPosition(),ship.getyPosition()).getName());

                if(shipWaitState && !ship.isSameHarbour(ship.getHarbour(), destinationHarbour) ){
                    ship.setState(ShipState.MOVING);

                }

                JOptionPane.showMessageDialog(null, "Destination Harbour is same as current!");

             }

        });

        loadButton.addActionListener(new ActionListener() {

            String shipName= giveCurrentNameOfComboBox(shipComboBox);
            Ship ship = shipDaoImplementation.getShipByName(shipName);

            String harbourName = giveCurrentNameOfComboBox(harbourCombobox);
            Harbour harbour = harbourDaoImplementation.getHarbourByName(harbourName);

            String companyName = giveCurrentNameOfComboBox(companyComboBox);
            Company company =companyDaoImplementation.getCompanyByName(companyName);



            @Override
            public void actionPerformed(ActionEvent e) {


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

        //create diaglog, where company is echtzeit in list addiert
        //on clicking launch, it should open dialog with company, harbour list and  details for ship
        // after clicking lauch, it should be created on hafen with dir and cost


        //with company, there should be list of ships also available and move to button should reach to harbour
        // if harbour x,y position is equal to ship position,then it is reached
        //loadcargo, can only be done,if it is in hafen. otherweise fehler meldung
        //after cargo is loaded, it should print quittung der erfolgreich ladung
        //unloaded cargo must update balance of company




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




    public List<Ship> updateShipComboBox(Company company){
        int id =company.getCompanyId();
        ShipDaoImplementation shipDaoImplementation = new ShipDaoImplementation();
        List<Ship> ships = shipDaoImplementation.getShipsByCompanyId(id);
        return ships;


    }


    public Company iterateListCompanies(List<Company>  companies,String name){
        Company company = null;

        for(Company company1:companies){
            if(name.equals(company1.getName())){
                company=company1;
                break;
            }

        }
        return company;
    }

    public Ship iterateShips(List<Ship>ships,String shipName){
        Ship ship = null;

        for(Ship ship1:ships){
            if(ship1.getName().equals(shipName)){
                ship=ship1;
                break;
            }
        }
      return   ship;
    }

    public Harbour iterateHarbour(List<Harbour> harbours, String harnourName){

        Harbour harbour = null;
        for(Harbour harbour1:harbours){
            if(harnourName.equals(harbour1.getName())){
                harbour=harbour1;
                break;
            }
        }
        return harbour;
    }

    private  String giveCurrentNameOfComboBox(JComboBox<String> comboBox){
        String string = comboBox.getSelectedItem().toString();
        return string;
    }

    private boolean isShipHarbourArrived(Ship ship, Harbour harbour){

        if(ship.getxPosition()==harbour.getxPosition() && ship.getyPosition()==harbour.getyPosition()){
            JOptionPane.showMessageDialog(null,"Ziel Hafen reached!");
            ship.setState(ShipState.WAIT);
            return true;
        }
        return false;
    }














}
