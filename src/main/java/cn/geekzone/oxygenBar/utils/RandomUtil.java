package cn.geekzone.oxygenBar.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RandomUtil {
	private static Random random = new Random();
	private static MessageDigest messagedigest = null;
	private static String str_value_interval = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static char[] hex_digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	private static String invite_code_element = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
	
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException var1) {
			var1.printStackTrace();
		}

	}

	public static Integer randomInteger() {
		return new Integer(random.nextInt());
	}

	public static Integer randomInteger(Integer min, Integer max) {
		if (min >= 0 && max >= 0) {
			if (min == max) {
				return min;
			} else {
				if (min > max) {
					int temp = min;
					min = max;
					max = temp;
				}

				Integer randint = randomIntegerABS();
				randint = randint % (max - min) + min;
				return randint;
			}
		} else {
			return 0;
		}
	}

	public static Integer randomIntegerABS() {
		return Math.abs(randomInteger());
	}

	public static String randomString(Integer len) {
		return randomString(len, len);
	}

	public static String randomString(Integer minlen, Integer maxlen) {
		int length = minlen;
		if (minlen < maxlen) {
			length = Math.abs(random.nextInt()) % (maxlen - minlen) + minlen;
		}

		StringBuffer strbuf = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			Integer charIndex = Math.abs(random.nextInt()) % str_value_interval.length();
			strbuf.append(str_value_interval.charAt(charIndex));
		}

		return strbuf.toString();
	}

	public static String randomStringByMD5Integer() {
		Integer randint = randomIntegerABS();
		byte[] md5bytes = messagedigest.digest(("" + randint).getBytes());
		StringBuffer strbuf = new StringBuffer();

		for (int i = 0; i < md5bytes.length; ++i) {
			byte bt = md5bytes[i];
			char c0 = hex_digits[(bt & 240) >> 4];
			char c1 = hex_digits[bt & 15];
			strbuf.append(c0);
			strbuf.append(c1);
		}

		return strbuf.toString();
	}
	
	public static String randomString1(Integer len) {
		return randomString1(len, len);
	}
	
	public static String randomString1(Integer minlen, Integer maxlen) {
		int length = minlen;
		if (minlen < maxlen) {
			length = Math.abs(random.nextInt()) % (maxlen - minlen) + minlen;
		}

		StringBuffer strbuf = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			Integer charIndex = Math.abs(random.nextInt()) % invite_code_element.length();
			strbuf.append(invite_code_element.charAt(charIndex));
		}

		return strbuf.toString();
	}

	public static void main(String[] args) {
		System.out.println(randomString(32));
	}
}
