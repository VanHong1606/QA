package vn.vietinbank.mobile.screens.iPay.UserAction.Login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.HomeScreen.HomeScreen;
import vn.vietinbank.mobile.screens.iPay.UserAction.OTP.OTPScreen;

public class LoginScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Tên đăng nhập\"]")
    @AndroidFindBy(xpath = "")
    private WebElement txtUsername;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value=\"Mật khẩu\"]")
    @AndroidFindBy(xpath = "")
    private WebElement txtPassword;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đăng nhập\"]")
    @AndroidFindBy(xpath = "")
    private WebElement btnLogin;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Đăng nhập tài khoản khác ?\"]")
    @AndroidFindBy(xpath = "")
    private WebElement loginWithOtherAccountTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Có\"]")
    @AndroidFindBy(xpath = "")
    private WebElement yesBtn;

    public LoginScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void inputUserName(String userName) {
        sendKeys(txtUsername, userName);
    }

    private void inputPassword(String password) {
        sendKeys(txtPassword, password);
    }

    private void clickLoginButton() {
        click(btnLogin);
    }

    public HomeScreen loginWithPassword(String password) {
        inputPassword(password);
        clickLoginButton();
        return new HomeScreen(appiumDriver);
    }

    public OTPScreen loginWithUsernamePassword(String username, String password) {
        inputUserName(username);
        inputPassword(password);
        clickLoginButton();
        return new OTPScreen(appiumDriver);
    }

    public void clickLoginWithOtherAccountTxt() {
        click(loginWithOtherAccountTxt);
        click(yesBtn);
        delay(1000);
    }
}
