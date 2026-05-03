package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

public interface RootInterface {

//RootServiceでのルールを追加
	public List<InquiryForm> findAll();

	public List<InquiryForm2> findAll2();

	public InquiryForm findOne(Long id);

	public InquiryForm2 findOne2(Long id);

	public InquiryForm save(InquiryForm inquiryForm);

	public InquiryForm2 save2(InquiryForm2 inquiryForm2);
}