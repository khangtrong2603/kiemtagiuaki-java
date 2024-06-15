package com.example.dinhtrongkhang.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    private String MaVN;

    @Column(name = "TENNV")
    private String TenNV;

    @Column(name="Phai", length = 5)
    private String Phai;

    @Column(name = "NoiSinh")
    private String NoiSinh;

    @Column(name="Luong")
    private int Luong;

    @ManyToOne
    @JoinColumn(name="MaPhong", referencedColumnName = "id")
    private PhongBan MaPhong;
}
