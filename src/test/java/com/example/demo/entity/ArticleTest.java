package com.example.demo.entity;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

public class ArticleTest {

	@Test
	public void createSlug() {
		// given
		String originalString = "How to Make the Perfect Cup of Coffee!!!Coffee@@Coffee";
		Article article = Article.builder()
			.id(1L)
			.title(originalString)
			.build();

		// when
		article.createSlug();

		// then
		BDDAssertions.assertThat(article.getSlug())
			.isEqualTo("how-to-make-the-perfect-cup-of-coffee-coffee-coffee1");
	}

	@Test
	public void givenIdAndTitleIsNull() {
		// given
		Article article = Article.builder().build();

		try {
			// when
			article.createSlug();
			BDDAssertions.fail("예외가 발생하지 않았습니다.");
		} catch (NullPointerException e) {
			// then
		}
	}
}
