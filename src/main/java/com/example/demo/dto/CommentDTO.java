package com.example.demo.dto;

import com.example.demo.entity.Comment;
import com.example.demo.entity.UserAccount;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @LastModifiedBy
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


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
