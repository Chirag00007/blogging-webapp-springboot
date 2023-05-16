package com.chirag.main.services.Impl;

import com.chirag.main.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FIleServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name
        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();




        String finalPath = randomId.concat(name.substring(name.lastIndexOf(".")));
        //full path
        String fullPath = path + "/" + finalPath;
                //create folder if not creat
                File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        //file copy

        Files.copy(file.getInputStream(), Paths.get(fullPath));

        return finalPath;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
       String fullpath = path + "/" + fileName;
       InputStream inputStream = new FileInputStream(fullpath);
         return inputStream;
    }
}
