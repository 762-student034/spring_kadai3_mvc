package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Account;
import com.example.demo.service.ValidationService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private Account account;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("account", account);
		return "accountForm";	
	}
	
	@PostMapping("/confirm")
	public String confirm(
			ValidationService validationService, 
			Account account, 
			Model model) {
		
		List<String> errors = validationService.validate(account);


		if (!errors.isEmpty()) {
			model.addAttribute("errorList", errors);
			model.addAttribute("account", account);
			return "accountForm";
		}
		
		model.addAttribute("name", account.getName());
		model.addAttribute("email", account.getEmail());
		model.addAttribute("password", account.getPassword());

		return"accountConfirmation";
	}
	
	@PostMapping("")
	public String store(Account account, Model model) {
		model.addAttribute("name", account.getName());
		model.addAttribute("email", account.getEmail());
		return"accountFinish";
	}
	

}
