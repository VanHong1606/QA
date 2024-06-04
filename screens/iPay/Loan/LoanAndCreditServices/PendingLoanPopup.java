package vn.vietinbank.mobile.screens.iPay.Loan.LoanAndCreditServices;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class PendingLoanPopup extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Có khoản vay đang chờ\"]")
    @AndroidFindBy(xpath = "")
    private WebElement lblPendingLoan;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục mở khoản vay\"]")
    @AndroidFindBy(xpath = "")
    private WebElement btnContinue;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Thực hiện lại\"]")
    @AndroidFindBy(xpath = "")
    private WebElement btnRetry;

    public PendingLoanPopup(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public void clickRetry() {
        btnRetry.click();
    }

    public boolean isDisplayed() {
        return waitForElementVisible(lblPendingLoan, 10);
    }
}
