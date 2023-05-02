package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles/{slug}")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    private final TokenParser tokenParser;

    @PostMapping("/favorite")
    public ResponseEntity<Map<String, Object>> onLike(
        @RequestHeader("Authorization") String token,
        @PathVariable String slug
    ){
        long userId = tokenParser.parseToken(token);
        ArticleDto articleDto = likeService.onLike(userId, slug);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("article", articleDto);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<Map<String, Object>> offLike(
        @RequestHeader("Authorization") String token,
        @PathVariable String slug
    ){
        long userId = tokenParser.parseToken(token);
        ArticleDto articleDto = likeService.offLike(userId, slug);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("article", articleDto);
        return ResponseEntity.ok(responseBody);
    }
}
