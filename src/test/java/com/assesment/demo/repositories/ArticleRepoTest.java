package com.assesment.demo.repositories;

import com.assesment.demo.Entity.Article;
import com.assesment.demo.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleRepoTest {

    private Repository articleRepo;

    @Test
    @Order(1)
    public void saveArticle(){
        Article article = new Article(1L, "GiveandTry", "Shannu",
                 "http://example.com/article",
                 2024-04-19,
                 "Premium",
                 2024-05-19,
                "This is a sample article description.");

        articleRepo.save(article);

        Assertions.assertThat(article.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getArticle(){
        Article Article = articleRepo.findById(1L).get();

        Assertions.assertThat(Article.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getAllArticles(){
        List<Article> list = articleRepo.findAll();

        Assertions.assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void upDateArticle(){
        Article article = articleRepo.findById(1L).get();
        article.setAuthors("Shanmukh");
        Article updated = articleRepo.save(article);

        Assertions.assertThat(updated.getAuthors()).isEqualTo("Shanmukh");
    }

    @Test
    @Order(5)
    public void deleteArticle(){
        Optional<Article> articleOptional = articleRepo.findById(1L);
        Assertions.assertThat(articleOptional).isPresent();

        Article article = articleOptional.get();
        articleRepo.delete(article);

        Optional<Article> deletedArticleOptional = articleRepo.findById(1L);
        Assertions.assertThat(deletedArticleOptional).isEmpty();
    }

}
