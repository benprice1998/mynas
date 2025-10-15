package com.example.nas;

import org.springframework.stereotype.Service;

@Service
class fileReaderService {

    public String readFile(String fileName) {
        System.out.println("Reading file: " + fileName);
        return "";
    }


}
