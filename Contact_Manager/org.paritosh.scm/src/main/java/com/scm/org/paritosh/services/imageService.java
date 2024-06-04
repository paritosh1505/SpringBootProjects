package com.scm.org.paritosh.services;


import org.springframework.web.multipart.MultipartFile;

public interface imageService {
 
    String UploadImage(MultipartFile pictureContact) ;

    String getUrlFromId(String id);

}
