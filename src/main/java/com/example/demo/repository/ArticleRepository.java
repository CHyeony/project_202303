package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	Optional<Article> findBySlug(String slug);
}
