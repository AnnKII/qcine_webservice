package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.AuthorKey;
import com.WEBservice_v1.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Repository_AuthorKey extends JpaRepository<AuthorKey, Integer> {
    @Query(value="SELECT count(id) from AuthorKey ")
    public int getCount();

    @Query(value="select secretkey from AuthorKey")
    public String getSecrectKey();

}
