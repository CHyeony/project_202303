package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.repository.ArticleRepository;
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

	@PutMapping("/{slug}")
	public Map<String, Object> updateArtcile(
		@PathVariable String slug,
		@RequestBody ArticleRequest articleRequest,
		@RequestHeader("Authorization") String token
	) {
		articleRequest.getArticle().setSlug(slug);
		long userId = tokenParser.parseToken(token);
		ArticleDto articleDto = articleService.update(articleRequest.getArticle(), userId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("article", articleDto);
		return responseBody;
	}

	@DeleteMapping("/{slug}")
	public void deleteArticle(@PathVariable String slug, @RequestHeader("Authorization") String token) {
		long userId = tokenParser.parseToken(token);
		articleService.delete(slug, userId);
	}

	@GetMapping("/{slug}")
	public Map<String, Object> selectArticle(@PathVariable String slug){
		ArticleDto articleDto = articleService.selectArticle(slug);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("article",articleDto);
		return responseBody;
	}
}
