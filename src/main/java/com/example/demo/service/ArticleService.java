package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;

	// 만들어야 하는 기능: 게시글 생성
	// 게시글 생성 파라미터: 제목, 내용, 해쉬태그, 본문 요약, 사용자 id
	// 게시글 생성: 사용자 id로 사용자 조회, 게시글 생성
	public ArticleDto create(ArticleDto articleDto, long userId) {
		return null;
	}
}
