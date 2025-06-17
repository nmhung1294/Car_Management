package facenet.car_management.controller;

import facenet.car_management.dto.XeDTO;
import facenet.car_management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    @GetMapping
    public ResponseEntity<List<XeDTO>>  getAllCars() throws  Exception{
        try {
            return ResponseEntity.ok(carService.getAllCars());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{maXe}")
    public ResponseEntity<XeDTO> getCarByCode(@PathVariable("maXe") String maXe) throws  Exception{
       try {
           return ResponseEntity.ok(carService.getCarByCode(maXe));
       } catch (Exception e) {
           logger.error(e.getMessage());
           throw new Exception(e.getMessage());
       }
    }

    @PostMapping
    public ResponseEntity<XeDTO> addCar(@RequestBody XeDTO carDTO) throws Exception {
        try {
            return ResponseEntity.ok(carService.createCar(carDTO));
        } catch (Exception e) {
            logger.error("Lỗi khi thêm xe mới", e.getMessage());
            throw new Exception("Lỗi khi thêm xe mới" + e.getMessage());
        }
    }

    @PutMapping("/{maXe}")
    public  ResponseEntity<XeDTO> updateCar(@RequestBody XeDTO carDTO, @PathVariable("maXe") String maXe) throws Exception {
        try {
            return ResponseEntity.ok(carService.updateCarByCode(maXe, carDTO));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Cập nhật không thành công, kiểm tra lại bản ghi mới " + e.getMessage());
        }
    }

    @DeleteMapping("/{maXe}")
    public String deleteCar(@PathVariable("maXe") String maXe) throws  Exception {
        try {
            carService.deleteCarByCode(maXe);
            return "ok";
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    @Autowired
    private CarService carService;

    Logger logger = LogManager.getLogger(CarController.class);
}
