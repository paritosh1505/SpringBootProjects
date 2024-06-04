package com.scm.org.paritosh.implementation;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.org.paritosh.services.imageService;

@Service
public class ImageServiceimpl implements imageService {
   private Cloudinary cloudinary;
    public ImageServiceimpl(Cloudinary cloud){
        this.cloudinary=cloud;
}
    @Override
    public String UploadImage(MultipartFile pictureContact)  {
      
       String file_name = UUID.randomUUID().toString();
       try {    
        byte[] dataimage = new byte[pictureContact.getInputStream().available()];
        pictureContact.getInputStream().read(dataimage);
        cloudinary.uploader().upload(dataimage, ObjectUtils.asMap(
            "public_id", file_name
           ));
           return this.getUrlFromId(file_name);
    
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
      
      
      
        
    }
    @Override
    public String getUrlFromId(String id) {
       return cloudinary.url().transformation(
        new Transformation<>()
        .width(300)
        .height(300)
       ).generate(id);
    }

}
