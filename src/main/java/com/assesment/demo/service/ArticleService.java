package com.assesment.demo.service;

import com.assesment.demo.Entity.Article;
import com.assesment.demo.Entity.ArticleDto;
import com.assesment.demo.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {


    private static final Logger logger = LogManager.getLogger(ArticleService.class);


    private  Repository articleRepository;
    private EmailService emailService;
    public ArticleService(Repository repository,EmailService emailService){
        this.articleRepository = repository;
        this.emailService = emailService;

    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));
    }

    public Article addArticle(Article article) {

        Article savedArticle  =articleRepository.save(article);
        emailService.sendEmail("recipient@example.com", "New Article Added", "A new article has been added: " + savedArticle.getTitle());
        return savedArticle;

    }

    public Article updateArticle(Long id, ArticleDto updatedArticle) throws IOException {

        Optional<Article> opitionalArticle = articleRepository.findById(id);
        if (opitionalArticle.isPresent()) {
            Article existingArticle = opitionalArticle.get();
            existingArticle = getArticleById(id);
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setAuthors(updatedArticle.getAuthors());
            existingArticle.setPublicUrl(updatedArticle.getPublicUrl());
            existingArticle.setArticleDate(updatedArticle.getArticleDate());
            existingArticle.setDescription(updatedArticle.getDescription());
            existingArticle.setFreeViewExpiry(updatedArticle.getFreeViewExpiry());
            existingArticle.setAccessCategory(updatedArticle.getAccessCategory());
            existingArticle.setFeaturedImage(updatedArticle.getFeaturedImage().getBytes());
            emailService.sendEmail("recipient@example.com", "Article Updated", "An article has been updated: " + updatedArticle.getTitle());
            return articleRepository.save(existingArticle);
        }else{
            throw new FileNotFoundException("Article with ID " + id + " not found");
        }

    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
        emailService.sendEmail("recipient@example.com", "Article Deleted", "An article has been deleted: ");
    }
    public Article convertToEntity(ArticleDto articleDTO) throws IOException {

        Article article = new Article();

        article.setArticleDate(articleDTO.getArticleDate());
        article.setAuthors(articleDTO.getAuthors());
        article.setDescription(articleDTO.getDescription());
        article.setTitle(articleDTO.getTitle());
        article.setAccessCategory(articleDTO.getAccessCategory());
        article.setFreeViewExpiry(articleDTO.getFreeViewExpiry());
        article.setPublicUrl(articleDTO.getPublicUrl());
        MultipartFile featuredImage = articleDTO.getFeaturedImage();
        if (featuredImage != null && !featuredImage.isEmpty()) {
            article.setFeaturedImage(featuredImage.getBytes());
        }
        return article;
    }
}
