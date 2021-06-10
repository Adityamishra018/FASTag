package mypckg;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TagScreen extends JPanel {
	private JTextField statusField;
	JComboBox<Vehicle> vehicleBox = new JComboBox<Vehicle>();
	JComboBox<Owner> ownerBox = new JComboBox<Owner>();
	JComboBox<Account> accountBox = new JComboBox<Account>();
	JComboBox plazaBox = new JComboBox();
	TagFactory tagFactory = new TagFactory();

	
	void updateBoxes() {
		vehicleBox.removeAllItems();
		accountBox.removeAllItems();
		Owner item = (Owner)ownerBox.getSelectedItem();
		for(Vehicle v : item.vList) {
			vehicleBox.addItem(v);
		}
		for(Account a : item.aList) {
			accountBox.addItem(a);
		}
	}
	public TagScreen(ArrayList<Owner> oList,ArrayList<TollPlaza> pList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		JLabel ownerLabel = new JLabel("Select Owner");
		ownerLabel.setBounds(75, 68, 97, 13);
		add(ownerLabel);
		
		for(Owner o : oList) {
			ownerBox.addItem(o);
		}
		ownerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBoxes();
			}
		});
		
		if(ownerBox.getItemCount() >=1) {
			ownerBox.setSelectedIndex(0);
		}
		
		for(TollPlaza tp : pList) {
			plazaBox.addItem(tp);
		}
		plazaBox.addItem("All");
		
		if(plazaBox.getItemCount() >=1) {
			plazaBox.setSelectedIndex(0);
		}
		
		ownerBox.setBounds(213, 64, 351, 21);
		add(ownerBox);
		
		JLabel vehicleLabel = new JLabel("Select Vehicle");
		vehicleLabel.setBounds(75, 122, 97, 13);
		add(vehicleLabel);
		
		vehicleBox.setBounds(213, 118, 351, 21);
		add(vehicleBox);
		
		JLabel typeLable = new JLabel("Type");
		typeLable.setBounds(75, 174, 45, 13);
		add(typeLable);
		
		JRadioButton nhaiButton = new JRadioButton("NHAI Tag");
		nhaiButton.setBounds(213, 170, 103, 21);
		add(nhaiButton);
		
		JRadioButton bankButton = new JRadioButton("Bank Specific Tag");
		bankButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accountBox.removeAllItems();
					accountBox.setEnabled(true);
					
					Owner o = (Owner)ownerBox.getSelectedItem();
					for(Account a : o.aList) {
						accountBox.addItem(a);
					}
				}
				else
					accountBox.setEnabled(false);
			}
		});
		bankButton.setBounds(336, 170, 148, 21);
		add(bankButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(bankButton);
		group.add(nhaiButton);
		
		JLabel accountLabel = new JLabel("Select Account");
		accountLabel.setBounds(75, 230, 85, 13);
		add(accountLabel);
		
		accountBox.setBounds(213, 226, 351, 21);
		add(accountBox);
		
		JButton attachButton = new JButton("Attach");
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vehicleBox.getSelectedIndex() != -1) {
					if(nhaiButton.isSelected() == true) {
						Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
						if(plazaBox.getSelectedItem().getClass().getName().equalsIgnoreCase("java.lang.string"))
						{
							v.setTag(tagFactory.getTag("all"));
							statusField.setText("Tag added successfully");
						}
						else
						{
							v.setTag(tagFactory.getTag(plazaBox.getSelectedItem().toString()));
							statusField.setText("Tag added successfully");
						}
						
					}
					else {
						if(accountBox.getSelectedIndex() != -1) {
							
							if(plazaBox.getSelectedItem().getClass().getName().equalsIgnoreCase("java.lang.string")) {
								Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
								v.setTag(tagFactory.getTag((Account)accountBox.getSelectedItem(),"all"));
								statusField.setText("Tag added successfully");
								
							}
							else
							{
								Vehicle v = (Vehicle)vehicleBox.getSelectedItem();
								v.setTag(tagFactory.getTag((Account)accountBox.getSelectedItem(),plazaBox.getSelectedItem().toString()));
								statusField.setText("Tag added successfully");
							}
						}
						else
							statusField.setText("No account available");
					}	
				}
				else
					statusField.setText("No vehicle found");				
			}
		});
		attachButton.setBounds(75, 346, 85, 21);
		add(attachButton);
		
		statusField = new JTextField();
		statusField.setBounds(10, 471, 668, 28);
		add(statusField);
		statusField.setColumns(10);
		
		
		plazaBox.setBounds(213, 280, 351, 21);
		add(plazaBox);
		
		JLabel plazaLabel = new JLabel("Select Plaza");
		plazaLabel.setBounds(75, 284, 97, 13);
		add(plazaLabel);

	}
}
