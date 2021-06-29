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
@Table(name="phim")
public class Phim {

	@Id
	
	String idphim;
	
	String tenphim;
	
	String mota;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date ngaykc;
	
	int thoiluong;

	String anh;
	
	String trailer;
	int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@OneToMany(mappedBy = "phim", fetch = FetchType.LAZY)
	Collection<Lich> lich ;

	public String getIdphim() {
		return idphim;
	}

	public void setIdphim(String idphim) {
		this.idphim = idphim;
	}

	public String getTenphim() {
		return tenphim;
	}

	public void setTenphim(String tenphim) {
		this.tenphim = tenphim;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Date getNgaykc() {
		return ngaykc;
	}

	public void setNgaykc(Date ngaykc) {
		this.ngaykc = ngaykc;
	}

	public int getThoiluong() {
		return thoiluong;
	}

	public void setThoiluong(int thoiluong) {
		this.thoiluong = thoiluong;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	
	
}
