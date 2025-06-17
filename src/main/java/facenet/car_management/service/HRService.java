package facenet.car_management.service;

import facenet.car_management.dto.NhanSuDTO;
import facenet.car_management.facenet_car_db.NhanSu;
import facenet.car_management.repository.NhanSuRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import facenet.car_management.repository.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;

@Service
public class HRService {
    /**
     * Hàm này tạo một bản ghi mới cho nhân sự
     * @param nhanSuDTO nhân sự với các thuộc tính/trường
     * @return bản ghi nhân sự mới được tạo
     */
    public NhanSuDTO createNhanSu(NhanSuDTO nhanSuDTO) throws Exception{
        try{
            logger.info("Bắt đầu tạo bản ghi mới");
            //Tạo bản ghi nhân sự mới
            NhanSu nhanSu = new NhanSu();
            nhanSu.setName(nhanSuDTO.getName());
            nhanSu.setMaNV(nhanSuDTO.getMaNV());
            nhanSu.setBirthDate(nhanSuDTO.getBirthDate());
            nhanSu.setDayIn(nhanSuDTO.getDayIn());
            nhanSu.setDayOut(nhanSuDTO.getDayOut());
            nhanSu.setRole(nhanSuDTO.getRole());
            nhanSu.setAddress(nhanSuDTO.getAddress());
            //Lưu vào cơ sở dữ liệu
            NhanSu result =  nhanSuRepository.save(nhanSu);
            //Trả về DTO
            logger.debug("Thêm người mới thành công", result.getMaNV());
            return new NhanSuDTO(result.getMaNV(), result.getName(), result.getBirthDate(),
                    result.getDayIn(), result.getDayOut(), result.getRole(),result.getAddress());
        } catch (Exception e){
            logger.error("Kiểm tra lại dữ liệu hoặc DB", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Hàm này lấy ra danh sách nhân sự
     * @return danh sách nhân sự trong cơ sở dữ liệu
     */
    public List<NhanSuDTO> getAllNhanSu() throws Exception{
        //Lấy ra các bản ghi nhân sự trong cơ sở dữ liệu
        logger.info("Bắt đầu lấy thông tin mỗi người từ db");
        List<NhanSu> results = nhanSuRepository.getAll();
        logger.debug("Kết quả", results);
        List<NhanSuDTO> nhanSuDTOList = new ArrayList<>();
        try{
            //Chuyển các bản ghi về DTO và trả về
            for (NhanSu result : results) {
                nhanSuDTOList.add(new NhanSuDTO(result.getMaNV(), result.getName(), result.getBirthDate(),
                        result.getDayIn(), result.getDayOut(), result.getRole(),result.getAddress()));
            }
            return nhanSuDTOList;

        }
        catch(Exception e){
            //Nếu lỗi thì debug và trả về List rỗng
            logger.error(e.getMessage());
            throw  new Exception(e.getMessage());
        }
    }

    /**
     * Hàm này lấy ra thông tin của một nhân sự
     * @param maNV mã của người đó
     * @return bản ghi thông tin của người đó
     */
    public NhanSuDTO getNhanSuById(String maNV) throws Exception{
        try {
            //Lấy ra dữ liệu từ db
            logger.info("Bắt đầu lấy thông tin của maNV");
            NhanSu result = nhanSuRepository.findByMaNV(maNV);
            logger.debug("Lấy kết quả thành công", result);
            //trả về DTO
            return new NhanSuDTO(result.getMaNV(), result.getName(), result.getBirthDate(),
                    result.getDayIn(), result.getDayOut(), result.getRole(),result.getAddress());
        } catch (Exception e){
            logger.error("Đã có lỗi khi lấy thông tin nhân sự bằng code", e.getMessage());
            throw  new Exception(e.getMessage());
        }


    }

    /**
     * Hàm này để update một bản ghi của nhân sự
     * @param nhanSuDTO bản ghi mới
     * @return bản ghi mới của nhân sự
     */
    public NhanSuDTO updateNhanSu(String maNV, NhanSuDTO nhanSuDTO) throws Exception{
        try{
            logger.info("Bắt đầu lấy dữ liệu trong DB");
            //Lấy ra dữ liệu trong cơ sở dữ liệu
            NhanSu nhanSu = nhanSuRepository.findByMaNV(nhanSuDTO.getMaNV());
            //Cập nhật các trường trừ mã nhân viên
            logger.info("Bắt đầu tạo bản ghi cập nhật");
            nhanSu.setName(nhanSuDTO.getName());
            nhanSu.setBirthDate(nhanSuDTO.getBirthDate());
            nhanSu.setDayIn(nhanSuDTO.getDayIn());
            nhanSu.setDayOut(nhanSuDTO.getDayOut());
            nhanSu.setRole(nhanSuDTO.getRole());
            nhanSu.setAddress(nhanSuDTO.getAddress());
            //Lưu kết quả
            NhanSu result =  nhanSuRepository.save(nhanSu);
            //Trả về DTO
            logger.debug("Cập nhật thành công", result.getMaNV());
            return new NhanSuDTO(result.getMaNV(), result.getName(), result.getBirthDate(),
                    result.getDayIn(), result.getDayOut(), result.getRole(),result.getAddress());
        } catch (Exception e){
            logger.error("Đã có lỗi: ", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Hàm này xóa nhân sự theo mã
     * @param maNV mã của người cần xóa
     */
    public void deleteNhanSu(String maNV)  {
        try {
            logger.info("Xóa nhân viên khỏi bảng ảnh");
            imageRepository.deleteByMaNV(maNV);
            logger.info("Xóa nhân viên khỏi bảng nhân sự");
            nhanSuRepository.deleteByMaNV(maNV);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Autowired
    private ImageRepository imageRepository;
    Logger logger = LogManager.getLogger(HRService.class);
}
