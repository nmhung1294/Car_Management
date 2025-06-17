package facenet.car_management.service;

import facenet.car_management.dto.ImageDTO;
import facenet.car_management.dto.ThuongHieuDTO;
import facenet.car_management.facenet_car_db.*;
import facenet.car_management.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class ImageService {
    /**
     * Hàm này lưu danh sách ảnh của một hãng xe
     * @param hangXe tên hãng xe
     * @param files  danh sách file ảnh
     * @return danh sách tên file, định dạng tên hãng xe + thời gian tải
     */
    public List<String> saveBrandImg(String hangXe, MultipartFile[] files){
        List<String> fileNames = new ArrayList<>();
        Image img = new Image();
        ThuongHieu thuongHieu = new ThuongHieu();
        //Xử lý danh sách file
        logger.info("Bắt đầu xử lý danh sách file");
        for(MultipartFile file : files){
            Date time = new Date(System.currentTimeMillis());
            //Tạo tên mơ
            String new_name = hangXe + time.toString().replaceAll(" ", "_").replaceAll(":","_") + ".png";
            Path filePath = Paths.get(uploadDir + new_name);
            logger.info("Lưu fie vào thư mục và lưu đường dẫn vào DB, file", file.getOriginalFilename());
            try{
                logger.info(filePath.toString());
                //Lưu file vào thư mục
                file.transferTo(filePath.toFile());
                thuongHieu.setHangXe(hangXe);
                img.setThuongHieu(thuongHieu);
                img.setPath(new_name);
                //Lưu đường dẫn file vào cơ sở dữ liệu
                imageRepository.save(img);
                logger.debug("Lưu thành công");
                fileNames.add(new_name);
            }
            catch(Exception ex){
                logger.error("Đã có lỗi xảy ra", ex.getMessage());
                fileNames.add("Lỗi");
            }
        }
        return  fileNames;
    }

    /**
     * Hàm này lưu file ảnh của nhân sự
     * @param maNV mã người đó
     * @param file file ảnh
     * @return tên file ảnh được lưu
     */
    public String saveHrImg(String maNV, MultipartFile file){
        Image img = new Image();
        NhanSu nhanSu = new NhanSu();
        nhanSu.setMaNV(maNV);
        img.setNhanSu(nhanSu);
        Date time = new Date(System.currentTimeMillis());
        logger.info("Bắt đầu xử lý ảnh nhân sự");
        //Xử lý việc thay tên file
        String newNameFile = maNV + time.toString().replaceAll(" ", "_").replaceAll(":","_") + ".png";
        Path filePath = Paths.get(uploadDir + newNameFile);
        try{
            logger.info("Bắt đầu lưu");
            //CHuyển file vào vị trí lưu
            file.transferTo(filePath.toFile());
            logger.info(filePath.toString());
            img.setPath(filePath.toString());
            //Lưu đường dẫn file vào cơ sở dữ liệu
            imageRepository.save(img);
            logger.debug("Lưu thành công", file.getOriginalFilename());
        } catch (Exception e) {
            logger.error(e.getMessage());
            newNameFile="Đã xảy ra lỗi";
        }
        return newNameFile;
    }

    /**
     * Hàm này để tải ảnh xe lêm
     * @param maXe mã của xe
     * @param files danh sách các file
     * @return tên mới của các file
     */
    public List<String> saveCarImg(String maXe,  MultipartFile[] files){
        List<String> fileNames = new ArrayList<>();
        Image img = new Image();
        Xe xe = new Xe();
        //Xử lý danh sách file
        for(MultipartFile file : files){
            logger.info("Bắt đầu xử lý danh sách ảnh xe");
            Date time = new Date(System.currentTimeMillis());
            String new_name = maXe + time.toString().replaceAll(" ", "_").replaceAll(":","_") + ".png";
            Path filePath = Paths.get(uploadDir + new_name);
            try{
                logger.info("Bắt đầu lưu");
                logger.info(filePath.toString());
                //Lưu file vào một vị trí nào đó
                file.transferTo(filePath.toFile());
                xe.setMaXe(maXe);
                img.setMaXe(xe);
                img.setPath(new_name);
                //Lưu đường dẫn file vào cơ sở dữ liệu
                imageRepository.save(img);
                logger.debug("Lưu thành công",  file.getOriginalFilename());
                fileNames.add(new_name);
            }
            catch(Exception ex){
                logger.error(ex.getMessage());
                fileNames.add("Đã xảy ra lỗi");
            }
        }
        return  fileNames;
    }

    /**
     * Hàm này lấy ra tất cả ảnh của xe theo mã xe
     * @param maXe mã xe
     * @return danh sách đường dẫn ảnh của xe đó
     */
    public List<String> getAllCarImg(String maXe){
        try{
            logger.info("Lấy thông tin xe bằng mã ", maXe);
            return imageRepository.findCarImgByCode(maXe);
        } catch(Exception ex){
            logger.error("Đã có lỗi xảy ra", ex.getMessage());
            List<String> fileNames = new ArrayList<>();
            fileNames.add("Đã có lỗi");
            return fileNames;
        }
    }

    /**
     * Hàm này lấy ra tất cả đường dẫn tới ảnh của hãng xe theo tên hãng xe đó
     * @param hangXe tên hãng xe
     * @return danh sách đường dẫn các ảnh của hãng xe
     */
    public List<String> getAllBrandImg(String hangXe){
        try{
            logger.info("Bắt đầu lấy danh sách ảnh của hãng xe", hangXe);
            return imageRepository.findBrandImgByName(hangXe);
        } catch (Exception e) {
            logger.error("Đã có lỗi xảy ra", e.getMessage());
            List<String> fileNames = new ArrayList<>();
            fileNames.add("Đã có lỗi");
            return fileNames;
        }
    }

    /**
     * Phương thức này lấy ra đường dẫn tới ảnh của người nào đó theo mã
     * @param maNV mã của người đó
     * @return đường dẫn tới ảnh của người đó
     */
    public String getHRImg(String maNV){
        try{
            logger.info("Bắt đầu lấy ảnh nhân sự");
            return imageRepository.getHrImgByCode(maNV);
        } catch(Exception ex){
            logger.error("Đã có lỗi", ex.getMessage());
            return "Có lỗi xảy ra" + ex.getMessage();

        }
    }
    @Autowired
    private ImageRepository imageRepository;
    @Value("${app.upload-dir}")
    private String uploadDir;
    Logger logger = LogManager.getLogger();

}
