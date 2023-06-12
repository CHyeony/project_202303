package com.example.demo.dto;

import com.example.demo.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommnetDTO {
    private Long id;
    private String content;
    private UserDto author;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    private String body;

    public static CommnetDTO toCommnetDto(Comment comment){
        UserDto userDto = comment.getAuthor() == null ? null : UserDto.builder()
                .username(comment.getAuthor().getUsername())
                .bio(comment.getAuthor().getBio())
                .image(comment.getAuthor().getImage())
                .build();

        return CommnetDTO.builder()
                .id(comment.getCom_id())
                .createAt(comment.getCreateAt())
                .updatedAt(comment.getUpdatedAt())
                .body(comment.getBody())
                .author(userDto)
                .build();


    }
}
