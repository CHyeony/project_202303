package com.example.demo.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
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
    }


    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProfileResponse {

        private UserProfileDTO profile;
    }
}
