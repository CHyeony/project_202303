package com.example.demo.service;

import com.example.demo.dto.LikeDTO;
import com.example.demo.entity.Article;
import com.example.demo.entity.Like;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LIkeService {

    @Autowired
    private LikeRepository likeRepository;
    private ArticleRepository articleRepository;
    private UserAccountRepository userAccountRepository;

    public Boolean onLike(LikeDTO likeDTO, Long userId, Long ArticleId){
        Article article = articleRepository.findById(ArticleId)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
        Optional<Like> liked = likeRepository.findByArticleUser(article,userAccount);

        if(liked!=null){return false;}

        likeDTO.setUser_id(userId);
        likeDTO.setArticle_id(ArticleId);
        // ERROR 수정필요 모르겠당///////
        likeRepository.save(liked);
        return true;
    }

    public Boolean offLike(LikeDTO likeDTO, Long userId, Long ArticleId){
        Article article = articleRepository.findById(ArticleId)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));

        Optional<Like> liked = likeRepository.findByArticleUser(article,userAccount);
        if(liked==null){return false;}
        // 여기도 모르게따///////
        likeRepository.delete(liked);
    }



}
