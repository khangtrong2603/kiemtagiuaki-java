package com.example.dinhtrongkhang.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="PHONGBAN")
public class PhongBan {
    @Id
    private String MaPhong;
    @Column(name="TenPhong")
    private String TenPhong;
    @OneToMany(mappedBy="PHONGBAN", cascade = CascadeType.ALL)
    private List<NhanVien> nhanViens;
}
