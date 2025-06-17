package facenet.car_management.service;

import facenet.car_management.dto.KhachHangDTO;
import facenet.car_management.facenet_car_db.KhachHang;
import facenet.car_management.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    /**
     * Hàm này lấy ra danh sách các khách hàng
     * @return danh sách các khách hàng
     */
    public List<KhachHangDTO> getAllCustomer(){
        List<KhachHangDTO> khachHangDTOList = new ArrayList<>();
        //Lấy ra danh sách dữ liệu
        List<KhachHang>  khachHangList = khachHangRepository.findAll();
        //Chuyển về DTO, lưu vào danh sách và trả về
        for(KhachHang khachHang : khachHangList){
            khachHangDTOList.add(new KhachHangDTO(khachHang.getMaKhachHang(), khachHang.getName(), khachHang.getPhoneNum(), khachHang.getEmail(), khachHang.getDiaChi()));
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
        // Tạo bản ghi mới để lưu vào db
        KhachHang khachHang = new KhachHang(khachHangDTO.getMaKhachHang(),khachHangDTO.getName(),khachHangDTO.getEmail(),khachHangDTO.getPhoneNum(),khachHangDTO.getDiaChi());
        //Lưu
        KhachHang result = khachHangRepository.save(khachHang);
        //Trả về DTO
        return new KhachHangDTO(result.getMaKhachHang(), result.getName(), result.getPhoneNum(), result.getEmail(), result.getDiaChi());
    }

    /**
     * Hàm này lấy ra thông tin của một khách hàng bằng mã của người đó
     * @param maKhachHang mã của khách hàng đó
     * @return bản ghi dữ liệu khách hàng đó
     */
    public KhachHangDTO getCustomerByCode(String maKhachHang){
        KhachHang result = khachHangRepository.getCustomerByCode(maKhachHang);
        return new KhachHangDTO(result.getMaKhachHang(), result.getName(), result.getPhoneNum(), result.getEmail(), result.getDiaChi());
    }

    /**
     * Hàm này để thay đô thông tin của một khách hàng
     * @param khachHangDTO thông tin update của một khách hàng
     * @return bản ghi mới được tạo
     */
    public KhachHangDTO updateCustomer(KhachHangDTO khachHangDTO) {
        //Lấy ra bản ghi hiện tại trong db và cập nhật các trường
        KhachHang khachHang = khachHangRepository.getCustomerByCode(khachHangDTO.getMaKhachHang());
        khachHang.setName(khachHangDTO.getName());
        khachHang.setEmail(khachHangDTO.getEmail());
        khachHang.setPhoneNum(khachHangDTO.getPhoneNum());
        khachHang.setDiaChi(khachHang.getDiaChi());
        //Lưu lại vào db
        KhachHang result = khachHangRepository.save(khachHang);
        //Chuyển về DTO và trả về kết quả
        KhachHangDTO resultDTO = new KhachHangDTO();
        resultDTO.setMaKhachHang(result.getMaKhachHang());
        resultDTO.setName(result.getName());
        resultDTO.setPhoneNum(result.getPhoneNum());
        resultDTO.setEmail(result.getEmail());
        resultDTO.setDiaChi(result.getDiaChi());
        return resultDTO;
    }

}
