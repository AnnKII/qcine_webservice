package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface Repository_Phong extends JpaRepository<Phong, String> {
    @Query(value="from Phong where idphong=?1")
    public Phong getPhong(String idphong);
    @Query(value="from Phong where tinhtrang = '1' ")
    public List<Phong>getActivePhong();
    @Query(value="select count(idphong) from Phong where idphong=?1")
    public int getNumberPhong(String idphong);
    @Query(value="from Phong where idphong in (select phong.idphong from Lich where ngay=?1 and (gio between ?2 and ?3))")
    public List<Phong>getUsablePhong(Date ngay, Time gio);
}
