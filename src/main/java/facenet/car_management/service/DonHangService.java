package facenet.car_management.service;

import facenet.car_management.dto.DonHangDTO;
import facenet.car_management.facenet_car_db.*;
import facenet.car_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class DonHangService {
    /**
     * Hàm này lấy tất cả đơn hàng xếp theo thứ tự mới nhất
     * @return danh sách đơn hàng
     */
    public List<DonHangDTO> getAllOrders() {
        //Lấy các bản ghi từ cơ sở dữ liệu

        List<DonHang> orders = orderRepository.getAllOrders();

        List<DonHangDTO> donHangDTOS = new ArrayList<>();
        //Chuyển các bản ghi thành dạng DTO và trả về kết quả
        for (DonHang donHang : orders) {
            donHangDTOS.add(new DonHangDTO(donHang.getMaDonHang(), donHang.getMaKhachHang().getMaKhachHang(), donHang.getNgay(),
                    donHang.getMaXe().getMaXe(), donHang.getMaNV().getMaNV(), donHang.getStatus()));
        }
        return donHangDTOS;
    }

    /**
     * Hàm này lấy tất cả đơn hàng theo trạng thái đang xử lý hay đã hoàn thành,ứng với 0,1
     * @param status trạng thái đơn hàng
     * @return danh sách đơn hàng
     */
    public List<DonHangDTO> getOrdersByStatus(Integer status) {
        try {
            // Lấy các bản ghi trong Db
            logger.info("Bắt đầu lấy dữ liệu trong db");
            List<DonHang> orders = orderRepository.getAllOrdersByStatus(status);
            List<DonHangDTO> donHangDTOS = new ArrayList<>();
            //Chuyển về DTO và trả về kết quả
            logger.debug("Lấy kết quả thành công, chuyển sang DTO rồi return");
            for (DonHang donHang : orders) {
                donHangDTOS.add(new DonHangDTO(donHang.getMaDonHang(), donHang.getMaKhachHang().getMaKhachHang(), donHang.getNgay(),
                        donHang.getMaXe().getMaXe(), donHang.getMaNV().getMaNV(), donHang.getStatus()));
            }
            return donHangDTOS;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Hàm này tạo bản ghi đơn hàng mới
     * @param donHangDTO thông tin đơn hàng
     * @return bản ghi ơn hàng mới
     */
    public DonHangDTO createOrder(DonHangDTO donHangDTO) {
        try {
            // Tạo mã đơn hàng nếu chưa có, mặc định status = 0
            logger.info("Bắt đầu thêm đơn hàng, kiểm tra các đại lượng");
            if (donHangDTO.getMaDonHang() == null || donHangDTO.getMaDonHang().isEmpty()) {
                donHangDTO.setMaDonHang("DH-" + UUID.randomUUID().toString());
            }

            // Kiểm tra KhachHang
            KhachHang khachHang = khachHangRepository.getCustomerByCode(donHangDTO.getMaKhachHang());
            if (khachHang == null) {
                throw new IllegalArgumentException("Khách hàng không tồn tại: " + donHangDTO.getMaKhachHang());
            }

            // Kiểm tra NhanSu
            NhanSu nhanSu = nhanSuRepository.findByMaNV(donHangDTO.getMaNV());
            if (nhanSu == null) {
                throw new IllegalArgumentException("Nhân sự không tồn tại: " + donHangDTO.getMaNV());
            }

            // Kiểm tra Xe
            Xe xe = carRepository.findCarByCode(donHangDTO.getMaXe());
            if (xe == null) {
                throw new IllegalArgumentException("Xe không tồn tại: " + donHangDTO.getMaXe());
            }
            logger.debug("Tạo bản ghi đơn hàng mới và lưu");
            // Tạo DonHang
            DonHang donHang = new DonHang(
                    donHangDTO.getMaDonHang(),
                    khachHang,
                    donHangDTO.getNgay(),
                    xe,
                    nhanSu,
                    donHangDTO.getStatus() != null ? donHangDTO.getStatus() : 0
            );

            // Lưu DonHang
            donHang = orderRepository.save(donHang);
            logger.debug("Lưu thành công", donHang.getMaDonHang());
            // Trả về DTO
            return new DonHangDTO(
                    donHang.getMaDonHang(),
                    khachHang.getMaKhachHang(),
                    donHang.getNgay(),
                    xe.getMaXe(),
                    nhanSu.getMaNV(),
                    donHang.getStatus()
            );
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new DonHangDTO();
        }
    }
    /**
     * Update trạng thái của đon hàng
     * @param maDonHang mã của đơn hàng
     * @param donHangDTO bản ghi của đơn hàng muốn update
     * @return bản ghi đơn hàng mới
     */
    public DonHangDTO updateOrder(String maDonHang, DonHangDTO donHangDTO) {
        try{
            //Lấy ra đơn hàng theo mã từ cơ sở dữ liệu
            DonHang donHang = orderRepository.getOrdersByID(maDonHang);
            donHang.setStatus(donHangDTO.getStatus());
            donHang = orderRepository.save(donHang);
            //Khởi tạo bản ghi cskh đầu tiên
            CSKH cskh = new CSKH();
            cskh.setMaDonHang(donHang);
            cskh.setMaKhachHang(donHang.getMaKhachHang());
            cskh.setNgay(donHang.getNgay());
            //Lưu vào cskh
            cskhRepository.save(cskh);
            //Trả về bản ghi đơn hàng mới (DTO)
            return new DonHangDTO(donHang.getMaDonHang(), donHang.getMaKhachHang().getMaKhachHang(), donHang.getNgay(),
                    donHang.getMaXe().getMaXe(), donHang.getMaNV().getMaNV(), donHang.getStatus());
        } catch (Exception e){
            logger.error(e.getMessage());
            return new DonHangDTO();
        }
    }
    @Autowired
    private DonHangRepository orderRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanSuRepository  nhanSuRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CskhRepository cskhRepository;

    Logger logger = LogManager.getLogger(DonHangService.class);
}
