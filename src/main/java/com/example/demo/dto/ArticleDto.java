package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class ArticleDto {

	private Long id;

	private String title;

	private String description;

	private String body;

	private List<String> tagList;
}
