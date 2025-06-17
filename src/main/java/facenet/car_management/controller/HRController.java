package facenet.car_management.controller;

import facenet.car_management.dto.NhanSuDTO;
import facenet.car_management.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrs")
public class HRController {
    @Autowired
    private HRService hrService;

    @PostMapping
    public ResponseEntity<NhanSuDTO> createNhanSu(@RequestBody NhanSuDTO nhanSuDTO) {
        return ResponseEntity.ok(hrService.createNhanSu(nhanSuDTO));
    }
    @GetMapping
    public ResponseEntity<List<NhanSuDTO>> getAllHRs(){
        return ResponseEntity.ok(hrService.getAllNhanSu());
    }

    @GetMapping("/{maNV}")
    public ResponseEntity<NhanSuDTO> getNhanSu(@PathVariable String maNV){
        return ResponseEntity.ok(hrService.getNhanSuById(maNV));
    }

    @PutMapping("/{maNV}")
    public ResponseEntity<NhanSuDTO> updateHR(@PathVariable String maNV, @RequestBody NhanSuDTO nhanSu){
        return ResponseEntity.ok(hrService.updateNhanSu(maNV, nhanSu));
    }

    @DeleteMapping("/{maNV}")
    public void deleteNhanSu(@PathVariable String maNV){
        hrService.deleteNhanSu(maNV);
    }
}
