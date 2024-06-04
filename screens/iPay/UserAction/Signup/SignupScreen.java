package vn.vietinbank.mobile.screens.iPay.UserAction.Signup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.UserAction.Login.LoginScreen;

public class SignupScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đăng nhập/Đăng ký\" or @name=\"Đăng nhập\"]")
    @AndroidFindBy(xpath = "")
    private WebElement btnLogin;

    public SignupScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public LoginScreen clickLoginOrSignUpBtn() {
        click(btnLogin);
        return new LoginScreen(appiumDriver);
    }
}
