package facenet.car_management.facenet_car_db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Xe")
public class Xe {
    public Xe(String maXe, String hangXe, String model,
              Date namSX, String phienBan, String color,
              int price, Date ngayNhap, String tienIch ) {
        this.maXe = maXe;
        this.hangXe = new ThuongHieu();
        this.hangXe.setHangXe(hangXe);
        this.model = model;
        this.namSX = namSX;
        this.phienBan = phienBan;
        this.color = color;
        this.price = price;
        this.ngayNhap = ngayNhap;
        this.tienIch = tienIch;

    }
    @Id
    @Column(name="maXe")
    private String maXe;
    @ManyToOne
    @JoinColumn(name="hangXe")
    private ThuongHieu hangXe;

    @Column(name="model")
    private String model;
    @Column(name="namSX")
    private Date namSX;
    @Column(name="phienBan")
    private String phienBan;
    @Column(name="mau")
    private String color;
    @Column(name="gia")
    private int price;
    @Column(name="ngayNhap")
    private Date ngayNhap;
    @Column(name="tienIch")
    private String tienIch;

    //Thông tin kỹ thuật
    @Column(name="dongCo")
    private String dongCo;
    @Column(name="hopSo")
    private String hopSo;
    @Column(name="heDanDong")
    private String heDanDong;
    @Column(name="tieuThu")
    private String tieuThu;
    @Column(name="kichThuoc")
    private String kichThuoc;
    @Column(name="trongLuong")
    private int trongLuong;

    // Thông tin trạng thái
    @Column(name="quaSuDung")
    private String isUsed;
    @Column(name="soKM")
    private String soKM;
    @Column(name="tinhTrang")
    private String tinhTrang;

    // Thông tin pháp lý
    @Column(name="soKhung")
    private String soKhung;
    @Column(name="soMay")
    private String soMay;
    @Column(name="suatSu")
    private String xuatXu;

    @OneToMany
    @JoinColumn(name = "maXe")
    private List<DonHang> listXeDonHang;

    @OneToMany
    @JoinColumn(name="maXe")
    private List<Image> listXeImg;
}

