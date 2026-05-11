package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import com.example.demo.model.RandomResult;

@Service
public class RandomStringService {
	//	private final static int GENERATE_NUM = 10;
	
	public RandomResult generate(
	        Integer charLength,
	        boolean withNumber,
	        boolean withAlphabet,
	        Integer createCount) {

	    List<String> list = new ArrayList<>();
	    RanUtil util = new RanUtil();
	    String errMessage = null;

	    if (createCount == null || createCount == 0) {
	    	System.out.println("0 or null ccount CALLED");
	        return new RandomResult(list, "生成数を入力してください");
	    }

	    for (int i = 0; i < createCount; i++) {

	        int mode = util.mode(withNumber, withAlphabet);
	        RandomStringGenerator.Builder builder = null;

	        switch (mode) {
	            case 1 -> builder = new RandomStringGenerator.Builder()
	                    .withinRange('0', '9')
	                    .filteredBy(Character::isLetterOrDigit);

	            case 2 -> builder = new RandomStringGenerator.Builder()
	                    .withinRange('A', 'z')
	                    .filteredBy(Character::isLetterOrDigit);

	            case 3 -> {
	                if (charLength < 2) {
	                    errMessage = "数字と英字を両方出力させるには2つ以上の文字が必要です";
	                    return new RandomResult(list, errMessage);
	                }
	                builder = new RandomStringGenerator.Builder()
	                        .withinRange('0', 'z')
	                        .filteredBy(Character::isLetterOrDigit);
	            }

	            default -> {
	                errMessage = "英字か数字にチェック入れてください";
	                return new RandomResult(list, errMessage);
	            }
	        }

	        RandomStringGenerator generator = builder.get();
	        String result;

	        if (mode==3) {
	            do {
	                result = generator.generate(charLength);
	            } while (!util.containsNumber(result) || !util.containsAlphabet(result));
	        } else {
	            result = generator.generate(charLength);
	        }

	        list.add(result);
	    }

	    return new RandomResult(list, errMessage);
	}

//	リファクタリング、分割前のバージョン
//	public List<String> generate(
//	        Integer charLength,
//	        boolean withNumber,
//	        boolean withAlphabet,
//	        Integer createCount) {
//
//	    List<String> list = new ArrayList<>();
//
//	    if (createCount == null || createCount == 0) {
//	        return list;
//	    }
//
//	    for (int i = 0; i < createCount; i++) {
//
//	        RandomStringGenerator.Builder builder;
//
//	        if (withNumber && withAlphabet) {
//	            System.out.println("BOTH CALLED");
//	            builder = new RandomStringGenerator.Builder()
//	                    .withinRange('0', 'z')
//	                    .filteredBy(Character::isLetterOrDigit);
//	        } else if (withNumber) {
//	            System.out.println("NUMBER CALLED");
//	            builder = new RandomStringGenerator.Builder()
//	                    .withinRange('0', '9')
//	                    .filteredBy(Character::isLetterOrDigit);
//	        } else if (withAlphabet) {
//	            System.out.println("ALPHABET CALLED");
//	            builder = new RandomStringGenerator.Builder()
//	                    .withinRange('A', 'z')
//	                    .filteredBy(Character::isLetterOrDigit);
//	        } else {
//	            System.out.println("BREAK CALLED");
//	            break;
//	        }
//
//	        RandomStringGenerator generator = builder.get();
//
//	        String result;
//
//	        if (withNumber && withAlphabet) {
//	            do {
//	                result = generator.generate(charLength);
//	            } while (!containsNumber(result) || !containsAlphabet(result));
//	        } else {
//	            result = generator.generate(charLength);
//	        }
//
//	        list.add(result);
//	    }
//
//	    return list;
//	}

//	
//	public boolean containsNumber(String s) {
//		return s.chars().anyMatch(Character::isDigit);
//	}
//
//	//英字が含まれるかどうか
//	public boolean containsAlphabet(String s) {
//		return s.chars().anyMatch(Character::isLetter);
//	}
}
