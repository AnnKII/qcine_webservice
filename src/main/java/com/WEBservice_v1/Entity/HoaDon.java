package com.WEBservice_v1.Entity;

import java.util.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="hoadon")
public class HoaDon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int mahd;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date tglap;
	int thanhtien;
	
	@ManyToOne
	@JoinColumn(name="username")
	KhachHang khachhang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="malich")
	Lich lich;

	@OneToMany(mappedBy = "hoadon", fetch = FetchType.LAZY)
	Collection<HoaDon> hoadon;
	public int getMahd() {
		return mahd;
	}

	public void setMahd(int mahd) {
		this.mahd = mahd;
	}

	public Date getTglap() {
		return tglap;
	}

	public void setTglap(Date tglap) {
		this.tglap = tglap;
	}

	public int getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien(int thanhtien) {
		this.thanhtien = thanhtien;
	}

	public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}

	public Lich getLich() {
		return lich;
	}

	public void setLich(Lich lich) {
		this.lich = lich;
	}
	
}
