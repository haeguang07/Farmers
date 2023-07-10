package com.yedam.app;

import java.util.Random;

public class RamdomString {

	public static void main(String[] args) {
		
			int leftLimit = 48; // numeral '0'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 13;
			Random random = new Random();
			String generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) &&(i <= 33 || i >= 38)).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			System.out.println(generatedString);
		

	}

}
