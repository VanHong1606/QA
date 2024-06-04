package vn.vietinbank.utils;

import static vn.vietinbank.utils.data.GetData.getValueOf;

public class Constants {

    //platform
    public static final String ENV = getValueOf("env");
    //platform
    public static final String PLATFORM = getValueOf("platformName");
    //otp
    public static final String OTP = getValueOf("otp");
    //appium
    public static final long APPIUM_TIMEOUT = Long.parseLong(getValueOf("appiumTimeOut"));
    public static final String NODE_PATH = getValueOf("nodePath");
    public static final String APPIUM_PATH = getValueOf("appiumPath");
    public static final String udid = getValueOf("udid");
    //plus/minus
    public static final String PLUS = "Thêm";
    public static final String MINUS = "Xoá bớt";
    //answer
    public static final String YES = "Có";
    public static final String NO = "Không";
    //account
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    //headers
    public static final String BEARER = "Bearer";
    public static final String BASIC_AUTH = "Basic Auth";
    public static final String ALL_TYPE = "*/*";
    //method
    public static final String METHOD_GET = "GET";
    public static final String METHOD_GET_PARAMS = "GET_PARAMS";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_PATCH = "PATCH";
    public static final String METHOD_DELETE = "DELETE";
    //results
    public static final String RESULT_PASS = "PASS";
    public static final String RESULT_FAIL = "FAIL";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    //other
    public static final String RUN_FULL_FILE = "Run Full File";
    //serenity session variable
    public static final String HEADERS = "headers";
    public static final String PARAMS = "params";
    public static final String BODY_REQUEST = "BODY_REQUEST";
    public static final String RESPONSE = "response";
    public static final String RESPONSE_STATUS = "responseStatus";
    public static final String RESPONSE_EXPECTED = "RESPONSE_EXPECTED";
    //excel
    public static final String EXCEL_PATH = "excelPath";
    public static final String SHEET_NAME = "sheetName";
    public static final String ROW = "row";
    //command
    public static final String FROM_COMMAND_LINE = "FROM_COMMAND_LINE";
    public static final String FROM_CONF_FILE = "FROM_CONF_FILE";
    public static final String FROM_ACCOUNTS_FILE = "FROM_ACCOUNTS_FILE";


}
