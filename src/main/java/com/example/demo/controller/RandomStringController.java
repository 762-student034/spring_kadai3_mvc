package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.RandomResult;
import com.example.demo.service.RandomStringService;

@Controller
public class RandomStringController {

	private final RandomStringService service; // ランダム文字列生成サービス

	// コンストラクタインジェクション
	public RandomStringController(RandomStringService service) {
		this.service = service;
	}

	// 初期表示
	@GetMapping("/random")
	public String index() {
		return "random";
	}

	// 作成するボタンをクリックしたとき
	@PostMapping("/random")
	public String generate(
			@RequestParam int charLength,
			@RequestParam(defaultValue = "false", required = false) boolean withNumber,
			@RequestParam(defaultValue = "false", required = false) boolean withAlphabet,
			@RequestParam(required = false) Integer createCount,
			Model model) {

		// ランダム文字列のListを生成

		RandomResult list = service.generate(charLength, withNumber, withAlphabet, createCount);
		
		if (list != null) {
			model.addAttribute("withNumber", withNumber);
			model.addAttribute("withAlphabet", withAlphabet);
			model.addAttribute("charLength", charLength);
			model.addAttribute("createCount", createCount);
		}
		
//		リファクタリング、分割前のバージョン
//		model.addAttribute("errMessage", errMessage);
//		model.addAttribute("randList", list);
		
		model.addAttribute("errMessage", list.getErrMessage());
		model.addAttribute("randList", list.getList());

		return "random";
	}
}
