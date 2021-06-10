package mypckg;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChargeScreen extends JPanel {
	private JTextField amtField;
	private JTextField rechargeField;
	private JTextField statusField;
	JComboBox<Owner> ownerBox = new JComboBox<Owner>();
	JComboBox<Account> accountBox = new JComboBox<Account>();
	JComboBox<Vehicle> vehicleBox = new JComboBox<Vehicle>();

	
	void updateBoxes() {
		accountBox.removeAllItems();
		vehicleBox.removeAllItems();
		Owner ow = (Owner)ownerBox.getSelectedItem();
		for(Account a: ow.aList)
			accountBox.addItem(a);
		for(Vehicle v : ow.vList) {
			if(v.hasTag() == true)
			{
				if(v.fTag.getClass().getName().equalsIgnoreCase("mypckg.NhaiTag"))
				 vehicleBox.addItem(v);
			}
				
		}
	}
	public ChargeScreen(ArrayList<Owner> oList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		JLabel ownerLabel = new JLabel("Select Owner");
		ownerLabel.setBounds(35, 27, 85, 13);
		add(ownerLabel);
		
		for(Owner ow : oList) {
			ownerBox.addItem(ow);
		}
		ownerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBoxes();
			}
		});
		
		if(ownerBox.getItemCount() >= 1) {
			ownerBox.setSelectedIndex(0);
		}
		
		ownerBox.setBounds(149, 23, 272, 21);
		add(ownerBox);
		
		JLabel accountLabel = new JLabel("Select Account");
		accountLabel.setBounds(35, 80, 85, 13);
		add(accountLabel);
		
		
		accountBox.setBounds(149, 76, 272, 21);
		add(accountBox);
		
		JLabel amtLabel = new JLabel("Amount");
		amtLabel.setBounds(35, 148, 85, 13);
		add(amtLabel);
		
		amtField = new JTextField();
		amtField.setBounds(149, 145, 96, 19);
		add(amtField);
		amtField.setColumns(10);
		
		JButton creditButton = new JButton("Credit");
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountBox.getSelectedIndex() != -1) {
					float amt;
					try {
						amt = Float.parseFloat(amtField.getText());
					}
					catch(Exception exp) {
						statusField.setText("Credit Amount not valid");
						return;
					}
					Account a = (Account)accountBox.getSelectedItem();
					a.balance += amt;
					statusField.setText(amt + " credited");
				}
				else
					statusField.setText("No account Found");
			}
		});
		creditButton.setBounds(35, 205, 85, 21);
		add(creditButton);
		
		
		vehicleBox.setBounds(149, 278, 272, 21);
		add(vehicleBox);
		
		JLabel vehicleLabel = new JLabel("Select Vehicle");
		vehicleLabel.setBounds(35, 280, 85, 17);
		add(vehicleLabel);
		
		JLabel rechargeLabel = new JLabel("Amount");
		rechargeLabel.setBounds(35, 327, 72, 13);
		add(rechargeLabel);
		
		rechargeField = new JTextField();
		rechargeField.setBounds(149, 324, 96, 19);
		add(rechargeField);
		rechargeField.setColumns(10);
		
		JButton rechargeButton = new JButton("Recharge");
		rechargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vehicleBox.getSelectedIndex() != -1) {
					float amt;
					try {
						amt = Float.parseFloat(rechargeField.getText());
					}
					catch(Exception exp) {
						statusField.setText("Recharge Amount not valid");
						return;
					}
					Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
					NhaiTag nt = (NhaiTag)v.fTag;
					nt.addBalance(amt);
					statusField.setText(amt + " added as balance");
				}
				else
					statusField.setText("No vehicles Found");
				
			}
		});
		rechargeButton.setBounds(35, 397, 111, 21);
		add(rechargeButton);
		
		statusField = new JTextField();
		statusField.setBounds(10, 480, 668, 19);
		add(statusField);
		statusField.setColumns(10);
	}

}
