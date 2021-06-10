package mypckg;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.*;
import java.awt.event.ActionEvent;

public class HistoryScreen extends JPanel {
	String[] columns = {"History"};
	String[][] data = {{"NoEnteries"}};
	
	private JTable table = new JTable(new DefaultTableModel(data,columns));
	JComboBox<TollPlaza> plazaBox = new JComboBox<TollPlaza>();

	void updateTable() {
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
	    TollPlaza item = (TollPlaza)plazaBox.getSelectedItem();
	    String path = "tollrecords/" + item.getName() +".txt";
	    FileInputStream fp=null;
		try {
			fp = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
	    Scanner sc = new Scanner(fp);
	    while(sc.hasNextLine()) {
	    		dtm.addRow(new Object[]{sc.nextLine()});
	    }
	    sc.close();
	}
	public HistoryScreen(ArrayList<TollPlaza> pList) {
		setPreferredSize(new Dimension(688, 509));
		setLayout(new BorderLayout(0, 0));
		
		for(TollPlaza tp : pList) {
			plazaBox.addItem(tp);
		}
		
		plazaBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		
		if(plazaBox.getItemCount() >= 1) {
			plazaBox.setSelectedIndex(0);
		}
 
		add(plazaBox, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);

	}

}
