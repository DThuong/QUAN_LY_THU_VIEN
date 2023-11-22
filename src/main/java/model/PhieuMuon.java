package model;

import java.sql.Date;

public class PhieuMuon {
	private String phieu_id;
	private SinhVien sinhVien;
	private Date ngayMuon;
	private Date hanTra;
	private Date ngayTra;
	
	//constructor
	public PhieuMuon() {
	}
	public PhieuMuon(String phieu_id, SinhVien sinhVien, Date ngayMuon, Date hanTra, Date ngayTra) {
		this.phieu_id = phieu_id;
		this.sinhVien = sinhVien;
		this.ngayMuon = ngayMuon;
		this.hanTra = hanTra;
		this.ngayTra = ngayTra;
	}
	//getter and setter
	public String getPhieu_id() {
		return phieu_id;
	}
	public void setPhieu_id(String phieu_id) {
		this.phieu_id = phieu_id;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	public Date getNgayMuon() {
		return ngayMuon;
	}
	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}
	public Date getHanTra() {
		return hanTra;
	}
	public void setHanTra(Date hanTra) {
		this.hanTra = hanTra;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	//toString
	@Override
	public String toString() {
		return "PhieuMuon [" + (phieu_id != null ? "phieu_id=" + phieu_id + ", " : "")
				+ (sinhVien != null ? "sinhVien=" + sinhVien + ", " : "")
				+ (ngayMuon != null ? "ngayMuon=" + ngayMuon + ", " : "")
				+ (hanTra != null ? "hanTra=" + hanTra + ", " : "") + (ngayTra != null ? "ngayTra=" + ngayTra : "")
				+ "]";
	}
	
}
