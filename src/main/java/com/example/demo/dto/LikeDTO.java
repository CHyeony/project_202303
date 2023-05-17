package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDTO {

    private Long article_id;
    private Long user_id;
    private Boolean liked;

}
