package com.example.demo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDTO {
   // private Long userId;
    private String username;
    private String bio;
    private String image;
    private Boolean following;

    public UserProfileDTO(String username, String bio, String image) {

        this.username = username;
        this.bio = bio;
        this.image = image;
//        this.following = following;
    }

//        return UserProfileDTO.builder()
//                .username(userAccount.getUsername())
//                .userId(userAccount.getId())
//                .bio(userAccount.getBio())
//                .image(userAccount.getImage())
//                .build();

}
