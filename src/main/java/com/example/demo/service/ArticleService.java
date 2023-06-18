package com.example.demo.service;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleLikeRepository;
import com.example.demo.repository.CommentRepository;
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

	private final ArticleLikeRepository articleLikeRepository;

	private final CommentRepository commentRepository;

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
	public ArticleDto update(ArticleDto articleDto, long userId) {
		UserAccount user = userAccountRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		String slug = articleDto.getSlug();
		Article article = articleRepository.findBySlug(slug)
			.orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

		if (!user.equals(article.getAuthor())) {
			throw new BusinessException(ErrorCode.ARTICLE_NOT_MINE);
		}

		if (articleDto.getTitle() != null) {
			article.changeTitle(articleDto.getTitle());
		}

		if (articleDto.getDescription() != null) {
			article.changeDescription(articleDto.getDescription());
		}

		if (articleDto.getBody() != null) {
			article.changeBody(articleDto.getBody());
		}

		return ArticleDto.toArticleDto(article);
	}

	@Transactional
	public void delete(String slug, long userId) {
		UserAccount user = userAccountRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Article article = articleRepository.findBySlug(slug)
			.orElseThrow(() -> new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));


		if (!user.equals(article.getAuthor())) {
			throw new BusinessException(ErrorCode.ARTICLE_NOT_MINE);
		}

		articleRepository.delete(article);
	}

	@Transactional(readOnly = true)
	public ArticleDto selectArticle(long userId, String slug){
		UserAccount user = userAccountRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		Article article = articleRepository.findBySlug(slug)
				.orElseThrow(()->new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
		articleRepository.save(article);

		boolean favorited = articleLikeRepository.findByArticleAndUserAccount(article, user)
			.isPresent();
		int favoritesCount = articleLikeRepository.countByArticleId(article.getId());

		ArticleDto articleDto = ArticleDto.toArticleDto(article);
		articleDto.setFavorited(favorited);
		articleDto.setFavoritesCount(favoritesCount);

		return articleDto;
	}

	@Transactional(readOnly = true)
	public ArticleDto selectArticle(String slug){
		Article article = articleRepository.findBySlug(slug)
			.orElseThrow(()->new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
		articleRepository.save(article);

		int favoritesCount = articleLikeRepository.countByArticleId(article.getId());

		ArticleDto articleDto = ArticleDto.toArticleDto(article);
		articleDto.setFavorited(false);
		articleDto.setFavoritesCount(favoritesCount);

		return articleDto;
	}


	@Transactional
	public CommentDTO addComment(CommentDTO commentDTO, long userId, String slug){
		Article article = articleRepository.findBySlug(slug)
				.orElseThrow(()->new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));

		UserAccount user = userAccountRepository.findById(userId)
				.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

	Comment comment = Comment.builder()
			.comId(commentDTO.getId())
			.body(commentDTO.getBody())
			.article(article)
			.build();
		return CommentDTO.toCommentDto(comment);

	}
}

