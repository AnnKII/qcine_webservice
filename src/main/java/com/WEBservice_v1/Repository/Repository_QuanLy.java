package com.WEBservice_v1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.WEBservice_v1.Entity.QuanLy;

public interface Repository_QuanLy extends JpaRepository<QuanLy, String> {
	@Query(value="from QuanLy where username=?1")
	QuanLy findByUsername(String username);
	
}
