package com.example.demo.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.interfaces.RootInterface;
import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

@WebMvcTest(RootController.class)
public class RootControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean(name = "rootService")
	private RootInterface rootInterface;

	@Test
	public void test_form_成功() throws Exception {
		when(rootInterface.save(any())).thenReturn(new InquiryForm());
		mockMvc.perform(post("/form").param("name", "名前").param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(view().name("root/form"));

	}

	@Test
	public void test_form_失敗_name_empty() throws Exception {
		mockMvc.perform(post("/form").param("name", "").param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(status().isOk()).andExpect(view().name("root/form")).andExpect(model().hasErrors());
	}

	@ParameterizedTest
	@NullSource
	@EmptySource
	@ValueSource(strings = {" ", "ABCDEFGHIJK"})
	public void test_form_失敗_パラメータ(String param) throws Exception {
		mockMvc.perform(post("/form").param("name", param).param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(status().isOk()).andExpect(view().name("root/form")).andExpect(model().hasErrors());
	}

	@Test
	public void test_form_失敗_name_null() throws Exception {
		mockMvc.perform(post("/form").param("name", "").param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(status().isOk()).andExpect(view().name("root/form")).andExpect(model().hasErrors());
	}

	@Test
	public void test_form_失敗_name_blank() throws Exception {
		mockMvc.perform(post("/form").param("name", " ").param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(status().isOk()).andExpect(view().name("root/form")).andExpect(model().hasErrors());
	}

	@Test
	public void test_form2_成功() throws Exception {
		when(rootInterface.save2(any())).thenReturn(new InquiryForm2());
		mockMvc.perform(post("/form2").param("name", "名前").param("mail", "mail@ne.jp").param("content", "お問い合わせ"))
				.andExpect(view().name("root/form2"));
	}

	@Test
	public void test_form2_失敗_name_empty() throws Exception {
		mockMvc.perform(post("/form2").param("name", "").param("mail", "mail@nejp").param("content", "お問い合わせ"))
				.andExpect(status().isOk()).andExpect(view().name("root/form2")).andExpect(model().hasErrors());
	}
}
