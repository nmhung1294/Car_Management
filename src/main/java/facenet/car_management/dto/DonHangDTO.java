package facenet.car_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonHangDTO {
    private String maDonHang;
    private String maNV;
    private String maKhachHang;
    private String maXe;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date ngay;
    private Integer status;

    public DonHangDTO(String maDonHang, String maKhachHang, Date ngay, String maXe, String maNV, Integer status) {
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.maNV = maNV;
        this.maXe = maXe;
        this.ngay = ngay;
        this.status = status;
    }

    public DonHangDTO() {

    }
}
