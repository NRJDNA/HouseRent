package com.example.houserentingsystem.component;

import com.example.houserentingsystem.dto.ImageDto;
import com.example.houserentingsystem.dto.user.UserRoomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

/**
 * @author dangal_nirajan on 14/08/2022
 */

@Slf4j
@Component
public class FileStoring {
    public ImageDto storeFile(MultipartFile multipartFile) throws IOException{
            String directoryPath = System.getProperty("user.home")+ File.separator + "Desktop" + File.separator + "HouseRentingPhoto";
            File directoryFile = new File(directoryPath);
            if(!directoryFile.exists()){
                directoryFile.mkdirs();
            }
            else {
                log.info("Folder Already Exists");
            }
            String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")){
                UUID uuid= UUID.randomUUID();
                String filePath = directoryPath + File.separator + uuid + "_" + multipartFile.getOriginalFilename();
                File fileToStore = new File(filePath);
                multipartFile.transferTo(fileToStore);

                return ImageDto.builder()
                        .status(true)
                        .message(filePath)
                        .build();
            }else {
                return ImageDto.builder()
                        .status(false)
                        .message("Invalid extension !!! use(jpg,png,jpeg Only)")
                        .build();
            }
    }
    public String returnFileAsBase64(String filePath) throws IOException{
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        String base64EncodedImage = ("date:image/png;base64, " + Base64.getEncoder().encodeToString(bytes));
        return base64EncodedImage;

    }
}
