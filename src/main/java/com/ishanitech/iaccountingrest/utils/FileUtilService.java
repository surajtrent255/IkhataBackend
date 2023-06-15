package com.ishanitech.iaccountingrest.utils;

import com.ishanitech.iaccountingrest.config.properties.FileStorageProperties;
import com.ishanitech.iaccountingrest.exception.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class FileUtilService {

    private final Path storageLocation;

    public FileUtilService(FileStorageProperties storageProperties) {
        this.storageLocation = Paths.get(storageProperties.getUpload().getDirectory()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageLocation);
        } catch(IOException ex) {
            throw new FileStorageException("Couldn't create directory to store your file!");
        }
    }

//    public String storeFile(MultipartFile image) {
//        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//
//        try {
//            if(fileName.contains("..")) {
//                throw new FileStorageException(String.format("Sorry your file %s contains invalid characters for pathname.!", fileName));
//            }
//            Path targetLocation = this.storageLocation.resolve(fileName);
//            Files.copy(image.getInputStream(), targetLocation);
//            return fileName;
//        } catch(IOException ex) {
//            throw new FileStorageException(String.format("Couldn't store the file %s!", fileName));
//        }
//    }

    public String storeFile(MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException(String.format("Sorry, your file %s contains invalid characters for a pathname!", fileName));
            }


            InputStream is = new ByteArrayInputStream(image.getBytes());
            BufferedImage originalImage = ImageIO.read(is);


            BufferedImage resizedImage = resizeImage(originalImage, 99, 99);


            String resizedFileName =  fileName;
            Path targetLocation = this.storageLocation.resolve(resizedFileName);
            ImageIO.write(resizedImage, "jpg", targetLocation.toFile());

            return resizedFileName;
        } catch (IOException ex) {
            throw new FileStorageException(String.format("Couldn't store the file %s!", fileName));
        }
    }


    @Transactional
    public void deleteFile(String fileName)
    {
        try {
            File file = new File(this.storageLocation.resolve(fileName).toString());
            log.info(file.getName());
            if(file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Failed to Delete image !!");
        }
    }

    public Boolean checkIfFileExists(String imageName){

            File file = new File(this.storageLocation+imageName);
            boolean exist = file.exists();
             return exist;
    }


    public void saveImageForIcon(String fileName, MultipartFile image) {

        Path nextTargetLocation = this.storageLocation.resolve("resized/" + fileName);

        try {

            //for resizing image

            InputStream is = new ByteArrayInputStream(image.getBytes());
            BufferedImage bi = ImageIO.read(is);

            BufferedImage img = resizeImage(bi, 99, 99);

            //converting buffered image to multipart

            File outputfile = new File(nextTargetLocation.toString());
            if (!outputfile.exists()){
                outputfile.mkdirs();
            }
            ImageIO.write(img, "png", outputfile);

            //conversion to multipart ends

            //img.transferTo(nextTargetLocation);
        } catch (IllegalStateException e) {
            log.error("Exception: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("Exception: {}", e.getLocalizedMessage());
        }

    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }


    public Resource load(String filename) {
        try {
            Path file = storageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public InputStream getResource(String path,String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }


}
