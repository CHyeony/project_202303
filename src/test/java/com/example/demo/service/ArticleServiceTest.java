package com.example.demo.service;

import static org.mockito.ArgumentMatchers.*;

import java.util.Optional;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserAccountRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

	@InjectMocks
	private ArticleService articleService;

	@Mock
	private UserAccountRepository userAccountRepository;

	@Mock
	private ArticleRepository articleRepository;

	@Test
	@DisplayName("본인이 작성한 게시물이 아닌 게시물을 수정하려고 할 때")
	public void updateArticleThenThrowsArticleNotMine() {
		// given
		long userId = 1L;
		UserAccount userAccount = UserAccount.builder()
			.id(userId)
			.email("chaeyeon@naver.com")
			.username("chy")
			.build();
		BDDMockito.given(userAccountRepository.findById(eq(userId)))
			.willReturn(Optional.of(userAccount));

		Article article = Article.builder()
			.id(1L)
			.title("article title")
			.author(
				UserAccount.builder()
					.id(2L)
					.email("geonhong@naver.com")
					.username("lgh")
					.build()
			)
			.build();
		article.createSlug();
		BDDMockito.given(articleRepository.findBySlug(eq(article.getSlug())))
			.willReturn(Optional.of(article));

		ArticleDto articleDto = ArticleDto.builder()
			.slug(article.getSlug())
			.title("changed title")
			.build();

		try {
			// when
			articleService.update(articleDto, userId);
			BDDAssertions.fail("");
		} catch (BusinessException e) {
			// then
			BDDAssertions.assertThat(e.getErrorCode()).isEqualTo(ErrorCode.ARTICLE_NOT_MINE);
		}
	}

	@Test
	@DisplayName("게시글 수정")
	public void updateArticle() {
		// given
		long userId = 1L;
		UserAccount userAccount = UserAccount.builder()
			.id(userId)
			.email("chaeyeon@naver.com")
			.username("chy")
			.build();
		BDDMockito.given(userAccountRepository.findById(eq(userId)))
			.willReturn(Optional.of(userAccount));

		Article article = Article.builder()
			.id(1L)
			.title("article title")
			.description("article description")
			.body("article body")
			.author(userAccount)
			.build();
		article.createSlug();
		BDDMockito.given(articleRepository.findBySlug(eq(article.getSlug())))
			.willReturn(Optional.of(article));

		ArticleDto articleDto = ArticleDto.builder()
			.slug(article.getSlug())
			.title("changed title")
			.description("changed description")
			.body("changed body")
			.build();

		// when
		ArticleDto result = articleService.update(articleDto, userId);

		// then
		BDDAssertions.assertThat(result.getTitle()).isEqualTo(articleDto.getTitle());
		BDDAssertions.assertThat(result.getSlug()).isEqualTo("changed-title1");
		BDDAssertions.assertThat(result.getDescription()).isEqualTo(articleDto.getDescription());
		BDDAssertions.assertThat(result.getBody()).isEqualTo(articleDto.getBody());
	}
}
