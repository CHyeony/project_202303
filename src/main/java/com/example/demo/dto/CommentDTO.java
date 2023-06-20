package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.entity.Comment;
import com.example.demo.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public static Comment toComment(CommentDTO dto, UserAccount author){
        return Comment.builder()
                .author(author)
                .body(dto.getBody())
                .build();
    }
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
