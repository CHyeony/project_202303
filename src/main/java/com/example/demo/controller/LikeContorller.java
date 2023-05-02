package com.example.demo.controller;

import com.example.demo.dto.LikeDTO;
import com.example.demo.entity.Like;
import com.example.demo.service.LIkeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles/{slug}")
public class LikeContorller {

    private final LIkeService lIkeService;

    public LikeContorller(LIkeService lIkeService) {
        this.lIkeService = lIkeService;
    }

    @PostMapping("/favorite")
    public ResponseEntity<String> onLike(@RequestBody LikeDTO likeDTO){
        lIkeService.onLike(likeDTO, likeDTO.getArticle_id(), likeDTO.getUser_id());
        return ResponseEntity.ok("LIKE");

    }

    @PostMapping("/favorite")
    public ResponseEntity<String> offLike(@RequestBody LikeDTO likeDTO){
        lIkeService.offLike(likeDTO, likeDTO.getUser_id(),likeDTO.getArticle_id());

        return ResponseEntity.ok("UNLIKE");
    }
}
