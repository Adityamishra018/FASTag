package mypckg;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class OwnerScreen extends JPanel {
	private JTextField nameField;
	private JTextField uidaiField;
	private JTextField statusField;

	public OwnerScreen(ArrayList<Owner> oList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(null);
		
		JLabel addOwner = new JLabel("Add Owner");
		addOwner.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addOwner.setBounds(60, 44, 107, 47);
		add(addOwner);
		
		nameField = new JTextField();
		nameField.setBounds(146, 97, 171, 19);
		add(nameField);
		nameField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Enter Name");
		nameLabel.setBounds(60, 101, 68, 12);
		add(nameLabel);
		
		uidaiField = new JTextField();
		uidaiField.setEditable(false);
		uidaiField.setBounds(146, 259, 171, 19);
		add(uidaiField);
		uidaiField.setColumns(10);
		
		JLabel uidaiLabel = new JLabel("UIDAI");
		uidaiLabel.setBounds(60, 262, 55, 13);
		add(uidaiLabel);
		
		JLabel addressLabel = new JLabel("Enter Address");
		addressLabel.setBounds(60, 138, 88, 13);
		add(addressLabel);
		
		JTextArea addressField = new JTextArea();
		addressField.setBounds(60, 164, 257, 76);
		add(addressField);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Owner newOwner = new Owner(nameField.getText(),addressField.getText());
				uidaiField.setText(newOwner.getUidai());
				oList.add(newOwner);
				statusField.setText(nameField.getText() + " is added as an Owner");
			}
		});
		createButton.setForeground(Color.BLACK);
		createButton.setBackground(new Color(175, 238, 238));
		createButton.setBounds(60, 329, 107, 21);
		add(createButton);
		
		statusField = new JTextField();
		statusField.setBounds(10, 472, 668, 27);
		add(statusField);
		statusField.setColumns(10);

	}
}
