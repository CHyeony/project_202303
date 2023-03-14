package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@WebMvcTest(
	controllers = {UserController.class}
)
@ActiveProfiles("local")
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void testRegister() throws Exception {
		String requestBody = "{\n"
			+ "    \"user\":{\n"
			+ "        \"email\":\"abcd@naver.com\",\n"
			+ "        \"password\":\"123123\",\n"
			+ "        \"username\":\"abcd\"\n"
			+ "    }\n"
			+ "}";

		UserDto userDto = UserDto.builder()
			.email("abcd@naver.com")
			.username("abcd")
			.build();
		BDDMockito.given(userService.register(any()))
			.willReturn(userDto);

		ResultActions result = mvc.perform(
			post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.user.email").value("abcd@naver.com"));
		result.andExpect(jsonPath("$.user.username").value("abcd"));
	}
}
