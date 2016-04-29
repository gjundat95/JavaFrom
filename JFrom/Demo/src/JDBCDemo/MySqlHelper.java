package JDBCDemo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MySqlHelper {
	public Connection conn;
	public MySqlHelper() throws ClassNotFoundException {
		
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// Dùng cái này nếu là sqlserver của microsoft
		// Nap drive cho ket noi mysql
		Class.forName("com.mysql.jdbc.Driver");
		// Duong dan ket noi csdl mysql
		String url = "jdbc:mysql://127.0.0.1:3306/test";
        // String Url = "jdbc:odbc:" + dataBaseName;
		// Dùng cái này nếu là SQLServer
		try{
			conn = (Connection) DriverManager.getConnection(url,"root","");
			System.out.println("Ket noi thanh cong.");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 						Function get data
	 * @param _query 		String MySql
	 * @return				ResultSet
	 */
	public ResultSet getData(String _query){
		ResultSet rs = null;
		try {
			Statement statement = (Statement)conn.createStatement();
			rs = statement.executeQuery(_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}
	// Insert to database
	public void insertRowToTable(String MaSinhVien,String TenSinhVien,String NgaySinh, String QueQuan ){
		
	
		String insertQuery = "insert into tblSinhVien";
		
	}
	// Close Connection
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
