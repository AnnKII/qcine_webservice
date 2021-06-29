package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.CtHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository_CtHoaDon extends JpaRepository<CtHoaDon, Integer> {
    @Query(value="from CtHoaDon where mact=?1")
    public CtHoaDon getCT(int mact);

    @Query(value="select count(mact) from CtHoaDon where mact=?1")
    public int getNumberCt(int mact);

    @Query(value = "from CtHoaDon where hoadon.mahd=?1")
    public List<CtHoaDon> getCtHoaDonByMaHD(int mahd);

    @Query(value ="from CtHoaDon where hoadon.lich.malich=?1")
    public List<CtHoaDon>getCtHoaDonFollowedByLich(int malich);
}
