package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Article;
import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
 Optional<Article> findBySlug(String slug);

	Optional<Article> findBySlug(String slug);
}
