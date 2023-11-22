package model;

public class KeSach {
	private TaiKhoan taikhoan;
	private Book sach;
	
	public KeSach() {
	}
	public KeSach(TaiKhoan taikhoan, Book sach) {
		this.taikhoan = taikhoan;
		this.sach = sach;
	}
	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
	public Book getSach() {
		return sach;
	}
	public void setSach(Book sach) {
		this.sach = sach;
	}
	
	
	
}
