package vn.vietinbank.utils.data;

import com.jayway.jsonpath.JsonPath;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;

import static vn.vietinbank.utils.Constants.RESPONSE_STATUS;


public class CompareData {

    private static final Logger logger = LogManager.getLogger(CompareData.class);

    public static void compareHttpStatusCode(int statusCodeExpected) {
        int statusCodeActual = Serenity.sessionVariableCalled(RESPONSE_STATUS);
        logger.info("statusCodeActual: {}", statusCodeActual);
        logger.info("statusCodeExpected: {}", statusCodeExpected);
        Assert.assertEquals(statusCodeExpected, statusCodeActual);
    }

    public static boolean compareJSONObjects(@NotNull JSONObject jsonObject1, @NotNull JSONObject jsonObject2) {
        if (jsonObject1.length() != jsonObject2.length()) {
            logger.error("2 Objects have different lengths");
            return false;
        }
        Iterator<String> keys = jsonObject1.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!jsonObject2.has(key)) {
                logger.error(String.format("Different key: %s", key));
                return false;
            }
            try {
                if (!jsonObject1.get(key).toString().equals(jsonObject2.get(key).toString())) {
                    logger.error(String.format("Different value for key '%s': actual: %s and expected: %s", key, jsonObject1.get(key), jsonObject2.get(key)));
                    return false;
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static boolean compareJSONObjectsWithIgnoreFields(@NotNull JSONObject jsonObject1, @NotNull JSONObject jsonObject2, List<String> ignoreFields) {
        if (jsonObject1.length() != jsonObject2.length()) {
            logger.error("2 Objects have different lengths");
            return false;
        }
        Iterator<String> keys = jsonObject1.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!ignoreFields.contains(key)) {
                if (!jsonObject2.has(key)) {
                    logger.error(String.format("Different key: %s", key));
                    return false;
                }
                try {
                    if (!jsonObject1.get(key).toString().equals(jsonObject2.get(key).toString())) {
                        logger.error(String.format("Different value for key '%s': actual: %s and expected: %s", key, jsonObject1.get(key), jsonObject2.get(key)));
                        return false;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    public static boolean compareJSONObjectsWithJsonPath(@NotNull JSONObject jsonObject1, @NotNull JSONObject jsonObject2, String key) {
        String jsonPath = "$." + key;
        String actual = JsonPath.read(jsonObject1.toString(), jsonPath);
        String expected = JsonPath.read(jsonObject2.toString(), jsonPath);
        if (!actual.equals(expected)) {
            try {
                logger.error(String.format("Different value for key '%s': actual: %s and expected: %s", key, jsonObject1.get(key), jsonObject2.get(key)));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return true;
    }

    public static void applyIgnoreFields(JSONObject actual, JSONObject expected, String ignoreFields) {
        if (actual.has(ignoreFields) && expected.has(ignoreFields)) {
            try {
                expected.put(ignoreFields, JSONObject.NULL);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                actual.put(ignoreFields, JSONObject.NULL);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
