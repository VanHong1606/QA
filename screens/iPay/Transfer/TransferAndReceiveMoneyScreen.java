package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class TransferAndReceiveMoneyScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Đặt lịch chuyển tiền\"])[2]/following-sibling::XCUIElementTypeButton[1]")
    @AndroidFindBy(xpath = "")
    private WebElement scheduleMoneyTransferBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Chuyển trong & ngoài hệ thống\"]")
    @AndroidFindBy(xpath = "")
    private WebElement transferAndReceiveMoneyTitle;

    public TransferAndReceiveMoneyScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public ScheduleMoneyTransferScreen clickScheduleMoneyTransferBtn() {
        click(scheduleMoneyTransferBtn);
        return new ScheduleMoneyTransferScreen(appiumDriver);
    }

    public boolean displayTransferAndReceiveMoneyTitle() {
        return waitForElementVisible(transferAndReceiveMoneyTitle, 10);
    }
}
