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
public class CommentDTO {
    private Long id;
    private String body;
    private UserDto author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static CommentDTO toCommentDto(Comment comment){
        UserDto userDto = comment.getAuthor() == null ? null : UserDto.builder()
                .username(comment.getAuthor().getUsername())
                .bio(comment.getAuthor().getBio())
                .image(comment.getAuthor().getImage())
                .build();

        return CommentDTO.builder()
                .id(comment.getComId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .body(comment.getBody())
                .author(userDto)
                .build();


    }
}
