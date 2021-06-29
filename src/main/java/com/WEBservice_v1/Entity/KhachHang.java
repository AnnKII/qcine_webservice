package com.WEBservice_v1.Entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="khachhang")
public class KhachHang {
	@Id
	String username;
	String pass;
	String ho;
	String ten;
	String diachi;
	String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date ngaysinh;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date ngaygianhap;

	@OneToMany(mappedBy = "khachhang", fetch = FetchType.LAZY)
	Collection<HoaDon> hoadon;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public Date getNgaygianhap() {
		return ngaygianhap;
	}

	public void setNgaygianhap(Date ngaygianhap) {
		this.ngaygianhap = ngaygianhap;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Collection<HoaDon> getHoadon() {
		return hoadon;
	}

	public void setHoadon(Collection<HoaDon> hoadon) {
		this.hoadon = hoadon;
	}
	
}
