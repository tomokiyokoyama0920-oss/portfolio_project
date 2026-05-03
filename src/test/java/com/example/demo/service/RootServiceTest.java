package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.interfaces.RootInterface;
import com.example.demo.models.InquiryForm;
import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;

//サービスクラスで必須のアノテーションで（モック）の機能を有効化する
@ExtendWith(MockitoExtension.class)
class RootServiceTest {

	// これからテストするもの
	@InjectMocks
	private RootService rootService;

	// テストをするための必要な部品
	@Mock
	private InquiryRepository inquiryRepository;

	@Mock
	private InquiryRepository2 inquiryRepository2;

	@Test
	void test_findOne_成功_存在するIDを指定した時に正しい値が返ってくること() {
		InquiryForm mockForm = new InquiryForm();
		mockForm.setId(1L);
		when(inquiryRepository.findById(1L)).thenReturn(Optional.of(mockForm));

//    Act 実行
		InquiryForm result = rootService.findOne(1L);
		
//    Assert 検証
		assertNotNull(result);
		assertEquals(1L, result.getId());
		verify(inquiryRepository).findById(1L);
	}

}