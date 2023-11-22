package model;

import java.util.Objects;

public class Book {
	private String book_id;
	private String tenSach;
	private TacGia tacGia;
	private String nhaXuatBan;
	private int namXuatBan;
	private int soLuongTrenThuVien;
	private int soLuongDaMuon;
	private String moTa;
	private TheLoai theLoai;
	private String link_img;
	//constructor method
	public Book() {
	}

	public Book(String book_id, String tenSach, TacGia tacGia, String nhaXuatBan, int namXuatBan,
			int soLuongTrenThuVien, int soLuongDaMuon, String moTa, TheLoai theLoai) {
		this.book_id = book_id;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.namXuatBan = namXuatBan;
		this.soLuongTrenThuVien = soLuongTrenThuVien;
		this.soLuongDaMuon = soLuongDaMuon;
		this.moTa = moTa;
		this.theLoai = theLoai;
	}
	

	public Book(String book_id, String tenSach, TacGia tacGia, String nhaXuatBan, int namXuatBan,
			int soLuongTrenThuVien, int soLuongDaMuon, String moTa, TheLoai theLoai, String link_img) {
		this.book_id = book_id;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.namXuatBan = namXuatBan;
		this.soLuongTrenThuVien = soLuongTrenThuVien;
		this.soLuongDaMuon = soLuongDaMuon;
		this.moTa = moTa;
		this.theLoai = theLoai;
		this.link_img = link_img;
	}

	// getter and setter
	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public TacGia getTacGia() {
		return tacGia;
	}

	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public int getSoLuongTrenThuVien() {
		return soLuongTrenThuVien;
	}

	public void setSoLuongTrenThuVien(int soLuongTrenThuVien) {
		this.soLuongTrenThuVien = soLuongTrenThuVien;
	}

	public int getSoLuongDaMuon() {
		return soLuongDaMuon;
	}

	public void setSoLuongDaMuon(int soLuongDaMuon) {
		this.soLuongDaMuon = soLuongDaMuon;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public TheLoai getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}
	
	public String getTenSach() {
		return tenSach;
	}


	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getLink_img() {
		return link_img;
	}

	public void setLink_img(String link_img) {
		this.link_img = link_img;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", tenSach=" + tenSach + ", tacGia=" + tacGia + ", nhaXuatBan=" + nhaXuatBan
				+ ", namXuatBan=" + namXuatBan + ", soLuongTrenThuVien=" + soLuongTrenThuVien + ", soLuongDaMuon="
				+ soLuongDaMuon + ", moTa=" + moTa + ", theLoai=" + theLoai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(book_id, other.book_id);
	}
	
	
	
}
