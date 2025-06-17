package facenet.car_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanSuDTO {
    private String maNV;
    private String name;
    private Date birthDate;
    private Date dayIn;
    private Date dayOut;
    private String role;
    private String address;
}
