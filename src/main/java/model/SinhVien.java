package model;

import java.sql.Date;

public class SinhVien {
	private String msv;
	private String hoVaTen;
	private Date ngaySinh;
	private String maLop;
	private String gioiTinh;
	private String diaChi;
	private String email;
	private String phone;
	
	// Constructor
	public SinhVien() {
	}
	
	public SinhVien(String msv, String hoVaTen, Date ngaySinh, String maLop, String gioiTinh, String diaChi,
			String email, String phone) {
		this.msv = msv;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.maLop = maLop;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.email = email;
		this.phone = phone;
	}
	
	// getter and setter method
	
	public String getMsv() {
		return msv;
	}

	public void setMsv(String msv) {
		this.msv = msv;
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

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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
	
	// toString
		@Override
		public String toString() {
			return "SinhVien [msv=" + msv + ", hoVaTen=" + hoVaTen + ", ngaySinh=" + ngaySinh + ", maLop=" + maLop
					+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", email=" + email + ", phone=" + phone + "]";
		}

}
