package QuanLySinhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mysql.jdbc.Statement;

public class QuanLySinhVien extends JFrame{
	
	private MySqlHelper conn;
	private TableModel tbModel;
	private JTable table;
	private JPanel home,top,bottom,layoutInput,layoutButton;
	private JButton btnThem,btnSua,btnXoa,btnLuu,btnHuy;
	private JTextField txtMaSV,txtTenSV,txtDiaChi,txtNgaySinh,txtTenLop;
	private JLabel lblThongBao;
	private int check = 0;
	
	public static void main(String[] args) {
		new QuanLySinhVien();
	}
	
	public QuanLySinhVien(){
		
		this.setTitle("Quan Ly Sinh Vien");
		this.setBounds(0, 0, 800, 700);
		home = new JPanel(new BorderLayout());
		
		try {
			
			// Khoi tao SqlHelper
			conn = new MySqlHelper();
			// Lay ResultSet tu lop SqlHelper
			ResultSet rs = conn.getData("Select * From tblSinhVien");
			// Do du lieu rs vao Table Model, va khoi tao TableModel
			tbModel = new TableModel(rs);
			// Khoi tao table va thao tac voi table
			table = new JTable(tbModel);
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMinWidth(150);
			table.getColumnModel().getColumn(2).setMinWidth(200);
			table.getColumnModel().getColumn(3).setMinWidth(200);
			// Sua ten Colum
			// Bat su kien click vao cac item cho jtable
			//ValidateJTable listenner = new ValidateJTable(table);
			//table.getSelectionModel().addListSelectionListener(listenner);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1){
						int row = table.getSelectedRow();
						txtMaSV.setText(table.getModel().getValueAt(row,0).toString());
						txtTenSV.setText(table.getModel().getValueAt(row, 1).toString());
						txtDiaChi.setText(table.getModel().getValueAt(row, 2).toString());
						txtNgaySinh.setText(table.getModel().getValueAt(row, 3).toString());
						txtTenLop.setText(table.getModel().getValueAt(row, 4).toString());
					}
				}
				
			});
			
			JScrollPane jScrollPane = new JScrollPane(table);
			// Tao ra Panel Nam o duoi (Bottom)
			bottom = new JPanel(new BorderLayout());
			bottom.add(jScrollPane,BorderLayout.CENTER);

			// Tao ra panel nam o tren(Top)
			top = new JPanel(new FlowLayout(FlowLayout.LEFT));
			// Tao ra may cai Button
			layoutButton = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
			btnThem = new JButton("Them");
			btnSua = new JButton("Sua");
			btnXoa = new JButton("Xoa");
			btnLuu = new JButton("Luu");
			btnHuy = new JButton("Huy");
			// Listen Button
			btnThem.addActionListener(new ValidateButton());
			btnSua.addActionListener(new ValidateButton());
			btnXoa.addActionListener(new ValidateButton());
			btnLuu.addActionListener(new ValidateButton());
			btnHuy.addActionListener(new ValidateButton());
			// Add layout
			layoutButton.add(btnThem);
			layoutButton.add(btnSua);
			layoutButton.add(btnXoa);
			layoutButton.add(btnLuu);
			layoutButton.add(btnHuy);
			// Tao ra may o de input du lieu
			txtMaSV = new JTextField("",15);
			txtTenSV = new JTextField("");
			txtDiaChi = new JTextField("");
			txtNgaySinh = new JTextField("");
			txtTenLop = new JTextField("");
			// Them label va textField vao layoutInput
			layoutInput = new JPanel(new GridLayout(0, 2));
			layoutInput.add(new Label("Ma SV"));
			layoutInput.add(txtMaSV);
			layoutInput.add(new Label("Ten Sinh Vien"));
			layoutInput.add(txtTenSV);
			layoutInput.add(new Label("Dia Chi"));
			layoutInput.add(txtDiaChi);
			layoutInput.add(new Label("Ngay Sinh"));
			layoutInput.add(txtNgaySinh);
			layoutInput.add(new Label("Ten Lop"));
			layoutInput.add(txtTenLop);

			// Thong bao
			lblThongBao = new JLabel("");
			lblThongBao.setForeground(Color.RED);
			// Add top
			top.add(layoutInput);
			top.add(layoutButton);
			
			
			// Add home
			home.add(top, BorderLayout.NORTH);
			home.add(bottom,BorderLayout.SOUTH);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Cap nhat trang thai component
		changeComponent(0);
		// Add frame
		this.add(home);
		this.pack();
		this.setVisible(true);
	}
	
	// Lang nghe xu kien cho button
	class ValidateButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnThem){
				changeComponent(1);
				check = 1;
			}
			if(e.getSource() == btnSua){
				changeComponent(1);
				check = 2;
			}
			if(e.getSource() == btnXoa){
				changeComponent(3);
				check = 3;
				int select = JOptionPane.showConfirmDialog(QuanLySinhVien.this, "Ban co muon xoa phai khong ?");
				if(select == 0){
					int MaSV = Integer.parseInt(txtMaSV.getText().toString());
					conn.deleteDB(MaSV);
					updateTable();
				}

			}
			if(e.getSource() == btnLuu){
				excuteDB();
				updateTable();
			}
			if(e.getSource() == btnHuy){
				changeComponent(2);
				check = 0;
			}
		}
		
	}
	
	
	
	/**
	 * 					Thuc hien thao tac MySql
	 */
	public void excuteDB(){
		// Them
		if(check == 1){
			SinhVien sv = new SinhVien();
			sv.setMaSV(Integer.parseInt(txtMaSV.getText().toString()));
			sv.setTenSV(txtTenSV.getText().toString());
			sv.setDiaChi(txtDiaChi.getText().toString());
			sv.setNgaySinh(txtNgaySinh.getText().toString());
			sv.setTenLop(txtTenLop.getText().toString());
			conn.insertDB(sv);
		}
		// Sua
		if(check == 2){
			SinhVien sv = new SinhVien();
			sv.setMaSV(Integer.parseInt(txtMaSV.getText().toString()));
			sv.setTenSV(txtTenSV.getText().toString());
			sv.setDiaChi(txtDiaChi.getText().toString());
			sv.setNgaySinh(txtNgaySinh.getText().toString());
			sv.setTenLop(txtTenLop.getText().toString());
			conn.updateDB(sv);
		}
		// Xoa 
		if(check == 3){
			int MaSV = Integer.parseInt(txtMaSV.getText().toString());
			conn.deleteDB(MaSV);
		}

	}
	
	/**
	 * 					Update Frame lai moi khi thuc hien update csdl
	 */
	public void updateTable(){
		ResultSet rs = conn.getData("Select * From tblSinhVien");
		try {
			TableModel model = new TableModel(rs);
			table.setModel(model);
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMinWidth(150);
			table.getColumnModel().getColumn(2).setMinWidth(200);
			table.getColumnModel().getColumn(3).setMinWidth(200);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 					Thay doi trang thai cac component
	 * @param i			Chuyen vao mot bien i ={0,1,2,3} de dieu khien
	 */
	public void changeComponent(int i){
		// i=0 Moi bat Frame
		if(i == 0){
			txtMaSV.setEditable(false);
			txtTenSV.setEditable(false);
			txtDiaChi.setEditable(false);
			txtNgaySinh.setEditable(false);
			txtTenLop.setEditable(false);
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);
		}
		// i=1 Khi bam nut them
		if(i == 1){
			txtMaSV.setEditable(true);
			txtMaSV.requestFocusInWindow();
			txtTenSV.setEditable(true);
			txtDiaChi.setEditable(true);
			txtNgaySinh.setEditable(true);
			txtTenLop.setEditable(true);
			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
		// i=2 Luu
		if(i == 2){
			 
			removeText();
			txtMaSV.setEditable(false);
			txtTenSV.setEditable(false);
			txtDiaChi.setEditable(false);
			txtNgaySinh.setEditable(false);
			txtTenLop.setEditable(false);
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		}
		// i=3 Xoa
		if(i == 3){
			txtMaSV.setEditable(true);
			txtTenSV.setEditable(true);
			txtDiaChi.setEditable(true);
			txtNgaySinh.setEditable(true);
			txtTenLop.setEditable(true);
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(true);
		}
	}
	/**
	 * 					Xoa du lieu o TextField
	 */
	public void removeText(){
		txtMaSV.setText("");
		txtTenSV.setText("");
		txtDiaChi.setText("");
		txtNgaySinh.setText("");
		txtTenLop.setText("");
	}
	
}
