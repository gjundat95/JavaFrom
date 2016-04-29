package JDBCDemo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class TableModel extends AbstractTableModel{

	Vector tblData;
	Vector colName;
	
	
	public TableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData rsmData = rs.getMetaData();
		int col = rsmData.getColumnCount();
		colName = new Vector(col);
		
		tblData = new Vector();
		
		for(int i = 1; i <= col; i++){
			// Set Name Colnum
			colName.addElement(rsmData.getColumnName(i));
		}
		
		while(rs.next()){
			Vector row = new Vector();
			for(int j = 1; j <= col;j++){
				row.addElement(rs.getObject(j));
			}
				// Add row to table
			tblData.addElement(row);
			
		}
		
	}

	@Override
	public int getRowCount() {
		return tblData.size();
	}

	@Override
	public int getColumnCount() {
		return colName.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)colName.elementAt(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Vector row = (Vector)tblData.elementAt(rowIndex);
		return row.elementAt(columnIndex);
	}

	
	
	

}
