package facenet.car_management.controller;
import facenet.car_management.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {

    @PostMapping("/brands/{hangXe}")
    public ResponseEntity<List<String>> uploadBrandImg(@PathVariable String hangXe,
                                                 @RequestParam("file") MultipartFile[] files) throws IOException {

        try{
            logger.info("Lưu các ảnh thương hiệu");
           return ResponseEntity.ok(imageService.saveBrandImg(hangXe, files));
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/faces/{maNV}")
    public ResponseEntity<String> uploadEmpImg(@PathVariable String maNV, @RequestParam("file") MultipartFile file) throws IOException {
        try{
            logger.info("Lưu các ảnh của người của showroom");
            return ResponseEntity.ok(imageService.saveHrImg(maNV, file));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("cars/{maXe}")
    public ResponseEntity<List<String>> uploadCarImg(@PathVariable String maXe, @RequestParam("file") MultipartFile[] files) throws IOException {
        try{
            logger.info("Lưu các ảnh của xe");
            return ResponseEntity.ok(imageService.saveCarImg(maXe, files));
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/brands/{hangXe}")
    public ResponseEntity<List<String>> getBrandImg(@PathVariable String hangXe){
        return ResponseEntity.ok(imageService.getAllBrandImg(hangXe));
    }
    @GetMapping("/faces/{maNV}")
    public ResponseEntity<String> getFacImg(@PathVariable String maNV){
       return ResponseEntity.ok(imageService.getHRImg(maNV));
    }

    @GetMapping("/cars/{maXe}")
    public ResponseEntity<List<String>> getCarImg(@PathVariable String maXe){
        return ResponseEntity.ok(imageService.getAllCarImg(maXe));
    }

    @Autowired
    private ImageService imageService;
    private final Logger logger = LogManager.getLogger(ImageController.class);

}
