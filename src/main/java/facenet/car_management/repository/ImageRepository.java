package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.Image;
import facenet.car_management.facenet_car_db.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    @Query(value="SELECT img.path FROM Image img WHERE img.thuongHieu.hangXe = :hangXe")
    public List<String> findBrandImgByName( @Param("hangXe") String hangXe);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM Image img WHERE img.nhanSu.maNV = :maNV")
    public void deleteByMaNV(@Param("maNV") String maNV);

    @Query("SELECT img.path FROM Image img WHERE img.maXe.maXe= :maXe")
    public List<String> findCarImgByCode(@Param("maXe") String maXe);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image img WHERE img.maXe.maXe = :maXe")
    public void deleteCarImgByCode(@Param("maXe") String maXe);

    @Query("SELECT img.path FROM Image img WHERE img.nhanSu.maNV= :maNV")
    public String getHrImgByCode(@Param("maNV") String maNV);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image img WHERE img.nhanSu.maNV = :maNV")
    public void deleteHrImgByCode(@Param("maNV") String maNV);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image img WHERE img.thuongHieu.hangXe = :hangXe")
    public void deleteBrandImgByCode(@Param("hangXe") String hangXe);

}
