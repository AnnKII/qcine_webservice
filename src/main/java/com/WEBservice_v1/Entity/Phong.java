package com.WEBservice_v1.Entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="phong")
public class Phong {
	
	@Id
	
	String idphong;
	
	String tenphong;
	
	int sohang;
	
	int socot;
	
	String tinhtrang;
	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	@OneToMany(mappedBy = "phong", fetch = FetchType.LAZY)
	Collection<Lich> lich;

	public String getIdphong() {
		return idphong;
	}

	public void setIdphong(String idphong) {
		this.idphong = idphong;
	}

	public String getTenphong() {
		return tenphong;
	}

	public void setTenphong(String tenphong) {
		this.tenphong = tenphong;
	}

	public int getSohang() {
		return sohang;
	}

	public void setSohang(int sohang) {
		this.sohang = sohang;
	}

	public int getSocot() {
		return socot;
	}

	public void setSocot(int socot) {
		this.socot = socot;
	}

	
}
