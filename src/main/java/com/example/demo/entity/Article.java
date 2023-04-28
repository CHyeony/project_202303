package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_idx")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "user_account_id")
	private UserAccount author;

	@Column(name = "slug")
	private String slug;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "body")
	private String body;

	@Column(name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	public void createSlug() {
		if (this.title == null || this.id == null) {
			throw new NullPointerException("title and id must not be null");
		}

		String originalString = this.title;
		String lowercaseString = originalString.toLowerCase(); // 소문자
		String hyphenatedString = lowercaseString.replaceAll("[^a-zA-Z0-9]+", "-"); // 특수문자 -> hyphen
		this.slug = hyphenatedString + this.id;
	}

	public void changeTitle(String title) {
		this.title = title;
		createSlug();
	}

	public void changeDescription(String description) {
		this.description = description;
	}

	public void changeBody(String body) {
		this.body = body;
	}
}
