package vn.vietinbank.mobile.steps.iPay.QRCodeGenerator;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import vn.vietinbank.mobile.screens.iPay.InitObjects;
import vn.vietinbank.mobile.screens.iPay.QRCodeGenerator.QRCodeGeneratorScreen;


public class QrCodeGeneratorSteps extends InitObjects {


    public QrCodeGeneratorSteps() {
        super();
    }

    @And("đến tab tạo QR")
    public void den_tab_tao_qr() {
        AppiumDriver appiumDriver = Serenity.sessionVariableCalled("appiumDriver");
        qrCodeGeneratorScreen = new QRCodeGeneratorScreen(appiumDriver);
        qrCodeGeneratorScreen.clickCreateTab();
    }

    @And("chọn {string}")
    public void chon_loai(String type) {
        qrCodeGeneratorScreen.clickCreateQRCodeByType(type);
    }

    @And("nhập nội dung đã decode base64 {string}")
    public void nhap_noi_dung_da_decode_base64(String variableName) {
        String base64Decoded = Serenity.sessionVariableCalled(variableName);
        qrCodeGeneratorScreen.inputTextToGenerateImage(base64Decoded);
    }

    @And("nhập nội dung qrData {string}")
    public void nhap_noi_dung_qrData(String variableName) {
        String qrData = Serenity.sessionVariableCalled(variableName);
        qrCodeGeneratorScreen.inputTextToGenerateImage(qrData);
    }

    @And("nhấn Generate QR Code")
    public void nhan_generate_qr_code() {
        qrCodeGeneratorScreen.clickGenerateQRCodeBtn();
    }

    @And("tải ảnh xuống")
    public void tai_anh_xuong() {
        qrCodeGeneratorScreen.clickDownloadImageBtn();
    }
}
