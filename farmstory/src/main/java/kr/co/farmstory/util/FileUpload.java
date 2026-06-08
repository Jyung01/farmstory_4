package kr.co.farmstory.util;

import java.io.File;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUpload {

    public static String uploadFile(HttpServletRequest req, Part part) {

        try {
        	String uploadPath = req.getServletContext()
                    .getRealPath("/upload/article");
        	
        	System.out.println(uploadPath);

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String oriName = part.getSubmittedFileName();

            String ext = oriName.substring(oriName.lastIndexOf("."));

            String newName = UUID.randomUUID() + ext;

            part.write(uploadPath+ File.separator + newName);

            return newName;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return null;
    }
}