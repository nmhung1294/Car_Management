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
public class ThuongHieuDTO {
    private String hangXe;
    private String moTa;
    private Date ngayHopTac;
}