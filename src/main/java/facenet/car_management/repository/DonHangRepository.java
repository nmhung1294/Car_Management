package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, String> {
    @Query("SELECT orders FROM DonHang orders ORDER BY orders.ngay DESC")
    public List<DonHang> getAllOrders();

    @Query("SELECT orders FROM DonHang orders WHERE orders.status = :status ORDER BY orders.ngay")
    public List<DonHang> getAllOrdersByStatus(@Param("status") Integer status);

    @Query("SELECT orders FROM DonHang orders WHERE orders.maDonHang = :maDonHang")
    public DonHang getOrdersByID(@Param("maDonHang") String maDonHang);

}
