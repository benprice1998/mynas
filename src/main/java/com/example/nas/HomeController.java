package com.example.nas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StorageService storageService;
        DecimalFormat numberFormat = new DecimalFormat("#.00");


    @GetMapping("/home")
    public String homePage(Model model) {
        StorageService.DriveInfo info = storageService.getDriveInfo();

        long totalBytes = info.getTotal();
        long freeBytes = info.getFree();
        long usedBytes = totalBytes - freeBytes;

        double totalGB = bytesToGigabytes(totalBytes);
        double freeGB = bytesToGigabytes(freeBytes);
        double usedGB = bytesToGigabytes(usedBytes);

        double percentUsed = (usedGB / totalGB) * 100;
        model.addAttribute("percentUsed", percentUsed);


        model.addAttribute("totalRaw", totalGB);
        model.addAttribute("freeRaw", freeGB);
        model.addAttribute("usedRaw", usedGB);

        model.addAttribute("total", numberFormat.format(totalGB));
        model.addAttribute("free", numberFormat.format(freeGB));
        model.addAttribute("used", numberFormat.format(usedGB));

        List<String> partitions = getMountedPartitions();
        model.addAttribute("partitions", partitions);

        return "home";
    }

    private double bytesToGigabytes(long bytes) {
        return bytes / 1024.0 / 1024.0 / 1024.0;
    }

    private List<String> getMountedPartitions() {
        List<String> mounts = new ArrayList<>();
        File[] roots = File.listRoots();
        for (File root : roots) {
            mounts.add(root.getAbsolutePath());
        }
        return mounts;
    }
}
