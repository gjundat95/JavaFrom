package DemoLayout;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class DemoBorderLayout extends JFrame {

	public static void main(String[] args) {
		new DemoBorderLayout();
	}
	
	public DemoBorderLayout() {
		
		this.setTitle("Jundat95");
		this.setBounds(10,10,500,300);
		this.setLayout(new BorderLayout());
		addMenu();
		this.add(new Label("Database Class k12b ICTU."),BorderLayout.NORTH);
		this.add(contentJpanel());
		
		this.pack();
		this.show();
		
	}
	private void addMenu(){
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuView = new JMenu("View");
		
		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuBar.add(menuFile);
		menuBar.add(menuView);
		menuFile.add(menuItemOpen);
		this.setJMenuBar(menuBar);
	}
	private JPanel contentJpanel(){
		
		JPanel panel = new JPanel();
		JTable table = new JTable(new SimpleJTableModel());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		
		return panel;
	}
}

class SimpleJTableModel extends AbstractTableModel {
	
	private Object[][] data = {};
	private Object[] columNames = {"ID","Name","Gender"};
	private Class[] columClass = {Integer.class,String.class,String.class};
	private Object[][] rowdata = new Object[][] {
		{new Integer(1),"Ngo Doan Tinh","Famale"},
		{new Integer(2),"Pham Xuan Tu","Male"},
		{new Integer(3),"Nguyen My Hanh","Male"},
		{new Integer(4),"Chu Xuan Linh","Male"},
		{new Integer(1),"Ngo Doan Tinh","Famale"},
		{new Integer(2),"Pham Xuan Tu","Male"},
		{new Integer(3),"Nguyen My Hanh","Male"},
		{new Integer(4),"Chu Xuan Linh","Male"}
	};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowdata.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rowdata[rowIndex][columnIndex];
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		return columClass[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
		rowdata[rowIndex][columnIndex] = aValue ;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		boolean isCellEdit = true;
		if(columnIndex == 0){
			isCellEdit = false;
		}
		return isCellEdit;
	}
	
	
}

