package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.PLATFORM;

public class TransferMoneyInsideSystemScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục\"]")
    @AndroidFindBy(xpath = "")
    private WebElement continueBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Số tiền\"]")
    @AndroidFindBy(xpath = "")
    private WebElement amountInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic keyboarddown\"]")
    @AndroidFindBy(xpath = "")
    private WebElement hideKeyboardBtn;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"ic drop down blue\"])[1]")
    @AndroidFindBy(xpath = "")
    private WebElement transferTimeIcon;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"ic drop down blue\"])[2]")
    @AndroidFindBy(xpath = "")
    private WebElement transferFrequencyIcon;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tham gia Bảo hiểm an ninh mạng Miễn phí\"]")
    @AndroidFindBy(xpath = "")
    private WebElement checkedInsurance;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tham gia Bảo hiểm an ninh mạng Miễn phí\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement checkInsuranceBtn;

    public TransferMoneyInsideSystemScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public ConfirmTransactionScreen clickContinueBtnAfterInputAmount() {
        click(continueBtn);
        return new ConfirmTransactionScreen(appiumDriver);
    }

    public ConfirmTransactionScreen clickContinueBtn() {
        click(continueBtn);
        return new ConfirmTransactionScreen(appiumDriver);
    }

    private void clickTransferTimeIcon() {
        click(transferTimeIcon);
    }

    private void clickTransferFrequencyIcon() {
        click(transferFrequencyIcon);
    }

    private void clickTransferTimeByText(String transferTimeText) {
        WebElement paymentAccountNumber = null;
        if (PLATFORM.equals(IOS)) {
            paymentAccountNumber = findElement("//XCUIElementTypeStaticText[@name=\"Chọn thời gian chuyển\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther//XCUIElementTypeStaticText[@name='" + transferTimeText + "']/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeButton");
        }
        click(paymentAccountNumber);
    }

    private void clickTransferFrequencyIconByText(String transferFrequencyText) {
        WebElement transferFrequency = null;
        if (PLATFORM.equals(IOS)) {
            transferFrequency = findElement("//XCUIElementTypeStaticText[@name=\"Chọn tần suất\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[@name='" + transferFrequencyText + "']/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeButton");
        }
        click(transferFrequency);
    }

    public void chooseTransferTimeByText(String transferTimeText) {
        clickTransferTimeIcon();
        clickTransferTimeByText(transferTimeText);
    }

    public void chooseTransferFrequencyByText(String transferFrequencyText) {
        clickTransferFrequencyIcon();
        clickTransferFrequencyIconByText(transferFrequencyText);
    }

    public void clickHideKeyboardBtn() {
        click(hideKeyboardBtn);
        delay(2000);
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
        click(checkInsuranceBtn);
    }
}
