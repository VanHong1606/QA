package vn.vietinbank.api.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static vn.vietinbank.api.common.APIHelper.*;
import static vn.vietinbank.api.common.CommonAPI.callApiWithMethod;
import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.converter.Converter.stringToList;
import static vn.vietinbank.utils.data.CompareData.*;
import static vn.vietinbank.utils.data.DataInput.setBodyJson;
import static vn.vietinbank.utils.data.GetData.getDataWithJsonPath;
import static vn.vietinbank.utils.data.GetData.readJsonFile;

public class CommonSteps {

    @Given("tạo headers request mặc định")
    public void tao_headers_request_mac_dinh() {
        Map<String, String> headers = headersDefault();
        Serenity.setSessionVariable(HEADERS).to(headers);
    }

    @Given("tạo headers request với Accept {string}")
    public void tao_headers_request_voi_accept(String accept) {
        Map<String, String> headers = headersChangeAcceptValue(accept);
        Serenity.setSessionVariable(HEADERS).to(headers);
    }

    @Given("tạo headers request with authorization type {string} and token {string}")
    public void tao_headers_request_with_authorization_type_and_token(String authorizationType, String token) {
        Map<String, String> headers = headersWithAuthorization(authorizationType, token);
        Serenity.setSessionVariable(HEADERS).to(headers);
    }

    @And("tạo request body")
    public void tao_request_body(DataTable dataTable) {
        Serenity.setSessionVariable(BODY_REQUEST).to(createBody(dataTable));
    }

    @And("tạo request params")
    public void tao_request_params(DataTable dataTable) {
        Serenity.setSessionVariable(PARAMS).to(createParams(dataTable));
    }

    @And("gọi api {string} với phương thức {string}")
    public void goi_api_voi_phuong_thuc(String jsonPath, String method) {
        callApiWithMethod(jsonPath, method);
    }

    @Then("httpCode phải là {int}")
    public void httpcode_phai_la(int expectedHttpStatusCode) {
        compareHttpStatusCode(expectedHttpStatusCode);
    }

    @And("response trả về giống với dữ liệu trong file excel")
    public void response_tra_ve_giong_voi_du_lieu_trong_file_excel(DataTable dataTable) {
        try {
            Response response = Serenity.sessionVariableCalled(RESPONSE);
            JSONObject jsonResponse = new JSONObject(response.getBody().asString());
            JSONAssert.assertEquals(getExcelToJson(dataTable), jsonResponse, true);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @And("lấy {string} từ file json {string}")
    public void lay_tu_file_json(String sessionVariableName, String pathJsonFile) {
        readJsonFile(sessionVariableName, pathJsonFile);
    }

    @And("cập nhật lại json {string}")
    public void cap_nhat_lai_json(String sessionVariableName, DataTable dataTable) {
        setBodyJson(sessionVariableName, dataTable);
    }

    @And("so sánh response thực tế với mong muốn")
    public void so_sanh_response_thuc_te_voi_mong_muon() {
        JSONObject actual = Serenity.sessionVariableCalled(RESPONSE);
        JSONObject expected = Serenity.sessionVariableCalled(RESPONSE_EXPECTED);
        try {
            JSONAssert.assertEquals(actual, expected, true);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @And("so sánh response thực tế với mong muốn và không so sánh các trường {string}")
    public void so_sanh_response_thuc_te_voi_mong_muon_va_khong_so_sanh_cac_truong(String ignoreFields) {
        JSONObject actual = Serenity.sessionVariableCalled(RESPONSE);
        JSONObject expected = Serenity.sessionVariableCalled(RESPONSE_EXPECTED);
        List<String> listIgnoreFields = stringToList(ignoreFields);
        boolean actualResult = compareJSONObjectsWithIgnoreFields(actual, expected, listIgnoreFields);
        assertThat(actualResult, equalTo(true));
    }

    @And("so sánh trường có jsonPath {string} của response thực tế với mong muốn")
    public void so_sanh_cac_truong_co_jsonpath_cua_response_thuc_te_voi_mong_muon(String jsonPath) {
        JSONObject actual = Serenity.sessionVariableCalled(RESPONSE);
        JSONObject expected = Serenity.sessionVariableCalled(RESPONSE_EXPECTED);
        boolean actualResult = compareJSONObjectsWithJsonPath(actual, expected, jsonPath);
        assertThat(actualResult, equalTo(true));
    }

    @And("lấy data with json path {string} và lưu vào serenity variable {string}")
    public void lay_data_with_json_path_and_save_to_serenity_variable(String jsonPath, String serenityVariable) {
        JSONObject actual = Serenity.sessionVariableCalled(RESPONSE);
        String saveVariable = getDataWithJsonPath(actual, jsonPath);
        Serenity.setSessionVariable(serenityVariable).to(saveVariable);
    }
}