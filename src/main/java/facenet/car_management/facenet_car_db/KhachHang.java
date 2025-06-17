package facenet.car_management.facenet_car_db;

import facenet.car_management.dto.NhanSuDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="KhachHang")
public class KhachHang {
    public KhachHang(String maKhachHang, String name, String email, String phoneNum, String diaChi) {
        this.maKhachHang = maKhachHang;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.diaChi = diaChi;
    }
    @Id
    @Column(name="maKhachHang")
    private String maKhachHang;
    @Column(name="hoTen")
    private String name;
    @Column(name="sdt")
    private String phoneNum;
    @Column(name="email")
    private String email;
    @Column(name="diaChi")
    private String diaChi;
    //Mapped by maKH tu bang DonHang
    @OneToMany
    @JoinColumn(name="maKhachHang")
    private List<DonHang> listDonHang;

    @OneToMany
    @JoinColumn(name="maKhachHang")
    private List<CSKH> listCSKH;

}

