package RanSanMoi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class BestCore {

	public String URL;	

	public BestCore(String uRL) {
		super();
		URL = uRL;
	}

	public  String getURL() {
		return URL;
	}

	public  void setURL(String uRL) {
		URL = uRL;
	}

	public void LuuDiemCao(NguoiChoi nc){
		final int n = 5;
		
		DiemCao diemCao = new  DiemCao();
		diemCao.setListDiem(LayDiem());
		System.out.println("Size: "+diemCao.listDiem.size());
		if(diemCao.getListDiem().size() < n){
			diemCao.addDiem(nc);
		}
		else{
			if(diemCao.listDiem.get(0).diem < nc.diem){
				diemCao.listDiem.set(0, nc);
				LuuDiem(diemCao);
			}
		}
	}
	
	public void LuuDiem(DiemCao diemcao){
		// Xắp xếp điểm theo thứ tự giảm dần
		diemcao.listDiem.sort(new Comparator<NguoiChoi>() {
			@Override
			public int compare(NguoiChoi o1, NguoiChoi o2) {
				// TODO Auto-generated method stub
				return o1.diem - o2.diem;
			}
			
		});
		
		try {
			FileOutputStream fos = new FileOutputStream(URL);
			ObjectOutputStream objStream = new ObjectOutputStream(fos);
			objStream.writeObject(diemcao);
			objStream.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NguoiChoi> LayDiem(){
		DiemCao diemcao = new DiemCao();
		try {
			FileInputStream fis = new FileInputStream(URL);
			ObjectInputStream objStream = new ObjectInputStream(fis);
			diemcao = (DiemCao)objStream.readObject();
			objStream.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(NguoiChoi dc : diemcao.getListDiem()){
			
			System.out.println("Ten: "+dc.ten+"\nDiem: "+dc.diem);
		}
		
		return diemcao.listDiem;
	}
}

class DiemCao implements Serializable{
	
	ArrayList<NguoiChoi> listDiem = new ArrayList<NguoiChoi>();

	public ArrayList<NguoiChoi> getListDiem() {
		return listDiem;
	}

	public void addDiem(NguoiChoi listDiem) {
		this.listDiem.add(listDiem);
	}

	public void setListDiem(ArrayList<NguoiChoi> listDiem) {
		this.listDiem = listDiem;
	}
}

class NguoiChoi implements Serializable{
	
	int diem;
	String ten;
	
	public NguoiChoi(int diem, String ten) {
		super();
		this.diem = diem;
		this.ten = ten;
	}
	public int getDiem() {
		return diem;
	}
	public void setDiem(int diem) {
		this.diem = diem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
}

