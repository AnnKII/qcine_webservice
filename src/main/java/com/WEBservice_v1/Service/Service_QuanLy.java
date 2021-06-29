package com.WEBservice_v1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WEBservice_v1.Entity.QuanLy;
import com.WEBservice_v1.Repository.Repository_QuanLy;

@Service
public class Service_QuanLy {
	@Autowired
	Repository_QuanLy ResQuanLy;
	
	public List<QuanLy> getAllQuanLy(){
		List<QuanLy> result = new ArrayList<>();
		result = ResQuanLy.findAll();
		return result;
	}
	
	public QuanLy getQuanLy(String username) {
		QuanLy result = new QuanLy();
		result = ResQuanLy.findByUsername(username);
		return result;
	}
	public QuanLy saveQuanLy(QuanLy quanly) {
		QuanLy result = quanly;
		ResQuanLy.save(quanly);
		return result;
	}
	public String deleteQuanLy(String id){
		ResQuanLy.delete(id);
		String result = "SUCCESS";
		return result;

	}
}
