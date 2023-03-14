package com.example.demo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.config.JpaConfig;
import com.example.demo.entity.Article;

/**
 * @author geonhong.lee
 */
@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
public class ArticleRepositoryTest {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void testCreatedDate() {
		Article article = Article.builder()
			.build();

		articleRepository.save(article);

		Assertions.assertThat(article.getCreatedAt()).isNotNull();
	}
}
