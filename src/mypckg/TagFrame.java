package mypckg;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;

public class TagFrame extends JFrame {

	private JPanel contentPane;
	private JTextField typeField;
	private JTextField idField;
	private JTextField balField;
	private JLabel typeLabel;
	private JLabel idLabel;
	private JLabel balLabel;
	private JLabel plazaLabel;
	private JTextField plazaField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagFrame frame = new TagFrame(new NhaiTag(NetcMapper.getInstance(),Integer.parseInt(args[0]),args[1]));
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TagFrame(Fastag fTag) {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(450, 300));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel upperLabel = new JLabel("img");
		upperLabel.setBounds(0, 10, 426, 42);
		upperLabel.setIcon(new ImageIcon(new ImageIcon("Images/upper.png").getImage().getScaledInstance(upperLabel.getWidth(), 
				upperLabel.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(upperLabel);
		
		JLabel lowerLabel = new JLabel("img");
		lowerLabel.setBounds(0, 221, 426, 42);
		lowerLabel.setIcon(new ImageIcon(new ImageIcon("Images/lower.png").getImage().getScaledInstance(lowerLabel.getWidth(), 
				lowerLabel.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(lowerLabel);
		
		typeField = new JTextField();
		typeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeField.setBorder(null);
		typeField.setBounds(133, 58, 230, 19);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idField.setBorder(null);
		idField.setBounds(133, 104, 230, 19);
		contentPane.add(idField);
		idField.setColumns(10);
		
		balField = new JTextField();
		balField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		balField.setBorder(null);
		balField.setBounds(133, 133, 230, 19);
		contentPane.add(balField);
		balField.setColumns(10);
		
		typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeLabel.setBounds(35, 62, 88, 13);
		contentPane.add(typeLabel);
		
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idLabel.setBounds(35, 108, 45, 13);
		contentPane.add(idLabel);
		
		balLabel = new JLabel("Balance");
		balLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		balLabel.setBounds(35, 140, 88, 13);
		contentPane.add(balLabel);
		
		plazaLabel = new JLabel("Plaza");
		plazaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		plazaLabel.setBounds(35, 177, 71, 13);
		contentPane.add(plazaLabel);
		
		plazaField = new JTextField();
		plazaField.setBorder(null);
		plazaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		plazaField.setBounds(133, 176, 276, 19);
		contentPane.add(plazaField);
		plazaField.setColumns(10);
		
		
		if(fTag.getClass().getName().equalsIgnoreCase("mypckg.nhaitag")) {
			NhaiTag nt = (NhaiTag)fTag;
			typeField.setText("NHAI TAG");
			idField.setText(Integer.toString(nt.getId()));
			balField.setText(Float.toString(nt.getBalance()));
			plazaField.setText(nt.getFlag());
		}
		else
		{
			BankSpecificTag bnt = (BankSpecificTag)fTag;
			typeField.setText("BANK SPECIFIC TAG");
			idField.setText(Integer.toString(bnt.getId()));
			balField.setText(Float.toString(bnt.getAccount().getBalance()));
			plazaField.setText(bnt.getFlag());
		}
	}
}
