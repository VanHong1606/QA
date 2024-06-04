package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.Transfer.ConfirmTransactionScreen;

public class PayOrderFlowerScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục\"]")
    @AndroidFindBy(xpath = "")
    private WebElement continueBtn;

    public PayOrderFlowerScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public ConfirmTransactionScreen clickContinueBtn() {
        click(continueBtn);
        return new ConfirmTransactionScreen(appiumDriver);
    }
}
