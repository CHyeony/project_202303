package com.example.demo.dto;

import com.example.demo.entity.UserAccount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDTO {
    private Long userId;
    private String username;
    private String bio;
    private String image;
    private Boolean following;

    public static UserProfileDTO toProfileDto(UserAccount userAccount){
        return UserProfileDTO.builder()
                .username(userAccount.getUsername())
                .userId(userAccount.getId())
                .bio(userAccount.getBio())
                .image(userAccount.getImage())
                .build();
    }
}
