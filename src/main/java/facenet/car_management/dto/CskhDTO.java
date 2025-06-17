package facenet.car_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CskhDTO {
    private int id;
    private String maKhachHang;
    private String maDonHang;
    private Date ngay;
}