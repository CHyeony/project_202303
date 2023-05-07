package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final UserAccountRepository userAccountRepository;

	private final ArticleRepository articleRepository;

	@Transactional
	public ArticleDto create(ArticleDto articleDto, long userId) {
		UserAccount user = userAccountRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		Article article = ArticleDto.toArticle(articleDto, user);
		articleRepository.save(article);
		article.createSlug();

		return ArticleDto.toArticleDto(article);
	}

	@Transactional
	public ArticleDto selectArticle(String slug){
		Article article = articleRepository.findBySlug(slug)
				.orElseThrow(()->new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
		articleRepository.save(article);

		return ArticleDto.toArticleDto(article);
	}
}
