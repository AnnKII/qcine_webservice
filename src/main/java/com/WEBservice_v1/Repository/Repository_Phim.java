package com.WEBservice_v1.Repository;

import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.Entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository_Phim extends JpaRepository<Phim, String> {
 /*   @Query(value="from Phim where idphim = (select idphim from lich)")
    public List<Phim> getCurrentPhim();*/
    @Query(value="from Phim where idphim = ?1")
    public Phim getPhim(String id);

    @Query(value="select count (idphim) from Phim where idphim=?1")
    public int getNumberPhim(String id);

    @Query(value="from Phim where state=1")
    public List<Phim> getCurrentPhim();

    @Query(value="from Phim where state=2")
    public List<Phim> getFuturePhim();
}
