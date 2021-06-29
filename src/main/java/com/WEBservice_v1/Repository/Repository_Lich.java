package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.CtHoaDon;
import com.WEBservice_v1.Entity.Lich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface Repository_Lich extends JpaRepository<Lich, Integer> {
    @Query(value = "from Lich where malich=?1 order by ngay,gio")
    public Lich getLich(int malich);

    @Query(value = "select count(malich) from Lich where malich=?1 order by ngay,gio")
    public int getNumberLich(int malich);

    @Query(value="from Lich where ngay>=current_date  and phim.state=1 order by ngay,gio")
    public List<Lich> getCurrentLich();

    @Query(value="from Lich where phim.idphim=?1 and phim.state=1 order by ngay, gio")
    public List<Lich> getLichFollowedByPhim(String idphim);

    @Query(value="from Lich where phim.idphim=?1 and phim.state=1 and ngay=current_date order by gio")
    public List<Lich> getTodayLichFollowedByPhim(String idphim);

    @Query(value="from Lich where ngay=?1 order by gio")
    public List<Lich> getLichFollowedByNgay(Date ngay);

    @Query(value="from Lich where ngay=?1 and phim .idphim=?2 order by gio")
    public List<Lich> getLichFollowedByDateAndPhim(Date ngay, String idphim);

//    @Query(value="from CtHoaDon where HoaDon .mahd = (select HoaDon .mahd from Lich where malich=?1 )")
//    public List<CtHoaDon> getListCtHoaDonFollowedByLich(int malich);
}
