package facenet.car_management.controller;

import facenet.car_management.dto.DonHangDTO;
import facenet.car_management.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")

public class OrderController {

    @PostMapping
    public ResponseEntity<DonHangDTO> createOrder(@RequestBody DonHangDTO donHangDTO) {
        return ResponseEntity.ok(orderService.createOrder(donHangDTO));
    }

    @PutMapping("/{maDonHang}")
    public DonHangDTO updateOrder(@PathVariable String maDonHang, @RequestBody DonHangDTO donHangDTO) {
        return orderService.updateOrder(maDonHang, donHangDTO);
    }

    @GetMapping
    public List<DonHangDTO> getOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/status={status}")
    public List<DonHangDTO> getOrderStatus(@PathVariable Integer status) {
        return orderService.getOrdersByStatus(status);
    }

    @Autowired
    DonHangService orderService;
}
