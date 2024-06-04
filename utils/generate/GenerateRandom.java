package vn.vietinbank.utils.generate;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateRandom {

    public static String generateRandomNumberFromTo(int begin, int end) {
        Random random = new Random();
        int length = random.nextInt(end - begin + 1) + begin;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateRandomNumberWithLength(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static int[] extractNumbers(String value) {
        String[] parts = value.split("_");
        int[] numbers = new int[1];
        if (parts.length == 3) {
            numbers[0] = Integer.parseInt(parts[2]);
        }
        if (parts.length == 4) {
            numbers[0] = Integer.parseInt(parts[2]);
            numbers[1] = Integer.parseInt(parts[3]);
        }
        return numbers;
    }

    public static String generateRandomString(int length) {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomStringFromTo(int begin, int end) {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(end - begin + 1) + begin;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
