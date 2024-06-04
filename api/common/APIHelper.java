package vn.vietinbank.api.common;

import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpHeaders.*;
import static org.htmlunit.util.MimeType.APPLICATION_JSON;
import static org.htmlunit.util.MimeType.APPLICATION_XML;
import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.excel.ExcelActions.excelToJsonConverter;

public class APIHelper {

    private static final Logger logger = LogManager.getLogger(APIHelper.class);

    public static Map<String, String> headersDefault() {
        Map<String, String> map = new HashMap<>();
        map.put(CONTENT_TYPE, APPLICATION_JSON);
        map.put(ACCEPT, ALL_TYPE);
        logger.info(map.toString());
        return map;
    }

    public static Map<String, String> headersChangeAcceptValue(String acceptValue) {
        Map<String, String> map = new HashMap<>();
        map.put(CONTENT_TYPE, APPLICATION_JSON);
        map.put(ACCEPT, acceptValue);
        logger.info(map.toString());
        return map;
    }

    public static Map<String, String> headersDefaultXML() {
        Map<String, String> map = new HashMap<>();
        map.put(CONTENT_TYPE, APPLICATION_XML);
        map.put(ACCEPT, ALL_TYPE);
        logger.info(map.toString());
        return map;
    }

    public static Map<String, String> headersWithAuthorization(String typeAuthorization, String authorization) {
        Map<String, String> map = headersDefault();
        String auth = "";
        switch (typeAuthorization) {
            case BEARER:
                auth = BEARER + " " + authorization;
                break;
            case BASIC_AUTH:
                auth = authorization;
                break;
            default:
                break;
        }
        map.put(AUTHORIZATION, auth);
        logger.info(map.toString());
        return map;
    }

    public static Map<String, String> createParams(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        logger.info(dataList.get(0).toString());
        return dataList.get(0);
    }

    public static Map<String, String> createBody(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        logger.info(dataList.get(0).toString());
        return dataList.get(0);
    }

    public static JSONObject getExcelToJson(DataTable dataTable) {
        try {
            List<List<String>> data = dataTable.asLists();
            return new JSONObject(excelToJsonConverter(data.get(1).get(0), data.get(1).get(1), data.get(1).get(2)));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}