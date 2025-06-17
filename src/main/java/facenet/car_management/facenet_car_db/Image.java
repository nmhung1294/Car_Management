package facenet.car_management.facenet_car_db;

import facenet.car_management.dto.ThuongHieuDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name="maNV", referencedColumnName = "maNV")
    private NhanSu nhanSu;

    @ManyToOne
    @JoinColumn(name="hangXe", referencedColumnName = "hangXe")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name="maXe", referencedColumnName = "maXe")
    private Xe maXe;

    @Column(name="path")
    private String path;
}

