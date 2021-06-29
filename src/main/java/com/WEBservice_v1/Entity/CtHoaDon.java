package com.WEBservice_v1.Entity;

import javax.persistence.*;

@Entity
@Table(name="cthoadon")
public class CtHoaDon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int mact;
	int hang;
	int cot;
	int gia;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mahd")
	HoaDon hoadon;
	public int getMact() {
		return mact;
	}
	public void setMact(int mact) {
		this.mact = mact;
	}

	public int getHang() {
		return hang;
	}

	public void setHang(int hang) {
		this.hang = hang;
	}

	public int getCot() {
		return cot;
	}

	public void setCot(int cot) {
		this.cot = cot;
	}

	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public HoaDon getHoadon() {
		return hoadon;
	}
	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}
	
}
