/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.imageprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Manasa
 */
public class UploadFile {

    private static final String Apath="/Users/karthik/NetBeansProjects/WeConnect/src/main/resources/images/";
    private static String Rpath=null;
   //private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);
    
    public static void addFile(HttpSession session, MultipartFile file,String imageURL) {
       
    Rpath=session.getServletContext().getRealPath("/images/");
      
 //   logger.info(Rpath);					
		System.out.println("reached upload="+imageURL);
		if(!new File(Rpath).exists()) {
			new File(Rpath).mkdirs();
		}
		
		if(!new File(Apath).exists()) {
			new File(Apath).mkdirs();
		}
       

        try {
            System.out.println("show path="+Apath+file.getOriginalFilename());
        //   file.transferTo(new File(Apath+file.getOriginalFilename()));
           file.transferTo(new File(Rpath+file.getOriginalFilename()));
       
          Files.copy(Paths.get(Rpath+file.getOriginalFilename()), Paths.get(Apath+file.getOriginalFilename()));
            System.out.println("Apath="+Apath);
        } catch (IOException ex) {
           ex.printStackTrace();
        }
           
     
    }

}
