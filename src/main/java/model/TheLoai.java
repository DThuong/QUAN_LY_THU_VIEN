package model;

public class TheLoai {
	private String theLoai_id;
	private String tenTheLoai;
	
	//constructor
	public TheLoai() {
	}
	public TheLoai(String theLoai_id, String tenTheLoai) {
		this.theLoai_id = theLoai_id;
		this.tenTheLoai = tenTheLoai;
	}
	//getter and setter
	public String getTheLoai_id() {
		return theLoai_id;
	}
	public void setTheLoai_id(String theLoai_id) {
		this.theLoai_id = theLoai_id;
	}
	public String getTenTheLoai() {
		return tenTheLoai;
	}
	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}
	
	//toString
	@Override
	public String toString() {
		return "TheLoai [" + (theLoai_id != null ? "theLoai_id=" + theLoai_id + ", " : "")
				+ (tenTheLoai != null ? "tenTheLoai=" + tenTheLoai : "") + "]";
	}
	
}
