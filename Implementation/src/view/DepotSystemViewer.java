package view;

import javax.swing.*;

import model.Parcel;
import model.Worker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepotSystemViewer extends JFrame {
    //Panels to display data
    private JTextArea parcelListArea;
    private JTextArea customerQueueArea;
    private JTextArea currentParcelArea;

    //Buttons for parcel and customer actions
    private JButton addParcelButton;
    private JButton editParcelButton;
    private JButton addCustomerButton;
    private JButton editCustomerButton;

    //Buttons for system actions
    private JButton reloadDataButton;
    private JButton processNextButton;
    private JButton quitButton;

    public DepotSystemViewer() {
        //Set main frame properties
        setTitle("Depot Parcel Processing System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Use GridBagLayout to divide the layout into 4 sections
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; //Make components stretch to fill available space

        //Create and configure the central panels
        parcelListArea = createTextArea();
        JPanel parcelPanel = createTitledPanel("Parcels in Depot:", parcelListArea);

        customerQueueArea = createTextArea();
        JPanel queuePanel = createTitledPanel("Customer Queue:", customerQueueArea);

        currentParcelArea = createTextArea();
        JPanel currentParcelPanel = createTitledPanel("Current Parcel Being Processed:", currentParcelArea);

        //Create the action buttons
        addParcelButton = new JButton("Add Parcel");
        editParcelButton = new JButton("Edit Parcel");
        addCustomerButton = new JButton("Add Customer");
        editCustomerButton = new JButton("Edit Customer");
        reloadDataButton = new JButton("Reload Parcel and Customer Data");
        processNextButton = new JButton("Process Next Customer in Queue");
        quitButton = new JButton("Exit Application");

        //Apply uniform fonts to buttons
        setButtonFont(addParcelButton);
        setButtonFont(editParcelButton);
        setButtonFont(addCustomerButton);
        setButtonFont(editCustomerButton);
        setButtonFont(reloadDataButton);
        setButtonFont(processNextButton);
        setButtonFont(quitButton);

        //Add action listeners to the quit button
        quitButton.addActionListener(new QuitButtonListener());

        //Create control panels for the buttons
        JPanel parcelControlPanel = new JPanel();
        parcelControlPanel.add(addParcelButton);
        parcelControlPanel.add(editParcelButton);

        JPanel customerControlPanel = new JPanel();
        customerControlPanel.add(addCustomerButton);
        customerControlPanel.add(editCustomerButton);

        //Create a control panel for the system buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 5)); 
        buttonPanel.add(reloadDataButton);
        buttonPanel.add(processNextButton);
        buttonPanel.add(quitButton);

        
        buttonPanel.setPreferredSize(new Dimension(150, 180)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //Position the components in the layout using GridBagLayout
        //Top left for the parcel panel 
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0.5; gbc.weighty = 0.5; // Allow parcel panel to take up available space
        add(parcelPanel, gbc);

        //Top right for the customer queue panel
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0.5; gbc.weighty = 0.5; // Allow queue panel to take up available space
        add(queuePanel, gbc);

        //Bottom left area for the current parcel being processed panel
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0.5; gbc.weighty = 0.5; //Allow current parcel panel to take up available space
        add(currentParcelPanel, gbc);

        //Bottom right area for some buttons
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0.2; gbc.weighty = 0.5;
        add(buttonPanel, gbc);

        //Positioning parcel action buttons below the parcel panel
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridheight = 1; //Position parcel control panel below parcel panel
        add(parcelControlPanel, gbc);

        //Positioning customer action buttons below the queue panel
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridheight = 1; //Position customer control panel below queue panel
        add(customerControlPanel, gbc);

        //Display the frame
        setVisible(true);
    }

    //Makes a JTextArea with default conditions
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(20, 30);
        textArea.setEditable(false); 
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true); // Wrap text
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setBackground(Color.WHITE); // White background
        textArea.setForeground(Color.DARK_GRAY); // Dark gray text
        return textArea;
    }

    //Creates a JPanel
    private JPanel createTitledPanel(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16)); 
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

    //Sets a font style for all the small buttons
    private void setButtonFont(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12)); 
    }

    //handles quit button click
    private class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    DepotSystemViewer.this,
                    "Are you sure you want to quit the application?",
                    "Confirm Quit",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); //Exit application
            }
        }
    }

    //Methods to update the text areas
    public void updateParcelList(String parcels) {
        parcelListArea.setText(parcels);
    }

    public void updateCustomerQueue(String queue) {
        customerQueueArea.setText(queue);
    }

    public void updateCurrentParcel(String parcelInfo) {
        currentParcelArea.setText(parcelInfo);
    }

    // Getters for buttons
    public JButton getAddParcelButton() {
        return addParcelButton;
    }

    public JButton getEditParcelButton() {
        return editParcelButton;
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JButton getEditCustomerButton() {
        return editCustomerButton;
    }

    public JButton getReloadDataButton() {
        return reloadDataButton;
    }

    public JButton getProcessNextButton() {
        return processNextButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}









