package com.assesment.demo.controller;

import com.assesment.demo.Entity.Article;
import com.assesment.demo.Entity.ArticleDto;
import com.assesment.demo.service.ArticleService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LogManager.getLogger(ArticleController.class);
    private ArticleService articleService;
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;

    }


    @PostMapping("/postArticle")
    public ResponseEntity<Article> postArticle(@ModelAttribute ArticleDto articleDto ,@RequestParam("featuredImage") MultipartFile featuredImage) throws IOException {
        try {
            articleDto.setFeaturedImage(featuredImage);
            Article article = articleService.convertToEntity(articleDto);
            Article savedArticle = articleService.addArticle(article);
            return ResponseEntity.ok(savedArticle);
        }catch (IOException e) {
            logger.error("Error occurred while converting ArticleDto to Entity or while saving the Article into database: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("/getArticleById/{id}")
    public ResponseEntity<Object> getArticleById(@PathVariable  Long id){

        try {
            return ResponseEntity.ok(articleService.getArticleById(id));
        } catch (EntityNotFoundException e) {
            logger.error("Article with id " + id + " not found", e);
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateArticleById/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        try {
            Article updatedArticle = articleService.updateArticle(id, articleDto);
            return ResponseEntity.ok(updatedArticle);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/deleteArticleById/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("/getArticles")
    public List<Article>  getArticles() {
        return  articleService.getAllArticles();
    }



}
