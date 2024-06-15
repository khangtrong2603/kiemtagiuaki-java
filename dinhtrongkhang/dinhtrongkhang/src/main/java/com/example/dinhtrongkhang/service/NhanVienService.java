package com.example.dinhtrongkhang.service;
import com.example.dinhtrongkhang.entity.NhanVien;
import com.example.dinhtrongkhang.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository NhanVienRepository;
    // Retrieve all products from the database
    public List<NhanVien> getAllNhanVien() {
        return NhanVienRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<NhanVien> getNhanVienById(String MaNV) {
        return NhanVienRepository.findById(MaNV);
    }
    // Add a new product to the database
    public NhanVien addNhanVien(NhanVien nhanVien) {
        return NhanVienRepository.save(nhanVien);
    }
    // Update an existing product
    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien) {
        NhanVien existingNhanVien = NhanVienRepository.findById(nhanVien.getMaVN())
                .orElseThrow(() -> new IllegalStateException("NhanVien with MaNV " +
                        nhanVien.getMaVN() + " does not exist."));
        existingNhanVien.setTenNV(nhanVien.getTenNV());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setMaPhong(nhanVien.getMaPhong());
        existingNhanVien.setLuong(nhanVien.getLuong());
        return NhanVienRepository.save(existingNhanVien);
    }
    // Delete a product by its id
    public void deleteNhanVienById(String maVN) {
        if (!NhanVienRepository.existsById(maVN)) {
            throw new IllegalStateException("Product with ID " + maVN + " does not exist.");
        }
        NhanVienRepository.deleteById(maVN);
    }
}
