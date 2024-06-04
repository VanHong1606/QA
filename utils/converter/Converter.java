package vn.vietinbank.utils.converter;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Converter {

    private static final Logger logger = LogManager.getLogger(Converter.class);

    public static List<String> stringToList(String str) {
        String[] arr = str.split(", ");
        return Arrays.asList(arr);
    }

    public static String getStringBeforeFirstDot(String input) {
        int index = input.indexOf(".");
        if (index == -1) {
            return input;
        }
        return input.substring(0, index);
    }

    public static JSONObject responseToJSONObject(Response response) {
        try {
            return new JSONObject(response.asString());
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    public static List<String> base64DecodedToList(String base64Decoded) {
        String[] stringArray = base64Decoded.split("");
        return new ArrayList<>(Arrays.asList(stringArray));
    }

    public static boolean isAllUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStringInListSpecialSymbols(String text) {
        List<String> specialCharacters = Arrays.asList(
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=",
                "{", "}", "[", "]", "|", "\\", ":", ";", "\"", "'", "<", ">", ",", ".", "?", "/"
        );
        return specialCharacters.contains(text);
    }

    public static boolean isStringInListNumbers(String text) {
        List<String> numberCharacters = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        return numberCharacters.contains(text);
    }

    public static String decodeBase64String(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        String value = new String(decodedBytes);
        logger.info(value);
        return value;
    }

    public static String format(String number) {
        StringBuilder formattedNumber = new StringBuilder();
        int length = number.length();
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            formattedNumber.insert(0, number.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                formattedNumber.insert(0, ",");
            }
        }
        return formattedNumber.toString();
    }

    public static String[] splitStringDate(String date) {
        String[] parts = date.split(" ");
        String day = parts[1];
        String month = date.substring(date.indexOf("Tháng"));
        return new String[]{day, month};
    }
}
