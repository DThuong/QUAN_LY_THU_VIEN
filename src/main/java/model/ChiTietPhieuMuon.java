package model;

public class ChiTietPhieuMuon {
	private String ctpm_id;
	private PhieuMuon phieu;
	private Book sach;
	private int soLuong;
	
	//constructor
	public ChiTietPhieuMuon() {
	}
	public ChiTietPhieuMuon(String ctpm_id, PhieuMuon phieu, Book sach, int soLuong) {
		this.ctpm_id = ctpm_id;
		this.phieu = phieu;
		this.sach = sach;
		this.soLuong = soLuong;
	}
	//getter and setter
	public String getCtpm_id() {
		return ctpm_id;
	}
	public void setCtpm_id(String ctpm_id) {
		this.ctpm_id = ctpm_id;
	}
	public PhieuMuon getPhieu() {
		return phieu;
	}
	public void setPhieu(PhieuMuon phieu) {
		this.phieu = phieu;
	}
	public Book getSach() {
		return sach;
	}
	public void setSach(Book sach) {
		this.sach = sach;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	//toString
	@Override
	public String toString() {
		return "ChiTietPhieuMuon [" + (ctpm_id != null ? "ctpm_id=" + ctpm_id + ", " : "")
				+ (phieu != null ? "phieu=" + phieu + ", " : "") + (sach != null ? "sach=" + sach + ", " : "")
				+ "soLuong=" + soLuong + "]";
	}
	
	
}
