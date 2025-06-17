package facenet.car_management.facenet_car_db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="CSKH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CSKH {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="maDonHang")
    private DonHang maDonHang;

    @ManyToOne
    @JoinColumn(name="maKhachHang")
    private KhachHang maKhachHang;

    @Column(name="ngay")
    private Date ngay;
}

