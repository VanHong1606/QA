package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class AddressReceiveFlowersScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Xác nhận\"]")
    @AndroidFindBy(xpath = "")
    private WebElement confirmBtn;

    public AddressReceiveFlowersScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void chooseAddressByIndex(String index) {
        WebElement address = findElement("//XCUIElementTypeTable/XCUIElementTypeCell[" + index + "]");
        click(address);
    }

    public AddReceiverScreen clickConfirmBtn() {
        click(confirmBtn);
        return new AddReceiverScreen(appiumDriver);
    }
}
