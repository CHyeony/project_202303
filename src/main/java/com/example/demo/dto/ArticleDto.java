package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.UserAccount;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto {

	private Long id;

	@Setter
	private String slug;

	private String title;

	private String description;

	private String body;

	private List<String> tagList;

	private UserDto author;

	@Setter
	private Boolean favorited;

	@Setter
	private Integer favoritesCount;


	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public static Article toArticle(ArticleDto dto, UserAccount author) {
		return Article.builder()
				.author(author)
				.title(dto.getTitle())
				.description(dto.getDescription())
				.body(dto.getBody())
				.build();
	}

	public static Article toArticle(ArticleDto dto) {
		return Article.builder()
				.slug(null)
				.title(dto.getTitle())
				.description(dto.getDescription())
				.body(dto.getBody())
				.build();
	}

	public static ArticleDto toArticleDto(Article article) {
		UserDto userDto = article.getAuthor() == null ? null : UserDto.builder()
				.username(article.getAuthor().getUsername())
				.bio(article.getAuthor().getBio())
				.image(article.getAuthor().getImage())
				.build();

		return ArticleDto.builder()
				.id(article.getId())
				.slug(article.getSlug())
				.title(article.getTitle())
				.description(article.getDescription())
				.body(article.getBody())
				.author(userDto)
				.build();
	}

	public static ArticleDto commnetArticleDto(Article article){
		UserDto userDto = article.getAuthor() == null ? null : UserDto.builder()
				.username(article.getAuthor().getUsername())
				.bio(article.getAuthor().getBio())
				.image(article.getAuthor().getImage())
				.build();

		return ArticleDto.builder()
				.id(article.getId())
				.body(article.getBody())
				.author(userDto)
				.build();
	}
}
