package com.example.demo.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_account_id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "bio")
	private String bio;

	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "userAccount")
	private List<Like> likes = new ArrayList<>();

	@Builder
	public UserAccount(Long id, String email, String password, String username, String bio, String image) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.bio = bio;
		this.image = image;
	}
}
