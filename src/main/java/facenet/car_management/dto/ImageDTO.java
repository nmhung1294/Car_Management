package facenet.car_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private int id;
    private String hangXe;
    private String maXe;
    private String maNV;
    private String path;

    public ImageDTO(String hangXe, String path) {
        this.hangXe = hangXe;
        this.path = path;
    }

//    public ImageDTO(String maNV, String path){
//        this.maNV = maNV;
//        this.path = path;
//    }
//
//    public ImageDTO ImageCarDTO(String maXe, String path){
//        this.maXe = maXe;
//        this.path = path;
//    }

}