
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

public class GUI {

	private JFrame frame;
	private JTable table_1;
	static DefaultTableModel model_1 ;
	static DefaultTableModel model_2 ;
	int maxConnections,maxDevices;
	static ArrayList<Device> devices;
	private JTable table_2;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	
	public void run() {
		try {
			this.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public GUI(int maxConnections, int maxDevices, ArrayList<Device> devices) {
		this.devices = devices;
		this.maxConnections = maxConnections;
		this.maxDevices = maxDevices;
		initialize();
		run();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize_table1();
		initialize_table2();
	}
	
	private void initialize_table1() {
		table_1 = new JTable(new DefaultTableModel(new Object[]{"Device", "Type", "Arrived", "Waiting", "Occupied" , "Activity", "Logged Out"},0));
		table_1.setFont(new Font("Consolas", Font.BOLD, 14));
		table_1.setFillsViewportHeight(true);
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table_1.setDefaultRenderer(Object.class, centerRenderer);
		table_1.setIntercellSpacing(new Dimension(40,5));
		table_1.setRowHeight(30);
		model_1 = (DefaultTableModel) table_1.getModel();
		
		for (int i=0 ; i<devices.size(); i++) {
			model_1.addRow(new Object[] {devices.get(i).name , devices.get(i).type});
		}
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		frame.getContentPane().add(scrollPane_1, BorderLayout.NORTH);
		scrollPane_1.setPreferredSize(new Dimension(1000, 226));
	}
	
	private void initialize_table2() {
		table_2 = new JTable(new DefaultTableModel(new Object[]{"Connection","Activity"},0));
		table_2.setFont(new Font("Consolas", Font.BOLD, 14));
		table_2.setFillsViewportHeight(true);
		table_2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		
		
		table_2.setIntercellSpacing(new Dimension(10,5));

		table_2.getColumnModel().getColumn(0).setMinWidth(130);
		table_2.getColumnModel().getColumn(0).setMaxWidth(140);
		table_2.setRowHeight(30);
		
		
		

		model_2 = (DefaultTableModel) table_2.getModel();

		
		for (int i=0 ; i<maxConnections ; i++) {
			model_2.addRow(new Object[] {"Connection " + (i+1)});
		}
		
		scrollPane_2 = new JScrollPane(table_2);
		frame.getContentPane().add(scrollPane_2, BorderLayout.SOUTH);
		scrollPane_2.setPreferredSize(new Dimension(1000,276));
	}
	
 	public static void arrived(Device device) {
		int table_1_row = devices.indexOf(device);
		model_1.setValueAt("done", table_1_row, 2);
		model_1.setValueAt("done", table_1_row, 3);
	}
 	
	public static void occupied(Device device) {
		int table_1_row = devices.indexOf(device);
		model_1.setValueAt("done", table_1_row, 4);
		model_1.setValueAt("done", table_1_row, 5);

		
		int table_2_row = device.connectionNumber-1;
		
		String prev = (String) model_2.getValueAt(table_2_row, 1);
		if (prev == null)
			prev = "";
		
		prev = prev + " " + device.name + " (" + device.type + ") " + "Occupied" + " | ";		
		prev = prev + " " + device.name + " (" + device.type + ") " + "Activity" + " | ";
		model_2.setValueAt(prev, table_2_row, 1);
		
	}
	public static void finished(Device device) {
		int table_1_row = devices.indexOf(device);
		model_1.setValueAt("done", table_1_row, 6);
		

		int table_2_row = device.connectionNumber-1;
		
		String prev = (String) model_2.getValueAt(table_2_row, 1);
		if (prev == null)
			prev = "";

		model_2.setValueAt(prev + " " + device.name + " (" + device.type + ") " + "Logged out" + " | ", table_2_row, 1);
	}
}
