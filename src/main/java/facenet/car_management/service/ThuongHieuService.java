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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class ThuongHieuService {

    /**
     * Hàm này tạo bản ghi mới cho hãng xe - bảng thuong_hieu
     * @param thuongHieuDTO Hãng xe
     * @return bản ghi mới được tạo
     */
    public ThuongHieuDTO createThuongHieu(ThuongHieuDTO thuongHieuDTO) {
        try{
            logger.info("Bắt đầu tạo bản ghi thương hiệu mới");
            //Tạo bản ghi thương hiệu mới
            ThuongHieu thuongHieu = new ThuongHieu();
            thuongHieu.setHangXe(thuongHieuDTO.getHangXe());
            thuongHieu.setMoTa(thuongHieuDTO.getMoTa());
            thuongHieu.setNgayHopTac(thuongHieuDTO.getNgayHopTac());
            //Lưu vào db
            logger.debug("Lưu vào db");
            ThuongHieu savedThuongHieu=  thuongHieuRepository.save(thuongHieu);
            //Chuyển về DTO và trả về kết quả
            logger.debug("Lưu thành công", savedThuongHieu.getHangXe());
            ThuongHieuDTO res = new ThuongHieuDTO();
            res.setHangXe(savedThuongHieu.getHangXe());
            res.setMoTa(savedThuongHieu.getMoTa());
            res.setNgayHopTac(savedThuongHieu.getNgayHopTac());
            return res;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ThuongHieuDTO();
        }
    }

    /**
     * Hàm này lấy ra tất cả các bản ghi
     * @return danh sách các brand cùng đầy đủ các trường
     */
    public List<ThuongHieuDTO> getAllThuongHieu() {
        try{
            List<ThuongHieuDTO> thuongHieuDTOList = new ArrayList<>();
            logger.info("Lấy ra các bản ghi trong db");
            //Lấy ra các bản ghi trong db
            List<ThuongHieu> thuongHieuList = thuongHieuRepository.findAll();
            //Chuyển sang DTO và trả về kết quả
            logger.info("Lấy ra thành công, chuyển sang DTO và trả về");
            for (ThuongHieu thuongHieu : thuongHieuList) {
                thuongHieuDTOList.add(new ThuongHieuDTO(thuongHieu.getHangXe(), thuongHieu.getMoTa(),  thuongHieu.getNgayHopTac()));
            }
            return thuongHieuDTOList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
    /**
     * Lấy ra toàn bộ thông tin hãng xe nào đó
     * @param hangXe tên hãng xe
     * @return bản ghi của hãng xe đó
     */
    public ThuongHieuDTO getBrandByID(String hangXe) {
        try {
            //Lấy ra bản ghi trong db
            logger.info("Lấy thông tin brand bằng tên hãng ", hangXe);
            ThuongHieu thuongHieu =  thuongHieuRepository.findById(hangXe).get();
            //Chuyển về DTO và trả về kết quả
            logger.info("Lấy thành công, chuyển sang DTO và return");
            ThuongHieuDTO res = new ThuongHieuDTO();
            res.setHangXe(thuongHieu.getHangXe());
            res.setMoTa(thuongHieu.getMoTa());
            res.setNgayHopTac(thuongHieu.getNgayHopTac());
            return res;
        } catch (Exception e) {
            logger.error("Lỗi khi lấy dữ liệu", e.getMessage());
            return new ThuongHieuDTO();
        }
    }

    /**
     * Cập nhật dữ liệu cho một hãng xe nào đó
     * @param hangXe tên hãng xe
     * @param thuongHieuDTO Bản ghi mới cần cập nhật
     * @return bản ghi mới của hãng xe đó
     */
    public ThuongHieuDTO updateBrand(String hangXe, ThuongHieuDTO thuongHieuDTO) {
        try{
            //Lấy ra bản ghi hiện tại trong db và cập nhật các trường
            logger.info("Lấy ra bản ghi hiện tại bằng tên hãng xe", hangXe);
            ThuongHieu thuongHieu =  thuongHieuRepository.findById(hangXe).get();
            logger.debug("Lấy thành công, cập nhật trường và lưu vào DB");
            thuongHieu.setHangXe(thuongHieuDTO.getHangXe());
            thuongHieu.setMoTa(thuongHieuDTO.getMoTa());
            thuongHieu.setNgayHopTac(thuongHieuDTO.getNgayHopTac());
            //Lưu vao db
            ThuongHieu res =   thuongHieuRepository.save(thuongHieu);
            //Chuyển về DTO và trả về kết quả
            logger.debug("Lưu thành công", res.getHangXe());
            ThuongHieuDTO resDTO = new ThuongHieuDTO();
            resDTO.setHangXe(res.getHangXe());
            resDTO.setMoTa(res.getMoTa());
            resDTO.setNgayHopTac(res.getNgayHopTac());
            return resDTO;
        } catch (Exception e) {
            logger.error("Lỗi khi update", e.getMessage());
            return new ThuongHieuDTO();
        }
    }

    /**
     * Xóa một hãng xe
     * @param hangXe tên hãng xe
     */
    @Modifying
    @Transactional
    public void deleteBrand(String hangXe) {
        try{
            logger.info("Xóa brand trong bảng ảnh");
            imageRepository.deleteBrandImgByCode(hangXe);
            logger.info("xóa hết brand");
            List<ThuongHieu> thuongHieu = thuongHieuRepository.findAllById(Collections.singleton(hangXe));
            for(ThuongHieu th :  thuongHieu){
                thuongHieuRepository.delete(th);
            }
            logger.debug("Xóa thành công");
        } catch (Exception e) {
            logger.error("Lỗi khi xóa", e.getMessage());
        }
    }
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    @Autowired
    private ImageRepository imageRepository;

    Logger logger =  LogManager.getLogger(ThuongHieu.class);
}

