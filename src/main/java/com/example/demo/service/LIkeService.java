package com.example.demo.service;

import com.example.demo.dto.LikeDTO;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleLike;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ArticleLikeRepository;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LIkeService {

    @Autowired
    private ArticleLikeRepository articleLikeRepository;
    private ArticleRepository articleRepository;
    private UserAccountRepository userAccountRepository;

    public Boolean onLike(LikeDTO likeDTO, Long userId, Long ArticleId){
        Article article = articleRepository.findById(ArticleId)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
        ArticleLike liked = articleLikeRepository.findByArticleAndUserAccount(article,userAccount)
            .orElse(null);
        if (liked == null) {
            throw new BusinessException(ErrorCode.LIKE_ALREADY_EXISTS);
        }

        likeDTO.setUser_id(userId);
        likeDTO.setArticle_id(ArticleId);
        articleLikeRepository.save(liked);
        return true;
    }

    public Boolean offLike(LikeDTO likeDTO, Long userId, Long ArticleId){
        Article article = articleRepository.findById(ArticleId)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));

        ArticleLike liked = articleLikeRepository.findByArticleAndUserAccount(article,userAccount)
            .orElseThrow(() -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));
        if(liked==null){return false;}
        articleLikeRepository.delete(liked);

        return true;
    }



}
