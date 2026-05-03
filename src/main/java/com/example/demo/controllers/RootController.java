package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaces.RootInterface;
import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

@Controller
@RequestMapping("/")
public class RootController {
	private final RootInterface rootService;

	// コンストラクタで受け取る
	public RootController(@Qualifier("rootService") RootInterface rootService) {
		this.rootService = rootService;
	}

	@GetMapping
	public String index() {
		return "root/index";
	}

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm2) {
		return "root/form2";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		try {
			// 例外が発生する可能性(通信エラーやDBが停止している等) ここで、RootService側の処理待ち
			rootService.save(inquiryForm);
			// RootService側の処理が成功した場合
			inquiryForm.clear();
			model.addAttribute("message", "お問い合わせを受け付けました。");
			// RootService側の処理が失敗した場合
		} catch (Exception e) {
			e.printStackTrace();
			// 失敗した時の安全装置
			model.addAttribute("message", "エラーが発生しました。時間をおいて再度確かめてください");
		}
		return "root/form";
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm2, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}

		try {
			// 例外が発生する可能性（通信エラーやDBが停止している等）
			rootService.save2(inquiryForm2);
			inquiryForm2.clear();
			model.addAttribute("message", "お問い合わせを受け付けました。");

		} catch (Exception e) {
			e.printStackTrace();
			// 失敗したときの安全装置
			model.addAttribute("message", "エラーが発生しました。もう一度やり直してください");
		}
		return "root/form2";
	}
}
