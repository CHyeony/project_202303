package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleLike;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleLikeRepository;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {

    private final ArticleLikeRepository articleLikeRepository;

    private final ArticleRepository articleRepository;

    private final UserAccountRepository userAccountRepository;

    @Transactional
    public ArticleDto onLike(long userId, String articleSlug){
        UserAccount userAccount = userAccountRepository.findById(userId)
            .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
        Article article = articleRepository.findBySlug(articleSlug)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

        ArticleLike liked = articleLikeRepository.findByArticleAndUserAccount(article, userAccount)
            .orElse(null);
        if (liked != null) {
            throw new BusinessException(ErrorCode.LIKE_ALREADY_EXISTS);
        }

        ArticleLike articleLike = ArticleLike.builder()
            .userAccount(userAccount)
            .article(article)
            .build();
        articleLikeRepository.save(articleLike);

        return ArticleDto.toArticleDto(article);
    }

    @Transactional
    public ArticleDto offLike(long userId, String articleSlug){
        UserAccount userAccount = userAccountRepository.findById(userId)
            .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
        Article article = articleRepository.findBySlug(articleSlug)
            .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
        ArticleLike liked = articleLikeRepository.findByArticleAndUserAccount(article, userAccount)
            .orElseThrow(() -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));
        articleLikeRepository.delete(liked);
        return ArticleDto.toArticleDto(article);
    }
// 좋아요 개수반환
    public int likeCount(String articleSlug){
        Article article = articleRepository.findBySlug(articleSlug)
                .orElseThrow(()-> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

        List<ArticleLike> articleLike = articleLikeRepository.findByArticleId(articleSlug);

        return articleLike.size();
    }
}
