package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class RandomStringService {
	//	private final static int GENERATE_NUM = 10;

	public List<String> generate(
			Integer charLength,
			boolean withNumber,
			boolean withAlphabet,
			Integer createCount) {

		List<String> list = new ArrayList<>();

		if (createCount == null || createCount == 0) {
		    return list;
		}

		for (int i = 0; i < createCount; i++) {
			RandomStringGenerator generator;

			if (withNumber && withAlphabet) {
				System.out.println("BOTH CALLED");
				generator = new RandomStringGenerator.Builder()
						.withinRange('0', 'z')
						.filteredBy(Character::isLetterOrDigit)
						.get();
			}

			else if (withNumber) {
				System.out.println("NUMBER CALLED");
				generator = new RandomStringGenerator.Builder()
						.withinRange('0', '9')
						.filteredBy(Character::isLetterOrDigit)
						.get();
			} else if (withAlphabet) {
				System.out.println("ALPHABET CALLED");
				generator = new RandomStringGenerator.Builder()
						.withinRange('A', 'z')
						.filteredBy(Character::isLetterOrDigit)
						.get();
			} else {
				System.out.println("BREAK CALLED");
				break;
			}

			list.add(generator.generate(charLength));
		}

		return list;
	}
}
