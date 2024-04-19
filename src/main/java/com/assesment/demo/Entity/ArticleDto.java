package com.assesment.demo.Entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.Date;

@Data
public class ArticleDto {

    private Long id;
    private String title;
    private String authors;
    private String publicUrl;
    private Date articleDate;
    private String accessCategory;
    private Date freeViewExpiry;
    private String description;
    private MultipartFile featuredImage;


}
