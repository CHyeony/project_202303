package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.ArticleRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {

	private final TokenParser tokenParser;

	@PostMapping
	public Map<String, Object> createArticle(@RequestBody ArticleRequest articleRequest, @RequestHeader("Authorization") String token) {
		long userId = tokenParser.parseToken(token);
		return null;
	}
}
