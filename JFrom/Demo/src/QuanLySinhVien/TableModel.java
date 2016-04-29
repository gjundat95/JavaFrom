package QuanLySinhVien;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class TableModel extends AbstractTableModel{
	
	Vector tblData;
	Vector colName;
	
	public TableModel(ResultSet rs) throws SQLException {
		
		ResultSetMetaData rsData = rs.getMetaData();
		int colCount = rsData.getColumnCount();
		
		colName = new Vector(colCount);
		tblData = new Vector();
		// Them du lieu vao cac o col
		for(int i = 1; i <= colCount; i++){
			colName.addElement(rsData.getColumnName(i));
		}
		
		while(rs.next()){
			
			Vector vector = new Vector();
			// Them du lieu vao mot dong
			for(int i = 1; i <= colCount; i++){
				vector.addElement(rs.getObject(i));
			}
			// Add dong do vao tblData
			tblData.addElement(vector);
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
		return (String)colName.elementAt(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector row = (Vector)tblData.elementAt(rowIndex);
		return row.elementAt(columnIndex);
	}
	

}
