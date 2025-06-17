package facenet.car_management.facenet_car_db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="DonHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonHang {
    public DonHang(String maDonHang, KhachHang khachHang, Date ngay, Xe xe, NhanSu nhanSu, int trangThai ) {
        this.maDonHang = maDonHang;
        this.maKhachHang = khachHang;
        this.ngay = ngay;
        this.maXe = xe;
        this.maNV = nhanSu;
        this.status = trangThai;
    }
    @Id
    private String maDonHang;

    @OneToMany(mappedBy = "maDonHang")
    private List<CSKH> listCSKH;

    @ManyToOne
    @JoinColumn(name="maXe")
    private Xe maXe;

    @ManyToOne
    @JoinColumn(name="maNV", referencedColumnName = "maNV")
    private NhanSu maNV;

    @ManyToOne
    @JoinColumn(name="maKhachHang")
    private KhachHang maKhachHang;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    @Column(name="ngay")
    private Date ngay;

    @Column(name="trangThai")
    private Integer status;
}

