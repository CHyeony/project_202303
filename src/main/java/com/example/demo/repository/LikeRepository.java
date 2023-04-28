package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Like;
import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository <Like,Long> {
    Optional<Like> findByArticleUser(Article article, UserAccount userAccount);

}
