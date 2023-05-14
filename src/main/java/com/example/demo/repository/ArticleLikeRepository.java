package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleLike;
import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository <ArticleLike,Long> {
    Optional<ArticleLike> findByArticleAndUserAccount(Article article, UserAccount userAccount);
    List<ArticleLike> findByArticleId(String slug);
}
