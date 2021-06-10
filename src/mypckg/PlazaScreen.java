package mypckg;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlazaScreen extends JPanel {
	private JTextField nameField;
	private JTextField lightField = new JTextField();
	private JTextField commField = new JTextField();
	JComboBox<TollPlaza> plazaBox = new JComboBox<TollPlaza>();
	JComboBox<Vehicle> vehicleBox = new JComboBox<Vehicle>();
	private JTextField statusField;
	
	void updatePlazaBox(ArrayList<TollPlaza> pList) {
		plazaBox.removeAllItems();
		for(TollPlaza tp : pList) {
			plazaBox.addItem(tp);
		}
	}
	

	public PlazaScreen(ArrayList<Owner> oList,ArrayList<TollPlaza> pList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		JLabel addplazaLabel = new JLabel("Add Plaza");
		addplazaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addplazaLabel.setBounds(30, 10, 81, 13);
		add(addplazaLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(30, 33, 45, 13);
		add(nameLabel);
		
		updatePlazaBox(pList);
		
		nameField = new JTextField();
		nameField.setBounds(118, 30, 130, 19);
		add(nameField);
		nameField.setColumns(10);
		
		JButton addButton = new JButton("Add Plaza");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().isEmpty() == false) {
					pList.add(new TollPlaza(nameField.getText()));
					statusField.setText("Toll Plaza Added");
					updatePlazaBox(pList);
				}
			}
		});
		addButton.setBounds(345, 29, 103, 21);
		add(addButton);
		
		lightField.setText("200");
		commField.setText("300");
		
		for(Owner ow : oList) {
			for(Vehicle v : ow.vList) {
				vehicleBox.addItem(v);
			}
		}
		
		JLabel fareLabel = new JLabel("Fare Chart");
		fareLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fareLabel.setBounds(30, 73, 70, 19);
		add(fareLabel);
		
		JLabel lightLabel = new JLabel("Light");
		lightLabel.setBounds(30, 108, 45, 13);
		add(lightLabel);
		
		JLabel commLabel = new JLabel("Commercial");
		commLabel.setBounds(30, 139, 81, 13);
		add(commLabel);
		
		lightField.setBounds(118, 105, 96, 19);
		add(lightField);
		lightField.setColumns(10);
		
		commField.setBounds(118, 136, 96, 19);
		add(commField);
		commField.setColumns(10);
		
		JLabel passLabel = new JLabel("Pass Vehicle");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passLabel.setBounds(30, 182, 96, 13);
		add(passLabel);
		
		JLabel tollLabel = new JLabel("Select Plaza");
		tollLabel.setBounds(30, 220, 70, 13);
		add(tollLabel);
		
		JLabel vehicleLabel = new JLabel("Select Vehicle");
		vehicleLabel.setBounds(30, 268, 96, 13);
		add(vehicleLabel);
		
		
		plazaBox.setBounds(136, 216, 161, 21);
		add(plazaBox);
		
		vehicleBox.setBounds(136, 264, 161, 21);
		add(vehicleBox);
		
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(plazaBox.getSelectedIndex() != -1 && vehicleBox.getSelectedIndex() != -1) {
					TollPlaza tp = (TollPlaza)plazaBox.getSelectedItem();
					Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
					if(v.hasTag() == true) {
						if(v.getType().equalsIgnoreCase("light") == true) {
							if(v.getTag().getClass().getName().equalsIgnoreCase("mypckg.NhaiTag") == true) {
								NhaiTag tg = (NhaiTag)v.getTag();
								if(tg.getBalance() > Float.parseFloat(lightField.getText())) {
									if(tg.getFlag().equalsIgnoreCase("all")) {
										tg.balance -= Float.parseFloat(lightField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(lightField.getText()));	
									}
									else if(tg.getFlag().equalsIgnoreCase(plazaBox.getSelectedItem().toString())) {
										tg.balance -= Float.parseFloat(lightField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(lightField.getText()));
									}
									else
										statusField.setText("Can't pass through this plaza");
								}	
								else
									statusField.setText("Not enough balance !");
							}
							else
							{
								BankSpecificTag btg = (BankSpecificTag)v.getTag();
								if(btg.getAccount().getBalance() > Float.parseFloat(lightField.getText())) {
									if(btg.getFlag().equalsIgnoreCase("all")) {
										btg.getAccount().balance -= Float.parseFloat(lightField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(lightField.getText()));
									}
									else if(btg.getFlag().equalsIgnoreCase(plazaBox.getSelectedItem().toString())) {
										btg.getAccount().balance -= Float.parseFloat(lightField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(lightField.getText()));
									}
									else
										statusField.setText("Can't pass through this plaza");
									
								}
								else
									statusField.setText("Not enough balance !");
							}
						}
						else if(v.getType().equalsIgnoreCase("commercial") == true)
						{
							if(v.getTag().getClass().getName().equalsIgnoreCase("mypckg.NhaiTag") == true) {
								NhaiTag tg = (NhaiTag)v.getTag();
								if(tg.getBalance() > Float.parseFloat(commField.getText())) {
									
									if(tg.getFlag().equalsIgnoreCase("all")) {
										tg.balance -= Float.parseFloat(commField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(commField.getText()));
									}
									else if(tg.getFlag().equalsIgnoreCase(plazaBox.getSelectedItem().toString())) {
										tg.balance -= Float.parseFloat(commField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(commField.getText()));
									}
									else
										statusField.setText("Can't pass through this plaza");
								}
								else
									statusField.setText("Not enough balance !");
							}
							else
							{
								BankSpecificTag btg = (BankSpecificTag)v.getTag();
								if(btg.getAccount().getBalance() > Float.parseFloat(commField.getText())) {
									if(btg.getFlag().equalsIgnoreCase("all")) {
										btg.getAccount().balance -= Float.parseFloat(commField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(commField.getText()));
									}
									else if(btg.getFlag().equalsIgnoreCase(plazaBox.getSelectedItem().toString())) {
										btg.getAccount().balance -= Float.parseFloat(commField.getText());
										statusField.setText("Vehicle Passed");
										tp.PassVehicle(v,Float.parseFloat(commField.getText()));
									}
									else
										statusField.setText("can't pass through this plaza");
									
								}
								else
									statusField.setText("Not enough balance !");
							}
						}
					}
					else
						statusField.setText("Vehicle has no fastag !");
				}
				else
					statusField.setText("Incomplete info to pass");
			}
		});
		passButton.setBounds(30, 323, 85, 21);
		add(passButton);
		
		statusField = new JTextField();
		statusField.setBounds(15, 480, 663, 19);
		add(statusField);
		statusField.setColumns(10);
	}
}
