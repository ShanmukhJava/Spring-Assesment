package com.assesment.demo.repository;

import com.assesment.demo.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Article,Long> {

    public Article findByAurthors(String name);
}
