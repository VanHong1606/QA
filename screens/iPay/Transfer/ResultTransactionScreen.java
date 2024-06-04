package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.HomeScreen.HomeScreen;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.PLATFORM;

public class ResultTransactionScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Kết quả giao dịch\"]")
    @AndroidFindBy(xpath = "")
    private WebElement resultTransactionLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Kết quả giao dịch\"]")
    @AndroidFindBy(xpath = "")
    private WebElement congratulatoryDepositHasBeenCompletedTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconLineHome\"]")
    @AndroidFindBy(xpath = "")
    private WebElement iconLineHome;

    public ResultTransactionScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public boolean verifyResultTransactionLabel() {
        return waitForElementVisible(resultTransactionLabel, 10);
    }

    public boolean verifyCongratulatoryDepositHasBeenCompleted() {
        return waitForElementVisible(congratulatoryDepositHasBeenCompletedTxt, 10);
    }

    public String getAccountNumber() {
        WebElement locator = null;
        if (PLATFORM.equals(IOS)) {
            locator = findElement("//XCUIElementTypeStaticText[@name=\"Đến tài khoản\"]/following-sibling::XCUIElementTypeStaticText");
        }
        return getElementText(locator);
    }

    public String getCardCode() {
        WebElement locator = null;
        if (PLATFORM.equals(IOS)) {
            locator = findElement("//XCUIElementTypeStaticText[@name=\"Đến số thẻ\"]/following-sibling::XCUIElementTypeStaticText");
        }
        return getElementText(locator);
    }

    public String getBankName() {
        WebElement locator = null;
        if (PLATFORM.equals(IOS)) {
            locator = findElement("//XCUIElementTypeStaticText[@name=\"Ngân hàng\"]/following-sibling::XCUIElementTypeStaticText");
        }
        return getElementText(locator);
    }

    public String getAmount() {
        WebElement locator = null;
        if (PLATFORM.equals(IOS)) {
            locator = findElement("//XCUIElementTypeStaticText[@name=\"Số tiền\"]/following-sibling::XCUIElementTypeStaticText");
        }
        return getElementText(locator);
    }

    public HomeScreen backToHome() {
        delay(5000);
        click(iconLineHome);
        return new HomeScreen(appiumDriver);
    }
}
