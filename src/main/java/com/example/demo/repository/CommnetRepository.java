package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommnetRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long article_id);

}
