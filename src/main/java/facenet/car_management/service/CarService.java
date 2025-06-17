package facenet.car_management.service;

import facenet.car_management.dto.XeDTO;
import facenet.car_management.facenet_car_db.Xe;
import facenet.car_management.repository.CarRepository;
import facenet.car_management.repository.ImageRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CarService {
    /**
     * Hàm này lấy ra dữ liệu của tất cả các xe trong database
     * @return danh sách các xe
     */
    @Operation(summary = "Retrieve a list of cars", description = "Returns a paginated list of all cars in the system")
    public List<XeDTO> getAllCars() throws  Exception{
        List<XeDTO> carDTOList = new ArrayList<>();
        try {
            //Lấy ra danh sách xe từ cơ sở dữ liệu
            logger.info("Bắt đầu truy vấn, lấy xe từ db");
            List<Xe> carList = carRepository.getAllCar();
            //Chuyển dữ liệu sang DTO
            for (Xe xe : carList) {
                XeDTO xeDTO = new XeDTO(xe.getMaXe(),
                        xe.getHangXe().getHangXe(), xe.getModel(),
                        xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                        xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
                carDTOList.add(xeDTO);
            }
            //Trả về kết quả
            return carDTOList;
        }  catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Đã có ngoại lệ " + e.getMessage());
        }

    }

    /**
     * Hàm này lấy ra bản ghi của một xe theo mã
     * @param maXe mã của xe
     * @return dữ liệu của xe có mã là maXe
     */
    public XeDTO getCarByCode(String maXe) throws  Exception{
        try{
            logger.info("Bắt đầu lấy dữ liệu của xe có maXe từ cơ sở dữ liệu");
            //Lấy Dữ liệu từ DB
            Xe xe = carRepository.findCarByCode(maXe);
            //Trả về kết quả DTO
            XeDTO xeDTO = new XeDTO(xe.getMaXe(),
                    xe.getHangXe().getHangXe(), xe.getModel(),
                    xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                    xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
            logger.info("Đã lấy được kết quả: ",  xeDTO.toString());
            return xeDTO;
        } catch (Exception e){
            logger.error("Lỗi khi tìm xe bằng mã: ", e.getMessage());
            throw new Exception("Có lỗi xảy ra khi tìm xe bằng mã " + e.getMessage());
        }
    }

    /**
     * Hàm này tạo một bản ghi mới cho xe
     * @param carDTO dữ liệu của xe muốn thêm
     * @return bản ghi mới được tạo
     */
    public XeDTO createCar(XeDTO carDTO) throws Exception {
        try{
            //Tạo bản ghi
            Xe car = new Xe(carDTO.getMaXe(),
                    carDTO.getHangXe(), carDTO.getModel(),
                    carDTO.getNamSX(),  carDTO.getPhienBan(), carDTO.getColor(),
                    carDTO.getPrice(), carDTO.getNgayNhap(), carDTO.getTienIch());
            //Lưu vào cơ sở dữ liệu
            Xe xe = carRepository.save(car);
            //Trả về kết quả là DTO
            XeDTO xeDTO = new XeDTO(xe.getMaXe(),
                    xe.getHangXe().getHangXe(), xe.getModel(),
                    xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                    xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
            return xeDTO;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Cập nhật dữ liệu cho xe có mã là maXe
     * @param carDTO bản ghi mới muốn cập nhật
     * @return xe sau khi cập nhật
     */
    public XeDTO updateCarByCode(String maXe, XeDTO carDTO) throws Exception{
        //Lấy dữ liệu từ DB theo mã xe
        logger.info("Lấy dữ liệu của xe có mã là {}", maXe);
        Xe xe = carRepository.findCarByCode(maXe);
        // Tạo bản ghi mới để lưu
        if (xe == null) {
            logger.error("Không tìm thấy bản ghi, kiểm tra lại mã xe");
            throw new Exception("Kiểm tra lại mã xe, hoặc không tồn tại mã xe");
        }
        logger.info("Bắt đầu cập nhật");
        try{
            Xe update = new Xe(xe.getMaXe(),
                    carDTO.getHangXe(), carDTO.getModel(),
                    carDTO.getNamSX(),  carDTO.getPhienBan(), carDTO.getColor(),
                    carDTO.getPrice(), carDTO.getNgayNhap(), carDTO.getTienIch());
            //Lưu vào DB

            xe = carRepository.save(update);
            logger.debug("Cập nhật thành công ", xe.getMaXe());
            //Trả về kết quả là DTO
            return new XeDTO(
                    xe.getMaXe(),
                    xe.getHangXe().getHangXe(), xe.getModel(),
                    xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                    xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
        }catch (Exception e){
            logger.error("Cập nhật không thành công: ", e.getMessage());
            throw new Exception("Cập nhật không thành công, kiểm tra lại bản ghi mới " + e.getMessage());

        }
    }

    /**
     * Xóa dữ liệu của một xe theo mã xe
     * @param maXe mã của xe
     * @return chuỗi Ok hoặc tin nhắn error
     */
    public String deleteCarByCode(String maXe) throws Exception {
        try {
            logger.info("Bắt đầu xóa xe trong bảng lưu trữ ảnh");
            //Xóa ảnh trong Image
            imageRepository.deleteCarImgByCode(maXe);
            logger.info("Bắt đầu xóa xe trong bảng xe");
            //Xóa dữ liệu trong bảng Xe
            carRepository.deleteCarByCode(maXe);
            logger.info("Xoá thành công", maXe);
            return "ok";
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ImageRepository imageRepository;

    Logger logger = LogManager.getLogger(CarService.class);

}
