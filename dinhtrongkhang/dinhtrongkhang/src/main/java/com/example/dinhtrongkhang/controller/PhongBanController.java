package com.example.dinhtrongkhang.controller;
import jakarta.validation.Valid;
import com.example.dinhtrongkhang.entity.PhongBan;
import com.example.dinhtrongkhang.service.PhongBanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongbanService;

    @GetMapping("/phongbans/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new PhongBan());
        return "/phongbans/add-category";
    }

    @PostMapping("/phongbans/add")
    public String addPhongBan(@Valid PhongBan phongban, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongbans/add-phongban";
        }
        phongbanService.addPhongBan(phongban);
        return "redirect:/phongbans";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/phongbans")
    public String listCategories(Model model) {
        List<PhongBan> phongbans = phongbanService.getAllPhongBan();
        model.addAttribute("phongbans", phongbans);
        return "/phongbans/phongbans-list";
    }
    // GET request to show category edit form
    @GetMapping("/phongbans/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PhongBan phongban = phongbanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phongban Id:"
                        + id));
        model.addAttribute("phongban", phongban);
        return "/phongbans/update-category";
    }
    // POST request to update category
    @PostMapping("/phongbans/update/{id}")
    public String updatePhongBan(@PathVariable("id") String id, @Valid PhongBan phongban,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongban.setMaPhong(id);
            return "/phongbans/update-phongban";
        }
        phongbanService.updatePhongBan(phongban);
        model.addAttribute("phongbans", phongbanService.getAllPhongBan());
        return "redirect:/phongbans";
    }
    // GET request for deleting category
    @GetMapping("/phongbans/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        PhongBan phongban = phongbanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        phongbanService.deletePhongBanById(id);
        model.addAttribute("phongbans", phongbanService.getAllPhongBan());
        return "redirect:/phongbans";
    }
}