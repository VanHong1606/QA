package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class ScheduleMoneyTransferScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, \"Liên ngân hàng\")]/following-sibling::XCUIElementTypeButton[1]")
    @AndroidFindBy(xpath = "")
    private WebElement interBankBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Đặt lịch chuyển tiền tự động mới\"]/following-sibling::XCUIElementTypeButton[1]")
    @AndroidFindBy(xpath = "")
    private WebElement setANewAutomaticMoneyTransferScheduleBtn;

    public ScheduleMoneyTransferScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
