package facenet.car_management.controller;

import facenet.car_management.dto.ThuongHieuDTO;
import facenet.car_management.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
public class ThuongHieuController {
    /**
     * Tạo bản ghi mới, gọi đến hàm trong ThuongHieuService
     * @param thuongHieuDTO bản ghi của hãng xe
     * @return bản ghi mới được tạo
     */
    @PostMapping
    public ThuongHieuDTO create(@RequestBody ThuongHieuDTO thuongHieuDTO) {
        return thuongHieuService.createThuongHieu(thuongHieuDTO);
    }

    /**
     * Lấy ra tất cả bản ghi
     * @return danh sách các bản ghi
     */
    @GetMapping
    public List<ThuongHieuDTO> getAll() {
        return  thuongHieuService.getAllThuongHieu();
    }

    /**
     * Lấy ra bản ghi của một hãng xe
     * @param hangXe tên hãng xe
     * @return bản ghi của hãng xe đó
     */
    @GetMapping("/{hangXe}")
    public ThuongHieuDTO findByBrand(@PathVariable String hangXe) {
        return thuongHieuService.getBrandByID(hangXe);
    }

    /**
     * Sửa thông tin của một hãng xe
     * @param hangXe tên hãng xe
     * @param thuongHieuDTO thông tin của hãng xe thay đổi
     * @return bản ghi mới
     */
    @PutMapping("/{hangXe}")
    public ThuongHieuDTO updateBrandDescript(@PathVariable String hangXe,  @RequestBody ThuongHieuDTO thuongHieuDTO) {
        return thuongHieuService.updateBrand(hangXe, thuongHieuDTO);
    }

    /**
     * Delete một hãng xe bất kì theo tên
     * @param hangXe tên hãng xe
     */
    @DeleteMapping("/{hangXe}")
    public void delete(@PathVariable String hangXe){
        thuongHieuService.deleteBrand(hangXe);
    }

    @Autowired
    ThuongHieuService thuongHieuService;
}
