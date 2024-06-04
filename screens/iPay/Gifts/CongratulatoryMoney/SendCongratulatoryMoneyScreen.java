package vn.vietinbank.mobile.screens.iPay.Gifts.CongratulatoryMoney;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.Transfer.ConfirmTransactionScreen;

public class SendCongratulatoryMoneyScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconLineAddnew\"]")
    @AndroidFindBy(xpath = "")
    private WebElement iconLineAddNew;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Số tài khoản/Alias/số điện thoại\"]")
    @AndroidFindBy(xpath = "")
    private WebElement receiverInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"THÊM NGƯỜI NHẬN NÀY\"]")
    @AndroidFindBy(xpath = "")
    private WebElement addReceiverBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Done\"]")
    @AndroidFindBy(xpath = "")
    private WebElement doneBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Số khác\"]")
    @AndroidFindBy(xpath = "")
    private WebElement anotherAmount;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Số tiền mừng\"]")
    @AndroidFindBy(xpath = "")
    private WebElement amountInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic keyboarddown\"]")
    @AndroidFindBy(xpath = "")
    private WebElement hideKeyboardBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView[@value=\"Chọn hoặc nhập lời chúc\"]")
    @AndroidFindBy(xpath = "")
    private WebElement wishesInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Xong\"]")
    @AndroidFindBy(xpath = "")
    private WebElement doneBtnOnKeyBoard;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục\"]")
    @AndroidFindBy(xpath = "")
    private WebElement continueBtn;

    public SendCongratulatoryMoneyScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void clickIconLineAddNew() {
        click(iconLineAddNew);
    }

    private void clickAddReceiverBtn() {
        click(addReceiverBtn);
    }

    private void inputReceiver(String receiver) {
        sendKeys(receiverInput, receiver);
    }

    private void clickDoneBtn() {
        click(doneBtn);
    }

    private void clickAmount() {
        click(anotherAmount);
    }

    private void scrollToInputAmount() {
        click(amountInput);
    }

    private void inputAmount(String amount) {
        sendKeys(amountInput, amount);
    }

    private void clickHideKeyboardBtn() {
        click(hideKeyboardBtn);
    }

    public ConfirmTransactionScreen clickContinueBtn() {
        click(continueBtn);
        return new ConfirmTransactionScreen(appiumDriver);
    }

    private void inputWishes(String wishes) {
        sendKeys(wishesInput, wishes);
    }

    private void clickDoneBtnOnKeyBoard() {
        click(doneBtnOnKeyBoard);
    }

    public void addNewReceiver(String receiver) {
        clickIconLineAddNew();
        inputReceiver(receiver);
        clickDoneBtn();
        clickAddReceiverBtn();
    }

    public void inputAmountToSend(String amount) {
        clickAmount();
        scrollToInputAmount();
        inputAmount(amount);
        clickHideKeyboardBtn();
    }

    public void inputWishesToSend(String wishes) {
        inputWishes(wishes);
        delay(2000);
        clickDoneBtnOnKeyBoard();
    }
}
