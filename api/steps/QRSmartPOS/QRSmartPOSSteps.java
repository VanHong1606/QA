package vn.vietinbank.api.steps.QRSmartPOS;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.Serenity;

import java.util.Map;

import static vn.vietinbank.api.common.APIHelper.headersDefault;
import static vn.vietinbank.utils.Constants.HEADERS;
import static vn.vietinbank.utils.converter.Converter.decodeBase64String;
import static vn.vietinbank.utils.data.GetData.getValueFromJson;

public class QRSmartPOSSteps {

    @Given("tạo headers request QRSmartPOS")
    public void tao_headers_request_QRSmartPOS() {
        Map<String, String> headers = headersDefault();
        String configPath = "src/test/java/vn/vietinbank/api/configs/QRSmartPOS/headerParams.json";
        String keyClientSecret = getValueFromJson(configPath, "$.QRSmartPOS[0].key");
        String valueClientSecret = getValueFromJson(configPath, "$.QRSmartPOS[0].value");
        String keyClientId = getValueFromJson(configPath, "$.QRSmartPOS[1].key");
        String valueClientId = getValueFromJson(configPath, "$.QRSmartPOS[1].value");
        headers.put(keyClientSecret, valueClientSecret);
        headers.put(keyClientId, valueClientId);
        Serenity.setSessionVariable(HEADERS).to(headers);
    }

    @And("giải mã base64 {string} và lưu với tên biến {string}")
    public void giai_ma_base64_va_luu_voi_ten_bien(String serenityVariable, String variableNameSave) {
        String saveVariable = Serenity.sessionVariableCalled(serenityVariable);
        String base64Decoded = decodeBase64String(saveVariable);
        Serenity.setSessionVariable(variableNameSave).to(base64Decoded);
    }
}