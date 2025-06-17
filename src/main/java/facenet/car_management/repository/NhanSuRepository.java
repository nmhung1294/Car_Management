package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.NhanSu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NhanSuRepository extends JpaRepository<NhanSu, String> {
    @Query("SELECT hr FROM NhanSu hr WHERE hr.maNV = :maNV")
    public NhanSu findByMaNV(@Param("maNV") String maNV);

    @Modifying
    @Transactional
    @Query("DELETE FROM NhanSu hr WHERE hr.maNV = :maNV")
    public void deleteByMaNV(@Param("maNV") String maNV);

    @Query("SELECT hr FROM NhanSu hr")
    public List<NhanSu> getAll();
}
