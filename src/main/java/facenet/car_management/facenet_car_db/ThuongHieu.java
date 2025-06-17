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
@Table(name="ThuongHieu")
public class ThuongHieu {
    @Id
    @Column(name="hangXe")
    private String hangXe;
    @Column(name="moTa")
    private String moTa;
    @Column(name="ngayHopTac")
    private Date ngayHopTac;

    //Set rela with Image by hangXe in Image tbl
    @OneToMany
    @JoinColumn(name = "hangXe")
    private List<Image> listBrandImg;
    //Set rela with Xe by hangXe in Xe tbl
    @OneToMany
    @JoinColumn(name="hangXe")
    private List<Xe> listCar;

}

