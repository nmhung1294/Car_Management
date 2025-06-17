package facenet.car_management.service;

import facenet.car_management.dto.CskhDTO;
import facenet.car_management.facenet_car_db.CSKH;
import facenet.car_management.facenet_car_db.DonHang;
import facenet.car_management.facenet_car_db.KhachHang;
import facenet.car_management.repository.CskhRepository;
import facenet.car_management.repository.DonHangRepository;
import facenet.car_management.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CskhService {

    @Autowired
    CskhRepository cskhRepository;

    @Autowired
    DonHangRepository orderRepository;

    @Autowired
    KhachHangRepository customerRepository;
    public List<Object[]> getCskh() {
        //Lấy ra thời điểm 6 tháng trước
        LocalDate sixMonthsAgoLocalDate = LocalDate.now().minusMonths(6);
        Date sixMonthsAgo = Date.from(sixMonthsAgoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Query theo điều kiện
        return cskhRepository.getCskh(sixMonthsAgo);
    }

    public CskhDTO createCskhSchedule(CskhDTO cskhDTO) throws Exception {
        CSKH cskh = new CSKH();
        //Kiểm tra xem mã đơn hàng tồn tại không
        DonHang donHang = orderRepository.getOrdersByID(cskhDTO.getMaDonHang());
        if(donHang==null){
            throw new RuntimeException("Không tồn tại đơn hàng này");
        }
        //Set mã đơn hàng cho bản ghi cskh mới
        cskh.setMaDonHang(donHang);
        //Kiểm tra xem khách hàng tồn tại không
        KhachHang khachHang = customerRepository.getCustomerByCode(cskhDTO.getMaKhachHang());
        if(khachHang==null){
            throw new RuntimeException("Không tồn tại khách hàng này");
        }
        //Set mã khách hàng cho bản ghi cskh mới
        cskh.setMaKhachHang(khachHang);
        //Set date
        Date date =  Date.from(Instant.now());
        cskh.setNgay(date);
        //Cập nhật bản ghi và trả về kết quả
        cskh = cskhRepository.save(cskh);
        return new CskhDTO(cskh.getId(), cskh.getMaKhachHang().getMaKhachHang(), cskh.getMaDonHang().getMaDonHang(), cskh.getNgay());
    }

}
