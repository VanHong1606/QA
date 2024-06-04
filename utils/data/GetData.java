package vn.vietinbank.utils.data;

import com.jayway.jsonpath.JsonPath;
import net.serenitybdd.core.Serenity;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static vn.vietinbank.utils.Constants.*;

public class GetData {

    private static final Logger logger = LogManager.getLogger(GetData.class);

    public static String getValueOf(String key) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        return variables.getProperty(key);
    }

    public static String getDataFromFile(String key, String value) {
        switch (value) {
            case FROM_CONF_FILE:
                value = getValueOf(key);
                break;
            case FROM_ACCOUNTS_FILE:
                String nameApp = Serenity.sessionVariableCalled("nameApp");
                String username = getValueOf(USERNAME);
                String accountFile = "src/test/resources/mobile/data/accounts.json";
                value = getValueFromJson(accountFile, String.format("$.%s.%s.accountsLogin.%s.%s", nameApp, ENV, username, key));
        }
        return value;
    }

    public static String getValueFromJson(String filePath, String jsonPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            Object value = JsonPath.read(content, jsonPath);
            return value.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDataWithJsonPath(@NotNull JSONObject jsonObject, String key) {
        String jsonPath = "$." + key;
        String value = JsonPath.read(jsonObject.toString(), jsonPath);
        logger.info(value);
        return value;
    }

    public static void readJsonFile(String sessionVariableName, String pathJsonFile) {
        GetData jsonReader = new GetData();
        String filePath = System.getProperty("user.dir") + "/" + pathJsonFile;
        jsonReader.readJsonFileAndSetToSession(filePath, sessionVariableName);
        logger.info("Nội dung của Json File {} được lưu vào biến {}", pathJsonFile, sessionVariableName);
    }

    public void readJsonFileAndSetToSession(String filePath, String sessionVariableName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(content);
            logger.info("{} trước khi update: {}", sessionVariableName, jsonObject.toString(4));
            Serenity.setSessionVariable(sessionVariableName).to(jsonObject);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
