package com.assesment.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String authors;

    @Column
    private String publicUrl;

    @Column
    private Date articleDate;

    @Column
    private String accessCategory;
    @Column
    private Date freeViewExpiry;

    @Column
    private String description;

//    @Column
//    private MultipartFile featuredImage;
//

    @Lob
    private byte[] featuredImage;

    public Article(Long id, String title, String authors, String publicUrl, Date articleDate, String accessCategory, Date freeViewExpiry, String description) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publicUrl = publicUrl;
        this.articleDate = articleDate;
        this.accessCategory = accessCategory;
        this.freeViewExpiry = freeViewExpiry;
        this.description = description;

    }


}
