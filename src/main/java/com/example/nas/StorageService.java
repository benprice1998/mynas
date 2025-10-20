package com.example.nas;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StorageService {

    public DriveInfo getDriveInfo() {
        File root = new File("/"); // On Linux/Unix or macOS
        // For Windows, you can use: new File("C:\\")

        long totalSpace = root.getTotalSpace(); // bytes
        long freeSpace = root.getFreeSpace();   // bytes
        long usableSpace = root.getUsableSpace(); // bytes

        return new DriveInfo(totalSpace, freeSpace, usableSpace);
    }

    public static class DriveInfo {
        private long total;
        private long free;
        private long usable;

        public DriveInfo(long total, long free, long usable) {
            this.total = total;
            this.free = free;
            this.usable = usable;
        }

        public long getTotal() {
            return total;
        }

        public long getFree() {
            return free;
        }

        public long getUsable() {
            return usable;
        }
    }
}
