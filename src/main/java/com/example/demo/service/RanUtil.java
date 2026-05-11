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
		int mode = 0;

		if (withNumber && !withAlphabet) { //数字
			mode = 1; 
		}

		if (!withNumber && withAlphabet) { //英字
			mode = 2;
		}

		if (withNumber && withAlphabet) { //両方入れている
			mode = 3;
		}

		if (!withNumber && !withAlphabet) { //両方も入れてない
			mode = 0;
		}

		return mode;
	}
}
