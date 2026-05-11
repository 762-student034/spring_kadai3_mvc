package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RanUtil {

	//数字が含まれるかどうか
	public boolean containsNumber(String s) {
		return s.chars().anyMatch(Character::isDigit);
	}

	//英字が含まれるかどうか
	public boolean containsAlphabet(String s) {
		return s.chars().anyMatch(Character::isLetter);
	}

	public int mode(boolean withNumber, boolean withAlphabet) {
	    return withNumber && withAlphabet ? 3 : //数字英字
	           withNumber ? 1 : //数字
	           withAlphabet ? 2 : 0; //英字 else エラー
	}
}
