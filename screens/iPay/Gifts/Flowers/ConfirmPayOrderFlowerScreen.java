package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class ConfirmPayOrderFlowerScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Xác nhận\"]")
    @AndroidFindBy(xpath = "")
    private WebElement confirmBtn;

    public ConfirmPayOrderFlowerScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public PayOrderFlowerScreen clickConfirmBtn() {
        click(confirmBtn);
        return new PayOrderFlowerScreen(appiumDriver);
    }
}
