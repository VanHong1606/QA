package vn.vietinbank.utils.data;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.data.GetData.getValueOf;
import static vn.vietinbank.utils.generate.GenerateDateTime.*;
import static vn.vietinbank.utils.generate.GenerateRandom.*;

public class DataInput {

    private static final Logger logger = LogManager.getLogger(DataInput.class);

    public static void setBodyJson(String sessionVariableName, @NotNull DataTable dataTable) {
        JSONObject jsonObject = updateJSONObject(sessionVariableName, dataTable);
        switch (sessionVariableName) {
            case BODY_REQUEST:
                Serenity.setSessionVariable(sessionVariableName).to(jsonObject.toString());
                break;
            case RESPONSE_EXPECTED:
                Serenity.setSessionVariable(sessionVariableName).to(jsonObject);
                break;
            default:
                break;
        }
    }

    public static JSONObject updateJSONObject(String sessionVariableName, @NotNull DataTable dataTable) {
        JSONObject jsonObject = Serenity.sessionVariableCalled(sessionVariableName);
        try {
            List<List<String>> data = dataTable.asLists();
            for (int i = 0; i < data.get(0).size(); i++) {
                String key = "$." + data.get(0).get(i);
                String value = data.get(1).get(i);
                String keyStr = key.replace("$.", "");
                Object[] checkData = checkData(jsonObject, value, keyStr);
                value = (String) checkData[1];
                jsonObject = (JSONObject) checkData[0];
                if (!Objects.equals(value, "REMOVE")) {
                    String check = JsonPath.parse(jsonObject.toString()).set(key, value).jsonString();
                    if (value != null && value.equals(FROM_COMMAND_LINE)) {
                        check = JsonPath.parse(jsonObject.toString()).set(key, getValueOf(keyStr)).jsonString();
                    }
                    jsonObject = new JSONObject(check);
                    Serenity.setSessionVariable(keyStr).to(value);
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            logger.info("{} sau khi update: {}", sessionVariableName, jsonObject.toString(4));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    public static Object[] checkData(JSONObject jsonObject, String value, String key) {
        String valueChecked;
        switch (value) {
            case "TIMESTAMP_LOCAL_NOW":
                valueChecked = timeStampLocalNow();
                break;
            case "LOCAL_TIME_1":
                valueChecked = getLocalTime1();
                break;
            case "LOCAL_DATE_1":
                valueChecked = getLocalDate1();
                break;
            case "LOCAL_DATE_2":
                valueChecked = getLocalDate2();
                break;
            case "LOCAL_DATE_3":
                valueChecked = getLocalDate3();
                break;
            case "NULL":
                valueChecked = null;
                break;
            case "EMPTY":
            case "BLANK":
                valueChecked = "";
                break;
            case "SAME_REQUEST":
                valueChecked = Serenity.sessionVariableCalled(key);
                break;
            case "REMOVE":
                valueChecked = value;
                jsonObject.remove(key.replace("$.", ""));
                break;
            default:
                valueChecked = value;
                break;
        }
        if (value.contains("RANDOM_NUMBER")) {
            int[] numbers = extractNumbers(value);
            switch (numbers.length) {
                case 1:
                    valueChecked = generateRandomNumberWithLength(numbers[0]);
                    break;
                case 2:
                    valueChecked = generateRandomNumberFromTo(numbers[0], numbers[1]);
                    break;
                default:
                    valueChecked = value;
            }
        }
        if (value.contains("RANDOM_STRING")) {
            int[] numbers = extractNumbers(value);
            switch (numbers.length) {
                case 1:
                    valueChecked = generateRandomString(numbers[0]);
                    break;
                case 2:
                    valueChecked = generateRandomStringFromTo(numbers[0], numbers[1]);
                    break;
                default:
                    valueChecked = value;
            }
        }
        return new Object[]{jsonObject, valueChecked};
    }
}
