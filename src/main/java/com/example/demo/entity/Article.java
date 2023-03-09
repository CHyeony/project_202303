package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_idx")
    private Long article_idx;

    @Column(name="slug")
    private String slug;

    @Column(name="title")
    private String title;

    @Column(name="body")
    private String body;

    @Column(name = "tagList")
    private String tagList;
// ################# 나중에보기 ##########################

    @Column(name = "createdAt")
    private Timestamp createdAt;

    @Column(name="updatedAt")
    private Timestamp updatedAt;

    @Column(name = "favorited")
    private boolean favorited;

    @Column(name = "favorited_count")
    private Long favorited_count;


    @Builder
    public Article(Long article_idx, String slug, String title, String body, String tagList
    , Timestamp createdAt, Timestamp updatedAt, boolean favorited,
                   Long favorited_count){

        this.article_idx = article_idx;
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favorited_count = favorited_count;



    }

}
