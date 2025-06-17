package facenet.car_management.service;

import facenet.car_management.dto.KhachHangDTO;
import facenet.car_management.facenet_car_db.KhachHang;
import facenet.car_management.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

@Service
public class KhachHangService {

    /**
     * Hàm này lấy ra danh sách các khách hàng
     * @return danh sách các khách hàng
     */
    public List<KhachHangDTO> getAllCustomer(){
        List<KhachHangDTO> khachHangDTOList = new ArrayList<>();
        try{
            logger.info("Bắt đầu lấy dữ liệu");
            //Lấy ra danh sách dữ liệu
            List<KhachHang>  khachHangList = khachHangRepository.findAll();
            //Chuyển về DTO, lưu vào danh sách và trả về
            logger.debug("Lấy dữ liệu thành công, chuyển về DTO rồi return");
            for(KhachHang khachHang : khachHangList){
                khachHangDTOList.add(new KhachHangDTO(khachHang.getMaKhachHang(), khachHang.getName(), khachHang.getPhoneNum(), khachHang.getEmail(), khachHang.getDiaChi()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return khachHangDTOList;
    }

    /**
     * Hàm này tạo một bản ghi mới vào bảng khách haàng
     * @param khachHangDTO thông tin của khách hàng, bao gồm
     *                     mã, tên, email, số điện thoại, địa chỉ
     * @return bản ghi mới được tạo
     */
    public KhachHangDTO createCustomer(KhachHangDTO khachHangDTO) {
        try {
            // Tạo bản ghi mới để lưu vào db
            logger.info("Bắt đầu thêm bản ghi vào bảng");
            KhachHang khachHang = new KhachHang(khachHangDTO.getMaKhachHang(),khachHangDTO.getName(),khachHangDTO.getEmail(),khachHangDTO.getPhoneNum(),khachHangDTO.getDiaChi());
            //Lưu
            KhachHang result = khachHangRepository.save(khachHang);
            //Trả về DTO
            logger.debug("Lưu thành công", result.getMaKhachHang());
            return new KhachHangDTO(result.getMaKhachHang(), result.getName(), result.getPhoneNum(), result.getEmail(), result.getDiaChi());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new KhachHangDTO();
        }

    }

    /**
     * Hàm này lấy ra thông tin của một khách hàng bằng mã của người đó
     * @param maKhachHang mã của khách hàng đó
     * @return bản ghi dữ liệu khách hàng đó
     */
    public KhachHangDTO getCustomerByCode(String maKhachHang){
       try{
           logger.info("Bắt đầu truy vấn bằng nv code", maKhachHang);
           KhachHang result = khachHangRepository.getCustomerByCode(maKhachHang);
           logger.debug("Lấy data thành công");
           return new KhachHangDTO(result.getMaKhachHang(), result.getName(), result.getPhoneNum(), result.getEmail(), result.getDiaChi());
       } catch (Exception e){
           logger.error(e.getMessage());
           return new KhachHangDTO();
       }
    }

    /**
     * Hàm này để thay đô thông tin của một khách hàng
     * @param khachHangDTO thông tin update của một khách hàng
     * @return bản ghi mới được tạo
     */
    public KhachHangDTO updateCustomer(KhachHangDTO khachHangDTO) {
        try{
            logger.info("Bắt đàu cập nhật thông tin khách hàng");
            //Lấy ra bản ghi hiện tại trong db và cập nhật các trường
            logger.debug("Lấy thông tin khách hàng có mã ", khachHangDTO.getMaKhachHang());
            KhachHang khachHang = khachHangRepository.getCustomerByCode(khachHangDTO.getMaKhachHang());
            logger.debug("Lấy thông tin thành công, tiến hành cập nhật và lưu vào csdl");
            khachHang.setName(khachHangDTO.getName());
            khachHang.setEmail(khachHangDTO.getEmail());
            khachHang.setPhoneNum(khachHangDTO.getPhoneNum());
            khachHang.setDiaChi(khachHang.getDiaChi());
            //Lưu lại vào db
            KhachHang result = khachHangRepository.save(khachHang);
            logger.debug("Cập nhật thành công");
            //Chuyển về DTO và trả về kết quả
            KhachHangDTO resultDTO = new KhachHangDTO();
            resultDTO.setMaKhachHang(result.getMaKhachHang());
            resultDTO.setName(result.getName());
            resultDTO.setPhoneNum(result.getPhoneNum());
            resultDTO.setEmail(result.getEmail());
            resultDTO.setDiaChi(result.getDiaChi());
            return resultDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new KhachHangDTO();
        }
    }
    @Autowired
    private KhachHangRepository khachHangRepository;

    Logger logger = LogManager.getLogger();
}
