package com.example.dinhtrongkhang.service;
import com.example.dinhtrongkhang.entity.PhongBan;
import com.example.dinhtrongkhang.repository.PhongBanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<PhongBan> getAllPhongBan() {
        return phongBanRepository.findAll();
    }
    /**
     * Retrieve a category by its id.

     * @return an Optional containing the found category or empty if not found
     */
    public Optional<PhongBan> getPhongBanById(String maPhong) {
        return phongBanRepository.findById(maPhong);
    }
    /**
     * Add a new category to the database.

     */
    public void addPhongBan(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }
    /**
     * Update an existing category.

     */
    public void updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingPhongBan = phongBanRepository.findById(phongBan.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        phongBan.getMaPhong() + " does not exist."));
        existingPhongBan.setTenPhong(phongBan.getTenPhong());
        phongBanRepository.save(existingPhongBan);
    }

    public void deletePhongBanById(String maPhong) {
        if (!phongBanRepository.existsById(maPhong)) {
            throw new IllegalStateException("Category with ID " + maPhong + " does not exist.");
        }
        phongBanRepository.deleteById(maPhong);
    }
}
