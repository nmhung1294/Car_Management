package facenet.car_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangDTO {
    private String maKhachHang;
    private String name;
    private String phoneNum;
    private String email;
    private String diaChi;
}
