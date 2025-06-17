package facenet.car_management.controller;

import facenet.car_management.dto.NhanSuDTO;
import facenet.car_management.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("api/v1/hrs")
public class HRController {
    @PostMapping
    public ResponseEntity<NhanSuDTO> createNhanSu(@RequestBody NhanSuDTO nhanSuDTO) throws  Exception {
        try{
            return ResponseEntity.ok(hrService.createNhanSu(nhanSuDTO));
        } catch(Exception e){
            logger.error("Đã có lỗi khi tạo nhân sự", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<NhanSuDTO>> getAllHRs() throws  Exception{
        try{
            return ResponseEntity.ok(hrService.getAllNhanSu());
        } catch(Exception e){
            logger.error("Đã có lỗi khi truy vấn nhân sự", e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{maNV}")
    public ResponseEntity<NhanSuDTO> getNhanSu(@PathVariable String maNV) throws Exception{
        try {
            return ResponseEntity.ok(hrService.getNhanSuById(maNV));
        } catch(Exception e){
            logger.error("Đã có lỗi khi truy vấn nhân  sự qua code", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/{maNV}")
    public ResponseEntity<NhanSuDTO> updateHR(@PathVariable String maNV, @RequestBody NhanSuDTO nhanSu) throws Exception{
        try {
            return ResponseEntity.ok(hrService.updateNhanSu(maNV, nhanSu));
        } catch (Exception e) {
            logger.error("Đã có lỗi", e.getMessage());
            throw  new Exception(e.getMessage());
        }
    }

    @DeleteMapping("/{maNV}")
    public void deleteNhanSu(@PathVariable String maNV){
        hrService.deleteNhanSu(maNV);
    }

    @Autowired
    private HRService hrService;

    Logger logger = LogManager.getLogger(HRController.class);
}
