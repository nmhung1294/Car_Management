package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.CSKH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CskhRepository extends JpaRepository<CSKH, Integer> {
    @Query("SELECT cusCare.ngay, cusCare.maDonHang.maDonHang, donHang.maXe.maXe, khachHang.name, khachHang.phoneNum, khachHang.email " +
        "FROM CSKH cusCare " +
        "INNER JOIN DonHang donHang ON cusCare.maDonHang = donHang " +
        "INNER JOIN KhachHang khachHang ON cusCare.maKhachHang = donHang.maKhachHang " +
        "WHERE cusCare.ngay IS NOT NULL AND cusCare.ngay <= :sixMonthsAgo " +
        "AND cusCare.maDonHang.maDonHang IN (" +
        "    SELECT cusCare2.maDonHang.maDonHang FROM CSKH cusCare2 " +
        "    GROUP BY cusCare2.maDonHang.maDonHang " +
        "    HAVING COUNT(cusCare2.maDonHang.maDonHang) <= 5" +
        ") " +
        "ORDER BY khachHang.name")
    List<Object[]> getCskh(@Param("sixMonthsAgo") Date sixMonthsAgo);


}
