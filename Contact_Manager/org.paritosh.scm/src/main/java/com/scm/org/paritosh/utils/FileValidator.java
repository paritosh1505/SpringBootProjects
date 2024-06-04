package com.scm.org.paritosh.utils;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<FileValidatorAnnotation,MultipartFile>{
public static final long MAX_FILE_SIZE=1024*1024*2;//1mb
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext arg1) {
        if(file==null || file.isEmpty()){
           arg1.disableDefaultConstraintViolation();
           arg1.buildConstraintViolationWithTemplate("File cannot be empty").addConstraintViolation();
            return false;
        }
        if(file.getSize()>MAX_FILE_SIZE){
            arg1.disableDefaultConstraintViolation();
           arg1.buildConstraintViolationWithTemplate("File size is  should be less than 5mb").addConstraintViolation();
           return false;
        }

        return true;
    }

}
