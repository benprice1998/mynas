package com.example.nas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/home")
    public String homePage(Model model) {
        StorageService.DriveInfo driveInfo = storageService.getDriveInfo();

        model.addAttribute("total", driveInfo.getTotal());
        model.addAttribute("free", driveInfo.getFree());
        model.addAttribute("usable", driveInfo.getUsable());

        return "home"; // Refers to home.html in templates/
    }
}
