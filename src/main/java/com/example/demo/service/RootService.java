package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
import com.example.demo.interfaces.RootInterface;
import org.springframework.transaction.annotation.Transactional; // これを追加

@Service("rootService")
@Transactional
	public class RootService implements RootInterface {
	
	//1つ目のRepository
	@Autowired
	private InquiryRepository inquiryRepository;

	
//2つ目のRepository
	@Autowired
	private InquiryRepository2 inquiryRepository2;


//1つ目（inquityRepository）の処理
	@Override
	public List<InquiryForm> findAll() {
		return inquiryRepository.findAll();
	}

//2つ目（inquityRepository2）の処理 
	@Override
	public List<InquiryForm2> findAll2() {
		return inquiryRepository2.findAll();
	}

//1つ目（inquityRepository）の検索
	@Override
	public InquiryForm findOne(Long id) {
		return inquiryRepository.findById(id).
				orElseThrow(() -> new RuntimeException("お問い合わせ(ID:" + id + ")が見つかりません。"));
	}

//2つ目（inquityRepository2）の検索
	@Override
	public InquiryForm2 findOne2(Long id) {
		return inquiryRepository2.findById(id).
                orElseThrow(() -> new RuntimeException("お問い合わせ(ID:" + id + ")が見つかりません。"));
	}

//1つ目（inquityRepository）の保存	
	@Override
	public InquiryForm save(InquiryForm inquiryForm) {
		return inquiryRepository.save(inquiryForm);
	}

//2つ目（inquityRepository2）の保存
	@Override
	public InquiryForm2 save2(InquiryForm2 inquiryForm2) {
        return inquiryRepository2.save(inquiryForm2);
	}
}
