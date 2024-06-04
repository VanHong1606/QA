package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.PLATFORM;

public class TransferMoneyOutsideSystemScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục\"]")
    @AndroidFindBy(xpath = "")
    private WebElement continueBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Số tiền\"]")
    @AndroidFindBy(xpath = "")
    private WebElement amountInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic keyboarddown\"]")
    @AndroidFindBy(xpath = "")
    private WebElement hideKeyboardBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tham gia Bảo hiểm an ninh mạng Miễn phí\"]")
    @AndroidFindBy(xpath = "")
    private WebElement checkedInsurance;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"iconChoiceCheckOn\"]/following-sibling::XCUIElementTypeButton")
    @AndroidFindBy(xpath = "")
    private WebElement checkInsuranceBtn;

    public TransferMoneyOutsideSystemScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public ConfirmTransactionScreen clickContinueBtnAfterInputAmount() {
        click(continueBtn);
        return new ConfirmTransactionScreen(appiumDriver);
    }

    public void clickHideKeyboardBtn() {
        click(hideKeyboardBtn);
    }

    public void inputAmount(String amount) {
        sendKeys(amountInput, amount);
    }

    public boolean displayReceiverName(String receiverName) {
        WebElement receiverElement = null;
        if (PLATFORM.equals(IOS)) {
            receiverElement = findElement("//XCUIElementTypeStaticText[@name='" + receiverName + "']");
        }
        return waitForElementVisible(receiverElement, 10);
    }

    public void uncheckInsurance() {
        if (waitForElementVisible(checkedInsurance, 1)) {
            click(checkInsuranceBtn);
        }
    }
}
