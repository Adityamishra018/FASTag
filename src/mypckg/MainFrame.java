package mypckg;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	ArrayList<Owner> oList = new ArrayList<Owner>();
	ArrayList<TollPlaza> pList = new ArrayList<TollPlaza>();
	JPanel screenPanel;
	JPanel ownerScreen = new OwnerScreen(oList);
	JPanel detailsScreen = new DetailsScreen(oList);
	JPanel tagScreen = new TagScreen(oList,pList);
    JPanel addScreen = new AddScreen(oList);
    JPanel plazaScreen = new PlazaScreen(oList,pList);
    JPanel chargeScreen = new ChargeScreen(oList);
    JPanel historyScreen = new HistoryScreen(pList);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setTitle("Fastag Simulation");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setResizable(false);
		contentPane = (JPanel)rootPane.getContentPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		JLabel picLabel = new JLabel();
		picLabel.setBounds(10, 10, 876, 83);
		picLabel.setIcon(new ImageIcon(new ImageIcon("Images/fastag.png").getImage().getScaledInstance(picLabel.getWidth(), 
														picLabel.getHeight(), Image.SCALE_SMOOTH)));
		getContentPane().add(picLabel);
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(245, 245, 245));
		controlPanel.setBounds(10, 103, 178, 509);
		getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		screenPanel = new JPanel();
		screenPanel.setBackground(Color.LIGHT_GRAY);
		screenPanel.setBounds(198, 103, 688, 509);
		getContentPane().add(screenPanel);
		screenPanel.setLayout(new CardLayout(0, 0));
		
		screenPanel.add(ownerScreen, "OWNER_SCREEN");
		screenPanel.add(detailsScreen, "DETAIL_SCREEN");
		screenPanel.add(addScreen, "ADD_SCREEN");
		screenPanel.add(tagScreen,"TAG_SCREEN");
		screenPanel.add(plazaScreen,"PLAZA_SCREEN");
		screenPanel.add(chargeScreen,"CHARGE_SCREEN");
		screenPanel.add(historyScreen,"HISTORY_SCREEN");
		
		JButton addOwnerButton = new JButton("Add Owner");
		addOwnerButton.setFocusPainted(false);
		addOwnerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "OWNER_SCREEN");

			}
		});
		addOwnerButton.setBounds(10, 40, 158, 21);
		controlPanel.add(addOwnerButton);
		
		JButton detailButton = new JButton("Show Details");
		detailButton.setFocusPainted(false);
		detailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    screenPanel.remove(detailsScreen);
			    detailsScreen = new DetailsScreen(oList);
			    screenPanel.add(detailsScreen,"DETAIL_SCREEN");
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "DETAIL_SCREEN");
			}
		});
		detailButton.setBounds(10, 128, 158, 21);
		controlPanel.add(detailButton);		
		
		JButton addButton = new JButton("Add Vehicle/Account");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenPanel.remove(addScreen);
			    addScreen = new AddScreen(oList);
			    screenPanel.add(addScreen,"ADD_SCREEN");
			    CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "ADD_SCREEN");
			}
		});
		addButton.setBounds(10, 84, 158, 21);
		controlPanel.add(addButton);
		
		JButton attachButton = new JButton("Attach Tag");
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenPanel.remove(tagScreen);
			    tagScreen = new TagScreen(oList,pList);
			    screenPanel.add(tagScreen,"TAG_SCREEN");
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "TAG_SCREEN");
			}
		});
		attachButton.setBounds(10, 173, 158, 21);
		controlPanel.add(attachButton);
		
		JButton plazaButton = new JButton("Toll Plaza");
		plazaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenPanel.remove(plazaScreen);
			    plazaScreen = new PlazaScreen(oList,pList);
			    screenPanel.add(plazaScreen,"PLAZA_SCREEN");
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "PLAZA_SCREEN");
				
			}
		});
		plazaButton.setBounds(10, 221, 158, 21);
		controlPanel.add(plazaButton);
		
		JButton chargeButton = new JButton("Credit/Recharge");
		chargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenPanel.remove(chargeScreen);
			    chargeScreen = new ChargeScreen(oList);
			    screenPanel.add(chargeScreen,"CHARGE_SCREEN");
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "CHARGE_SCREEN");
			}
		});
		chargeButton.setBounds(10, 271, 158, 21);
		controlPanel.add(chargeButton);
		
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenPanel.remove(historyScreen);
			    historyScreen = new HistoryScreen(pList);
			    screenPanel.add(historyScreen,"HISTORY_SCREEN");
				CardLayout c1 = (CardLayout)screenPanel.getLayout();
				c1.show(screenPanel, "HISTORY_SCREEN");
			}
		});
		historyButton.setBounds(10, 478, 158, 21);
		controlPanel.add(historyButton);
	}
}
