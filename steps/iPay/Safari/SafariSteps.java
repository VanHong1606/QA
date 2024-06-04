package vn.vietinbank.mobile.steps.iPay.Safari;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import vn.vietinbank.mobile.screens.iPay.InitObjects;
import vn.vietinbank.mobile.screens.iPay.Safari.SafariScreen;

import static vn.vietinbank.runner.Runner.mobileDriver;


public class SafariSteps extends InitObjects {

    public SafariSteps() {
        super();
    }

    @And("đến đường dẫn {string}")
    public void den_duong_dan(String url) {
        AppiumDriver appiumDriver = Serenity.sessionVariableCalled("appiumDriver");
        safariScreen = new SafariScreen(appiumDriver);
        safariScreen.goToUrl(url);
    }

    @And("nhập nội dung đã giải mã base64 {string}")
    public void nhap_noi_dung_da_giai_ma_base64(String variableName) {
        String base64Decoded = Serenity.sessionVariableCalled(variableName);
        safariScreen.inputTextToGenerateImage(base64Decoded);
    }

    @And("nhấn Tạo mã QR")
    public void nhan_tao_ma_qr() {
        safariScreen.clickGenerateQRCodeBtn();
    }

    @And("nhấn Tải xuống")
    public void nhan_tai_xuong() {
        safariScreen.clickDownloadImage();
        mobileDriver.closeApplication();
    }
}
