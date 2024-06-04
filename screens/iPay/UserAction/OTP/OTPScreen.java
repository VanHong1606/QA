package vn.vietinbank.mobile.screens.iPay.UserAction.OTP;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.HomeScreen.HomeScreen;

public class OTPScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[1]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey1;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[2]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey2;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[3]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey3;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[4]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey4;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[5]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey5;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[6]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey6;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[7]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey7;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[8]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey8;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[9]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey9;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[11]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey0;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Hoàn thành\"]")
    @AndroidFindBy(xpath = "")
    private WebElement btnHoanThanh;


    public OTPScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public HomeScreen inputOTP(String otp) {
        String[] characters = otp.split("");
        for (String character : characters) {
            inputCharacter(character);
        }
        clickDone();
        return new HomeScreen(appiumDriver);
    }

    private void inputCharacter(String character) {
        switch (character) {
            case "1":
                click(btnKey1);
                break;
            case "2":
                click(btnKey2);
                break;
            case "3":
                click(btnKey3);
                break;
            case "4":
                click(btnKey4);
                break;
            case "5":
                click(btnKey5);
                break;
            case "6":
                click(btnKey6);
                break;
            case "7":
                click(btnKey7);
                break;
            case "8":
                click(btnKey8);
                break;
            case "9":
                click(btnKey9);
                break;
            case "0":
                click(btnKey0);
                break;
        }
    }

    private void clickDone() {
        click(btnHoanThanh);
    }
}
