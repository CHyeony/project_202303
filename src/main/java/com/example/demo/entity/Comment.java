package com.example.demo.entity;

import com.example.demo.dto.CommnetDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long com_id;
    private String comments;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedBy
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_account_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private UserAccount author;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

}
