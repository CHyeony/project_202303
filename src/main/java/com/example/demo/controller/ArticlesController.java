package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entity.Article;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.ArticleRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleRequest;
import com.example.demo.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {

	private final TokenParser tokenParser;

	private final ArticleService articleService;
	private final ArticleRepository articleRepository;

	@PostMapping
	public Map<String, Object> createArticle(@RequestBody ArticleRequest articleRequest, @RequestHeader("Authorization") String token) {
		long userId = tokenParser.parseToken(token);
		ArticleDto articleDto = articleService.create(articleRequest.getArticle(), userId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("article", articleDto);
		return responseBody;
	}

	@GetMapping("/{slug}")
	public Map<String, Object> selectArticle(@RequestBody ArticleRequest articleRequest, @PathVariable("slug") String slug){
		Article article = articleRepository.findBySlug(slug)
				.orElseThrow(()->new BusinessException(ErrorCode.ARTICLE_NOT_FOUND));
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("article",article);
		return responseBody;
	}

}
