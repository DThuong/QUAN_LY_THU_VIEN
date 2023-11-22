package model;

public class TacGia{
	private String tacGia_id;
	private String ten_tacGia; 
	private int namSinh;
	private String ghiChu;
	
	//constructor
	public TacGia() {
	}

	public TacGia(String tacGia_id, String ten_tacGia, int namSinh, String ghiChu) {
		super();
		this.tacGia_id = tacGia_id;
		this.ten_tacGia = ten_tacGia;
		this.namSinh = namSinh;
		this.ghiChu = ghiChu;
	}
	// getter and setter
	public String getTacGia_id() {
		return tacGia_id;
	}

	public void setTacGia_id(String tacGia_id) {
		this.tacGia_id = tacGia_id;
	}

	public String getTen_tacGia() {
		return ten_tacGia;
	}

	public void setTen_tacGia(String ten_tacGia) {
		this.ten_tacGia = ten_tacGia;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	// toString
	@Override
	public String toString() {
		return "TacGia [" + (tacGia_id != null ? "tacGia_id=" + tacGia_id + ", " : "")
				+ (ten_tacGia != null ? "ten_tacGia=" + ten_tacGia + ", " : "") + "namSinh=" + namSinh + ", "
				+ (ghiChu != null ? "ghiChu=" + ghiChu : "") + "]";
	}
	

	
	
}
