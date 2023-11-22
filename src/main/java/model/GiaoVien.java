package model;

import java.sql.Date;

public class GiaoVien {
	private String giaoVien_id;
	private String hoVaTen;
	private Date ngaySinh;
	private String gioiTinh;
	private String email;
	private String phone;
	
	//constructor
	public GiaoVien() {
	}
	public GiaoVien(String giaoVien_id, String hoVaTen, Date ngaySinh, String gioiTinh, String email, String phone) {
		this.giaoVien_id = giaoVien_id;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.phone = phone;
	}
	
	//getter and setter
	public String getGiaoVien_id() {
		return giaoVien_id;
	}
	public void setGiaoVien_id(String giaoVien_id) {
		this.giaoVien_id = giaoVien_id;
	}
	public String getHoVaTen() {
		return hoVaTen;
	}
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//toString
	@Override
	public String toString() {
		return "GiaoVien [" + (giaoVien_id != null ? "giaoVien_id=" + giaoVien_id + ", " : "")
				+ (hoVaTen != null ? "hoVaTen=" + hoVaTen + ", " : "")
				+ (ngaySinh != null ? "ngaySinh=" + ngaySinh + ", " : "")
				+ (gioiTinh != null ? "gioiTinh=" + gioiTinh : "") + "]";
	}
	
	
}
