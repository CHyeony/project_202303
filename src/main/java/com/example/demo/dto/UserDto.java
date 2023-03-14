package com.example.demo.dto;

import com.example.demo.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class UserDto {

	private Long id;

	private String email;

	private String password;

	private String username;

	private String bio;

	private String image;

	private String token;

	public static UserDto toUserDto(UserAccount userAccount) {
		return UserDto.builder()
			.id(userAccount.getId())
			.email(userAccount.getEmail())
			.username(userAccount.getUsername())
			.bio(userAccount.getBio())
			.image(userAccount.getImage())
			.token(userAccount.getId().toString())
			.build();
	}
}
