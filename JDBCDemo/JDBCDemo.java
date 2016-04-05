package JDBCDemo;

import java.awt.Panel;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JDBCDemo extends JFrame{
	
	MySqlHelper sqlHelper;
	ResultSet resultSet;
	Panel panel;
	JScrollPane jScrollPane;
	
	
	public JDBCDemo() throws ClassNotFoundException, SQLException{
		
		this.setTitle("JTable...");
		this.setBounds(50,50,800,700);
		
		// Lay du lieu tu MySQl
		sqlHelper = new MySqlHelper();
		String query = "Select *from tblSinhVien";
		resultSet = sqlHelper.getData(query);
		TableModel tableModel = new TableModel(resultSet);
		
		JTable jTable = new JTable(tableModel);
		// Thay doi kich thuoc cua cot
		jTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		jScrollPane = new JScrollPane(jTable);
		panel = new Panel();
		panel.add(jScrollPane);
		this.add(panel);
		this.show();
		
	}
	
	public static void main(String[] args) throws  ClassNotFoundException, SQLException {
		
		new JDBCDemo();
		
	}
	
}
