package thich.thong.lac.util;

import java.util.Random;

public class RandomData {

	private static final char[] poolString = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G',
			'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r',
			'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };

	private static final char[] poolNumber = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	private Random rnd;

	public RandomData() {
		rnd = new Random();
	}

	public char getChar() {
		return poolString[rnd.nextInt(poolString.length)];
	}

	public char getNumber() {
		return poolNumber[rnd.nextInt(poolNumber.length)];
	}

	public String getStr(int length_string) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length_string; i++)
			sb.append(getChar());
		return new String(sb);
	}

	public String getEmail(int length_string, String domain) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length_string; i++)
			sb.append(getChar());
		return new String(sb + "@" + domain + ".auto");
	}

	public int getNumber(int length_string) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length_string; i++)
			sb.append(getNumber());

		String abc = new String(sb);
		int numbers = Integer.parseInt(abc);
		return numbers;
	}

	public int getNumber(int minimum, int maximum) {
		return minimum + (int) (Math.random() * ((maximum - minimum) + 1));
	}

}
