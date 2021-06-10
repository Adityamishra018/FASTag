package mypckg;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddScreen extends JPanel {
	private JTextField statusField;
	private JTextField bankField;
	private JTextField accountField;
	private JTextField modelField;
	private JTextField priceField;
	
	JComboBox<Owner> ownerBox = new JComboBox<Owner>();
	JComboBox<String> typeBox = new JComboBox<String>();

	public AddScreen(ArrayList<Owner> oList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		typeBox.addItem("Light");
		typeBox.addItem("Commercial");
				
		for(Owner o : oList) {
			ownerBox.addItem(o);
		}
		
		ownerBox.setBounds(188, 53, 413, 21);
		add(ownerBox);
		
		JLabel ownerLabel = new JLabel("Select Owner");
		ownerLabel.setBounds(69, 55, 82, 17);
		add(ownerLabel);
		
		JLabel accountSection = new JLabel("Add Account");
		accountSection.setBounds(69, 108, 82, 13);
		add(accountSection);
		
		JLabel vehicleSection = new JLabel("Add Vehicle");
		vehicleSection.setBounds(519, 108, 82, 13);
		add(vehicleSection);
		
		statusField = new JTextField();
		statusField.setBounds(10, 471, 668, 28);
		add(statusField);
		statusField.setColumns(10);
		
		JLabel bankLabel = new JLabel("Bank Name");
		bankLabel.setBounds(69, 160, 82, 13);
		add(bankLabel);
		
		bankField = new JTextField();
		bankField.setBounds(166, 157, 96, 19);
		add(bankField);
		bankField.setColumns(10);
		
		JLabel accNoLabel = new JLabel("Account No");
		accNoLabel.setAutoscrolls(true);
		accNoLabel.setBounds(69, 196, 82, 13);
		add(accNoLabel);
		
		accountField = new JTextField();
		accountField.setEditable(false);
		accountField.setBounds(166, 193, 96, 19);
		add(accountField);
		accountField.setColumns(10);
		
		JLabel modelLabel = new JLabel("Model Name");
		modelLabel.setBounds(394, 160, 101, 13);
		add(modelLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(394, 238, 45, 13);
		add(priceLabel);
		
		modelField = new JTextField();
		modelField.setBounds(505, 157, 96, 19);
		add(modelField);
		modelField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(505, 235, 96, 19);
		add(priceField);
		priceField.setColumns(10);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean modification = false;
				Owner item = (Owner)ownerBox.getSelectedItem();
				
				//Add Account
				if(bankField.getText().isEmpty() == false) {
				Account newAcc = new Account(item.getName(),bankField.getText());
				item.addAccount(newAcc);
				accountField.setText(Integer.toString(newAcc.getAccountNo()));
				modification = true;
				}
				
				//Add Vehicle
				if(modelField.getText().isEmpty() == false) {
					float p=100000;
					try {
						p = Float.parseFloat(priceField.getText());
					}
					catch(Exception ecp){
						statusField.setText("Price not valid, setting 100000");
					}
					item.addVehicle(new Vehicle(modelField.getText(),(String)typeBox.getSelectedItem(),p));
					modification = true;
				}
				
			    //set status 
				if(modification == true) {
					statusField.setText(item.getName() + " Modified");
				}
				else
					statusField.setText("No Changes");
			}
		});
		addButton.setBounds(69, 274, 85, 21);
		add(addButton);
		
		
		typeBox.setBounds(505, 192, 96, 21);
		add(typeBox);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(394, 196, 45, 13);
		add(typeLabel);
       
	}
}
