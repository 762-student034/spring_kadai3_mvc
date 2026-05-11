package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Account;

@Service
public class ValidationService {

	private static final String EMAIL_REGEX = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

	public List<String> validate(Account account) {

		List<String> errors = new ArrayList<>();

		// 名前
		if (account.getName() == null || account.getName().isBlank())
			errors.add("名前を入力してください");

		if (account.getName() != null && account.getName().length() > 20)
			errors.add("名前は20字以内で入力してください");

		// メール
		// メール必須
		if (account.getEmail() == null || account.getEmail().isBlank()) {
			errors.add("メールを入力してください");
		}

		// メール形式
		if (account.getEmail() != null && !account.getEmail().isBlank() && !account.getEmail().matches(EMAIL_REGEX)) {
			errors.add("メールアドレスの形式が正しくありません");
		}
		
		if (account.getPassword() == null || account.getPassword().isEmpty()) {
			errors.add("パスワードは必須です");
		}
		return errors;
	}
}
