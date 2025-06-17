package facenet.car_management.controller;

import facenet.car_management.dto.XeDTO;
import facenet.car_management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<XeDTO>>  getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{maXe}")
    public ResponseEntity<XeDTO> getCarByCode(@PathVariable("maXe") String maXe){
        return ResponseEntity.ok(carService.getCarByCode(maXe));
    }

    @PostMapping
    public ResponseEntity<XeDTO> addCar(@RequestBody XeDTO carDTO){
        return ResponseEntity.ok(carService.createCar(carDTO));
    }

    @PutMapping("/{maXe}")
    public  ResponseEntity<XeDTO> updateCar(@RequestBody XeDTO carDTO, @PathVariable("maXe") String maXe){
        return ResponseEntity.ok(carService.updateCarByCode(maXe, carDTO));
    }

    @DeleteMapping("/{maXe}")
    public String deleteCar(@PathVariable("maXe") String maXe){
        try {
            carService.deleteCarByCode(maXe);
            return "ok";
        }
        catch (Exception e){
            return e.getMessage();
        }

    }
}
