package vn.vietinbank.api.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import vn.vietinbank.utils.excel.ExcelActions;

import java.util.Map;

import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.converter.Converter.getStringBeforeFirstDot;
import static vn.vietinbank.utils.converter.Converter.responseToJSONObject;
import static vn.vietinbank.utils.data.GetData.getValueFromJson;

public class CommonAPI {

    private static final Logger logger = LogManager.getLogger(CommonAPI.class);

    public static void callApiWithMethod(String jsonPath, @NotNull String method) {
        String APIPathFile = "src/test/java/vn/vietinbank/api/configs/APIPath.json";
        String APIUrlFile = "src/test/java/vn/vietinbank/api/configs/APIUrl.json";
        String service = getValueFromJson(APIUrlFile, String.format("$.%s.%s", getStringBeforeFirstDot(jsonPath), ENV));
        String url = service + getValueFromJson(APIPathFile, String.format("$.%s", jsonPath));
        Map<String, String> headers = Serenity.sessionVariableCalled(HEADERS);
        String body = Serenity.sessionVariableCalled(BODY_REQUEST);
        Map<String, String> params = Serenity.sessionVariableCalled(PARAMS);
        Response response;
        RequestSpecification requestSpec = SerenityRest.given()
                .baseUri(url)
                .relaxedHTTPSValidation()
                .headers(headers);
        response = switch (method) {
            case METHOD_POST -> requestSpec.body(body).post();
            case METHOD_GET -> requestSpec.get();
            case METHOD_GET_PARAMS -> requestSpec.params(params).get();
            default -> null;
        };
        assert response != null;
        logger.info("URL: {}", url);
        try {
            logger.info("Response: {}", responseToJSONObject(response).toString(4));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Serenity.setSessionVariable(RESPONSE).to(responseToJSONObject(response));
        Serenity.setSessionVariable(RESPONSE_STATUS).to(response.statusCode());
    }

    public static boolean callAPIFromExcelData() {
        String excelPath = Serenity.sessionVariableCalled(EXCEL_PATH);
        String sheetName = Serenity.sessionVariableCalled(SHEET_NAME);
        String row = Serenity.sessionVariableCalled(ROW);
        try {
            ExcelActions excelReader = new ExcelActions(excelPath);
            int numberOfRow;
            if (row.equals(RUN_FULL_FILE)) {
                numberOfRow = excelReader.getRowCount(sheetName) - 1;
            } else {
                numberOfRow = Integer.parseInt(row);
            }
            return callAPIAndWriteResultIntoExcel(excelPath, sheetName, numberOfRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean callAPIAndWriteResultIntoExcel(String pathFile, String sheetName, int row) {
        try {
            ExcelActions excelReader = new ExcelActions(pathFile);
            String url = excelReader.getCellData(sheetName, EXCEL_DATA.url.name(), row);
            String body = excelReader.getCellData(sheetName, EXCEL_DATA.body.name(), row);
            Serenity.setSessionVariable(BODY_REQUEST).to(body);
            String method = excelReader.getCellData(sheetName, EXCEL_DATA.method.name(), row);
            String responseExpected = excelReader.getCellData(sheetName, EXCEL_DATA.responseExpected.name(), row);
            callApiWithMethod(url, method);
            JSONObject response = Serenity.sessionVariableCalled(RESPONSE);
            boolean result = false;
            String responseStr = response.toString(4);
            if (responseStr.contains(responseExpected)) {
                result = true;
                writeIntoResultColumn(pathFile, sheetName, row, RESULT_PASS);
            } else {
                writeIntoResultColumn(pathFile, sheetName, row, RESULT_FAIL);
            }
            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void writeIntoResultColumn(String pathFile, String sheetName, int row, String statusRun) {
        ExcelActions excelReader = new ExcelActions(pathFile);
        excelReader.setCellData(sheetName, EXCEL_DATA.result.name(), row, statusRun);
    }

    public enum EXCEL_DATA {
        url,
        headerParam,
        method,
        body,
        responseExpected,
        result,
    }
}
