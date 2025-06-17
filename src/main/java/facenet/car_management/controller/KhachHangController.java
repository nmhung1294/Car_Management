package facenet.car_management.controller;

import facenet.car_management.dto.KhachHangDTO;
import facenet.car_management.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class KhachHangController {
    @Autowired
    private KhachHangService customerService;

    @GetMapping
    public ResponseEntity<List<KhachHangDTO>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping
    public ResponseEntity<KhachHangDTO> createCustomer(@RequestBody KhachHangDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping("/{maKhachHang}")
    public ResponseEntity<KhachHangDTO> getCustomerById(@PathVariable String maKhachHang) {
        return ResponseEntity.ok(customerService.getCustomerByCode(maKhachHang));
    }

    @PutMapping("/{maKhachHang}")
    public ResponseEntity<KhachHangDTO> updateCustomer(@RequestBody KhachHangDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(customerDTO));
    }
}

