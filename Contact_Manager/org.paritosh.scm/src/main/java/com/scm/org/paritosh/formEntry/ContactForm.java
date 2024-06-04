package com.scm.org.paritosh.formEntry;

import org.springframework.web.multipart.MultipartFile;

import com.scm.org.paritosh.utils.FileValidatorAnnotation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactForm {
    @NotBlank(message = "user name cannot be blank")
    private String contactName;
    @NotBlank(message="Email cannot be blank")
    private String emailContact;
    private String address;
    private boolean fav;
    private String descContact;
    private String websiteLink;
    private String phonenoContact;
    
    @FileValidatorAnnotation
    private MultipartFile pictureContact;

}
