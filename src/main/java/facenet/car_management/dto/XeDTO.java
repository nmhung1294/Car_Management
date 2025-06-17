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
public class XeDTO {
    public XeDTO(String maXe, String hangXe, String model,
                 Date namSX, String phienBan, String color,
                 int price, Date ngayNhap, String tienIch ) {
        this.maXe = maXe;
        this.hangXe = hangXe;
        this.model = model;
        this.namSX = namSX;
        this.phienBan = phienBan;
        this.color = color;
        this.price = price;
        this.ngayNhap = ngayNhap;
        this.tienIch = tienIch;

    }
    //Thông tin chung
    private String maXe;
    private String hangXe;
    private String model;
    private Date namSX;
    private String phienBan;
    private String color;
    private int price;
    private Date ngayNhap;
    private String tienIch;

    //Thông tin kỹ thuật
    private String dongCo;
    private String hopSo;
    private String heDanDong;
    private String tieuThu;
    private String kichThuoc;
    private int trongLuong;

    // Thông tin trạng thái
    private String isUsed;
    private String soKM;
    private String tinhTrang;

    // Thông tin pháp lý
    private String soKhung;
    private String soMay;
    private String xuatXu;

}

