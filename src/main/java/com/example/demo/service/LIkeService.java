package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Like;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LIkeService {

    @Autowired
    private LikeRepository likeRepository;

    public void onLike(Like like){
        likeRepository.save(like);
    }

    public void offLike(Like like){
        likeRepository.delete(like);
    }

    public Like LikeUser(Article article, UserAccount userAccount){

       return null;
    }

}
