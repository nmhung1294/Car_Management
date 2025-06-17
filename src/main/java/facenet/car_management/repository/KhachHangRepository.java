package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    @Query("SELECT kh from KhachHang kh WHERE kh.maKhachHang= :maKhachHang")
    public KhachHang getCustomerByCode(@Param("maKhachHang") String maKhachHang);

    @Query("SELECT kh from KhachHang kh")
    public List<KhachHang> getCustomers();

}
