package facenet.car_management.facenet_car_db;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="NhanSu")
public class NhanSu {
    @Id
    @Column(name="maNV")
    private String maNV;
    @Column(name="hoTen")
    private String name;
    @Column(name="ngaySinh")
    private Date birthDate;
    @Column(name="ngayVao")
    private Date dayIn;
    @Column(name="ngayRa")
    private Date dayOut;
    @Column(name="chucVu")
    private String role;
    @Column(name="diaChi")
    private String address;
    @OneToOne
    @JoinColumn(name="maNV")
    private Image nvImg;
    @OneToMany
    @JoinColumn(name="maNV")
    private List<DonHang> listNvDonHang;
}

