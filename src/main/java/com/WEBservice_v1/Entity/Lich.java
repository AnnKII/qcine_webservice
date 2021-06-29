package com.WEBservice_v1.Entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="lich")
public class Lich {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int malich;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date ngay;

	Time gio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idphim")
	Phim phim;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idphong")
	Phong phong;

	public int getMalich() {
		return malich;
	}

	public void setMalich(int malich) {
		this.malich = malich;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public Time getGio() {
		return gio;
	}

	public void setGio(Time gio) {
		this.gio = gio;
	}

	public Phim getPhim() {
		return phim;
	}

	public void setPhim(Phim phim) {
		this.phim = phim;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	
}
