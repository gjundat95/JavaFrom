package QuanLySinhVien;

public class SinhVien {
	private int MaSV;
	private String TenSV;
	private String DiaChi;
	private String NgaySinh;
	private String TenLop;
	
	public SinhVien(){}
	public SinhVien(int maSV, String tenSV, String diaChi, String ngaySinh, String tenLop) {
		super();
		MaSV = maSV;
		TenSV = tenSV;
		DiaChi = diaChi;
		NgaySinh = ngaySinh;
		TenLop = tenLop;
	}

	public int getMaSV() {
		return MaSV;
	}

	public void setMaSV(int maSV) {
		MaSV = maSV;
	}

	public String getTenSV() {
		return TenSV;
	}

	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	
}
