package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.HoaDon;
import com.WEBservice_v1.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface Repository_HoaDon extends JpaRepository<HoaDon, Integer> {

    @Query(value="from HoaDon where mahd=?1")
    public HoaDon getHoaDon(int mahd);

    @Query(value="select count(mahd) from HoaDon where mahd=?1")
    public int getNumberHoaDon(int mahd);

    @Query(value="from HoaDon where khachhang.username=?1 order by mahd desc ")
    public List<HoaDon> getHoaDonByUser(String username);

    @Query(value="select mahd from HoaDon  where lich.malich =?1" +
            " and khachhang.username=?2" +
            " and tglap=?3" +
            " and thanhtien =?4")
    public List<Integer> getMaHDCreated(int malich, String username, Date tglap, int thanhTien);

    @Query(value="from HoaDon where khachhang.username=?1")
    public List<HoaDon> getHoaDonFollowedByKhachhang(String username);


}
