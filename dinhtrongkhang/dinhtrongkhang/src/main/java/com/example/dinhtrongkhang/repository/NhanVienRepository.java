package com.example.dinhtrongkhang.repository;

import com.example.dinhtrongkhang.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
}
