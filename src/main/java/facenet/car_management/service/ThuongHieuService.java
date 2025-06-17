package facenet.car_management.service;

import facenet.car_management.dto.ThuongHieuDTO;
import facenet.car_management.facenet_car_db.ThuongHieu;
import facenet.car_management.repository.ImageRepository;
import facenet.car_management.repository.ThuongHieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ThuongHieuService {
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    @Autowired
    private ImageRepository imageRepository;

    /**
     * Hàm này tạo bản ghi mới cho hãng xe - bảng thuong_hieu
     * @param thuongHieuDTO Hãng xe
     * @return bản ghi mới được tạo
     */
    public ThuongHieuDTO createThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        //Tạo bản ghi thương hiệu mới
        ThuongHieu thuongHieu = new ThuongHieu();
        thuongHieu.setHangXe(thuongHieuDTO.getHangXe());
        thuongHieu.setMoTa(thuongHieuDTO.getMoTa());
        thuongHieu.setNgayHopTac(thuongHieuDTO.getNgayHopTac());
        //Lưu vào db
        ThuongHieu savedThuongHieu=  thuongHieuRepository.save(thuongHieu);
        //Chuyển về DTO và trả về kết quả
        ThuongHieuDTO res = new ThuongHieuDTO();
        res.setHangXe(savedThuongHieu.getHangXe());
        res.setMoTa(savedThuongHieu.getMoTa());
        res.setNgayHopTac(savedThuongHieu.getNgayHopTac());
        return res;
    }

    /**
     * Hàm này lấy ra tất cả các bản ghi
     * @return danh sách các brand cùng đầy đủ các trường
     */
    public List<ThuongHieuDTO> getAllThuongHieu() {
        List<ThuongHieuDTO> thuongHieuDTOList = new ArrayList<>();
        //Lấy ra các bản ghi trong db
        List<ThuongHieu> thuongHieuList = thuongHieuRepository.findAll();
        //Chuyển sang DTO và trả về kết quả
        for (ThuongHieu thuongHieu : thuongHieuList) {
            thuongHieuDTOList.add(new ThuongHieuDTO(thuongHieu.getHangXe(), thuongHieu.getMoTa(),  thuongHieu.getNgayHopTac()));
        }
        return thuongHieuDTOList;
    }
    /**
     * Lấy ra toàn bộ thông tin hãng xe nào đó
     * @param hangXe tên hãng xe
     * @return bản ghi của hãng xe đó
     */
    public ThuongHieuDTO getBrandByID(String hangXe) {
        //Lấy ra bản ghi trong db
        ThuongHieu thuongHieu =  thuongHieuRepository.findById(hangXe).get();
        //Chuyển về DTO và trả về kết quả
        ThuongHieuDTO res = new ThuongHieuDTO();
        res.setHangXe(thuongHieu.getHangXe());
        res.setMoTa(thuongHieu.getMoTa());
        res.setNgayHopTac(thuongHieu.getNgayHopTac());
        return res;
    }

    /**
     * Cập nhật dữ liệu cho một hãng xe nào đó
     * @param hangXe tên hãng xe
     * @param thuongHieuDTO Bản ghi mới cần cập nhật
     * @return bản ghi mới của hãng xe đó
     */
    public ThuongHieuDTO updateBrand(String hangXe, ThuongHieuDTO thuongHieuDTO) {
        //Lấy ra bản ghi hiện tại trong db và cập nhật các trường
        ThuongHieu thuongHieu =  thuongHieuRepository.findById(hangXe).get();
        thuongHieu.setHangXe(thuongHieuDTO.getHangXe());
        thuongHieu.setMoTa(thuongHieuDTO.getMoTa());
        thuongHieu.setNgayHopTac(thuongHieuDTO.getNgayHopTac());
        //Lưu vao db
        ThuongHieu res =   thuongHieuRepository.save(thuongHieu);
        //Chuyển về DTO và trả về kết quả
        ThuongHieuDTO resDTO = new ThuongHieuDTO();
        resDTO.setHangXe(res.getHangXe());
        resDTO.setMoTa(res.getMoTa());
        resDTO.setNgayHopTac(res.getNgayHopTac());
        return resDTO;
    }

    /**
     * Xóa một hãng xe
     * @param hangXe tên hãng xe
     */
    @Modifying
    @Transactional
    public void deleteBrand(String hangXe) {
        imageRepository.deleteBrandImgByCode(hangXe);
        List<ThuongHieu> thuongHieu = thuongHieuRepository.findAllById(Collections.singleton(hangXe));
        for(ThuongHieu th :  thuongHieu){
            thuongHieuRepository.delete(th);
        }
    }
}

