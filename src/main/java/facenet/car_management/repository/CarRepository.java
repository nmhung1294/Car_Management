package facenet.car_management.repository;

import facenet.car_management.facenet_car_db.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository extends JpaRepository<Xe, String> {
    @Query("SELECT xe FROM Xe xe WHERE xe.maXe = :maXe")
    public Xe findCarByCode(@Param("maXe") String maXe);

    @Query("SELECT xe FROM Xe xe")
    public List<Xe> getAllCar();

    @Modifying
    @Transactional
    @Query("DELETE FROM Xe xe WHERE xe.maXe= :maXe")
    public void deleteCarByCode(@Param("maXe") String maXe);
}
