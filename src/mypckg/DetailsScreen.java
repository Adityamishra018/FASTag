package mypckg;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class DetailsScreen extends JPanel {
	JComboBox<Owner> ownerBox = new JComboBox<Owner>();
	JComboBox<Account> accountBox = new JComboBox<Account>();
	JComboBox<Vehicle> vehicleBox = new JComboBox<Vehicle>();
	
	
	private JTextField nameField;
	private JTextField bankNameField;
	private JTextField accountField;
	private JTextField balanceField;
	private JTextField fastagField;
	private JTextField priceField;
	private JTextField vehicleField;
	private JTextField modelField;
	private JTextField statusField;
	private JTextField typeField;

	
	void updateBoxes() {
		accountBox.removeAllItems();
		vehicleBox.removeAllItems();
		Owner item = (Owner)ownerBox.getSelectedItem();
		for(Account a : item.aList) {
			accountBox.addItem(a);
		}
		
		for(Vehicle v : item.vList) {
			vehicleBox.addItem(v);
		}
	}
	public DetailsScreen(ArrayList<Owner> oList) {
			
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		JLabel selectOwnerLabel = new JLabel("Select Owner");
		selectOwnerLabel.setBounds(54, 30, 84, 27);
		add(selectOwnerLabel);
		
		for (Owner o : oList) {
			ownerBox.addItem(o);
		}
		
		ownerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBoxes();
			}
		});
		
		if(ownerBox.getItemCount() >= 1) {
			ownerBox.setSelectedIndex(0);
		}
		
		ownerBox.setBounds(167, 33, 311, 21);
		add(ownerBox);
		
		
		JButton showButton = new JButton("Show Details");
		showButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(accountBox.getSelectedIndex() == -1 && vehicleBox.getSelectedIndex() == -1)
				{
					statusField.setText("No information available");
					return;
				}
				
				Account a = (Account)accountBox.getSelectedItem();
				Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
				
				//Account Details
				if(accountBox.getSelectedIndex() != -1) {
					nameField.setText(a.getOwnerName());
					bankNameField.setText(a.getBankName());
					accountField.setText(Integer.toString(a.getAccountNo()));
					balanceField.setText(Float.toString(a.getBalance()));
				}
				else
				{
					nameField.setText("");
					bankNameField.setText("");
					accountField.setText("");
					balanceField.setText("");
				}
				
				//Vehicle Details
                if(vehicleBox.getSelectedIndex() != -1) {
					modelField.setText(v.getModel());
					vehicleField.setText(v.getVehicleNo());
					priceField.setText(Float.toString(v.getPrice()));
					if(v.hasTag() == true)
						fastagField.setText("Fastag Enabled");
					else
						fastagField.setText("Unavailable");
					typeField.setText(v.getType());
                }
                else
                {
                	modelField.setText("");
					vehicleField.setText("");
					priceField.setText("");
					fastagField.setText("");
					typeField.setText("");
                }
			}
		});
		showButton.setBounds(54, 176, 153, 27);
		add(showButton);
		
		JLabel selectAccountLabel = new JLabel("Select Account");
		selectAccountLabel.setBounds(54, 82, 103, 21);
		add(selectAccountLabel);
		
		JLabel selectVehicleLabel = new JLabel("Select Vehicle");
		selectVehicleLabel.setBounds(54, 136, 84, 13);
		add(selectVehicleLabel);
		
		JLabel accountLabel = new JLabel("Account Details");
		accountLabel.setBounds(64, 232, 104, 21);
		add(accountLabel);
		
		JLabel vehicleLabel = new JLabel("Vehicle Details");
		vehicleLabel.setBounds(491, 232, 104, 21);
		add(vehicleLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(64, 289, 45, 13);
		add(nameLabel);
		
		JLabel bankNameLabel = new JLabel("Bank Name");
		bankNameLabel.setBounds(64, 326, 74, 13);
		add(bankNameLabel);
		
		JLabel accNoLabel = new JLabel("Account No");
		accNoLabel.setBounds(64, 369, 74, 13);
		add(accNoLabel);
		
		JLabel balanceLabel = new JLabel("Balance");
		balanceLabel.setBounds(64, 408, 60, 13);
		add(balanceLabel);
		
		JLabel modelLabel = new JLabel("Model");
		modelLabel.setBounds(357, 289, 45, 13);
		add(modelLabel);
		
		JLabel vehicleNoLabel = new JLabel("Vehicle No");
		vehicleNoLabel.setBounds(357, 326, 74, 13);
		add(vehicleNoLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(357, 369, 60, 13);
		add(priceLabel);
		
		JLabel fastagLabel = new JLabel("Fastag Status");
		fastagLabel.setBounds(357, 408, 91, 13);
		add(fastagLabel);
		
		accountBox.setBounds(167, 82, 311, 21);
		add(accountBox);
		vehicleBox.setBounds(167, 132, 311, 21);
		add(vehicleBox);
		
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(153, 286, 131, 19);
		add(nameField);
		nameField.setColumns(10);
		
		bankNameField = new JTextField();
		bankNameField.setEditable(false);
		bankNameField.setBounds(153, 323, 131, 19);
		add(bankNameField);
		bankNameField.setColumns(10);
		
		accountField = new JTextField();
		accountField.setEditable(false);
		accountField.setColumns(10);
		accountField.setBounds(153, 366, 131, 19);
		add(accountField);
		
		balanceField = new JTextField();
		balanceField.setEditable(false);
		balanceField.setColumns(10);
		balanceField.setBounds(153, 405, 131, 19);
		add(balanceField);
		
		JButton quesButton = new JButton();
		quesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fastagField.getText().equalsIgnoreCase("Fastag Enabled")) {
					Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
					JFrame tagFrame = new TagFrame(v.fTag);
					tagFrame.setVisible(true);
				}
			}
		});
		quesButton.setBounds(604, 405, 23, 19);
		quesButton.setIcon(new ImageIcon(new ImageIcon("Images/question-mark.png").getImage().getScaledInstance(quesButton.getWidth(), 
														quesButton.getHeight(), Image.SCALE_SMOOTH)));
		add(quesButton);
		
		fastagField = new JTextField();
		fastagField.setEditable(false);
		fastagField.setColumns(10);
		fastagField.setBounds(464, 405, 131, 19);
		add(fastagField);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setColumns(10);
		priceField.setBounds(464, 366, 131, 19);
		add(priceField);
		
		vehicleField = new JTextField();
		vehicleField.setEditable(false);
		vehicleField.setColumns(10);
		vehicleField.setBounds(464, 323, 131, 19);
		add(vehicleField);
		
		modelField = new JTextField();
		modelField.setEditable(false);
		modelField.setColumns(10);
		modelField.setBounds(464, 286, 131, 19);
		add(modelField);
		
		statusField = new JTextField();
		statusField.setBounds(13, 480, 665, 19);
		add(statusField);
		statusField.setColumns(10);
		
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(357, 437, 45, 13);
		add(typeLabel);
		
		typeField = new JTextField();
		typeField.setEditable(false);
		typeField.setBounds(464, 434, 131, 19);
		add(typeField);
		typeField.setColumns(10);
	}
}
