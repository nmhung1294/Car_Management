package facenet.car_management.service;

import facenet.car_management.dto.XeDTO;
import facenet.car_management.facenet_car_db.Xe;
import facenet.car_management.repository.CarRepository;
import facenet.car_management.repository.ImageRepository;
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
    public List<XeDTO> getAllCars() {
        List<XeDTO> carDTOList = new ArrayList<>();
        //Lấy ra danh sách xe từ cơ sở dữ liệu
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
    }

    /**
     * Hàm này lấy ra bản ghi của một xe theo mã
     * @param maXe mã của xe
     * @return dữ liệu của xe có mã là maXe
     */
    public XeDTO getCarByCode(String maXe) {
        //Lấy Dữ liệu từ DB
        Xe xe = carRepository.findCarByCode(maXe);
        //Trả về kết quả DTO
        XeDTO xeDTO = new XeDTO(xe.getMaXe(),
                xe.getHangXe().getHangXe(), xe.getModel(),
                xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
        return xeDTO;
    }

    /**
     * Hàm này tạo một bản ghi mới cho xe
     * @param carDTO dữ liệu của xe muốn thêm
     * @return bản ghi mới được tạo
     */
    public XeDTO createCar(XeDTO carDTO) {
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
    /**
     * Cập nhật dữ liệu cho xe có mã là maXe
     * @param carDTO bản ghi mới muốn cập nhật
     * @return xe sau khi cập nhật
     */
    public XeDTO updateCarByCode(String maXe, XeDTO carDTO) {
        //Lấy dữ liệu từ DB theo mã xe
        Xe xe = carRepository.findCarByCode(maXe);
        // Tạo bản ghi mới để lưu
        Xe update = new Xe(xe.getMaXe(),
                carDTO.getHangXe(), carDTO.getModel(),
                carDTO.getNamSX(),  carDTO.getPhienBan(), carDTO.getColor(),
                carDTO.getPrice(), carDTO.getNgayNhap(), carDTO.getTienIch());
        //Lưu vào DB
        xe = carRepository.save(update);
        //Trả về kết quả là DTO
        return new XeDTO(
                xe.getMaXe(),
                xe.getHangXe().getHangXe(), xe.getModel(),
                xe.getNamSX(),  xe.getPhienBan(), xe.getColor(),
                xe.getPrice(), xe.getNgayNhap(), xe.getTienIch());
    }

    /**
     * Xóa dữ liệu của một xe theo mã xe
     * @param maXe mã của xe
     * @return chuỗi Ok hoặc tin nhắn error
     */
    public String deleteCarByCode(String maXe) {
        //Xóa ảnh trong Image
        imageRepository.deleteCarImgByCode(maXe);
        //Xóa dữ liệu trong bảng Xe
        carRepository.deleteCarByCode(maXe);
        return "ok";
    }

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ImageRepository imageRepository;

    Logger logger = LogManager.getLogger(CarService.class);

}
