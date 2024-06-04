package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

import static vn.vietinbank.utils.Constants.OTP;


public class ConfirmTransactionScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Xác nhận & hoàn tất\"]")
    @AndroidFindBy(xpath = "")
    private WebElement confirmAndCompleteBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Thông báo\"]")
    @AndroidFindBy(xpath = "")
    private WebElement notificationLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Xác nhận giao dịch\"]")
    @AndroidFindBy(xpath = "")
    private WebElement confirmTransactionLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Đến tài khoản\"]/following-sibling::XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "")
    private WebElement receiverElement;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Số tiền\"]/following-sibling::XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "")
    private WebElement amountElement;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Giao dịch đã được ghi nhận và sẽ được chuyển theo lịch đã tạo.\n" +
            " Quý khách có thể hủy lịch tại Chuyển tiền/ Đặt lịch chuyển tiền.\"]")
    @AndroidFindBy(xpath = "")
    private WebElement createASuccessfulTransferScheduleTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đồng ý\"]")
    @AndroidFindBy(xpath = "")
    private WebElement agreeBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Chúng tôi đã gửi mã OTP\")]")
    @AndroidFindBy(xpath = "")
    private WebElement otpTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Chúng tôi đã gửi mã OTP\")]/following-sibling::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement otpInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[1]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey1;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[2]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey2;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[3]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey3;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[4]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey4;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[5]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey5;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[6]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey6;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[7]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey7;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[8]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey8;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[9]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey9;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeKey[11]")
    @AndroidFindBy(xpath = "")
    private WebElement btnKey0;

    public ConfirmTransactionScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public ResultTransactionScreen clickConfirmAndCompleteBtn() {
        if (waitForElementVisible(otpTxt, 1)) {
            click(otpInput);
            inputOTP(OTP);
        }
        click(confirmAndCompleteBtn);
        return new ResultTransactionScreen(appiumDriver);
    }

    public void inputOTP(String otp) {
        String[] characters = otp.split("");
        for (String character : characters) {
            inputCharacter(character);
        }
        new ResultTransactionScreen(appiumDriver);
    }

    private void inputCharacter(String character) {
        switch (character) {
            case "1":
                click(btnKey1);
                break;
            case "2":
                click(btnKey2);
                break;
            case "3":
                click(btnKey3);
                break;
            case "4":
                click(btnKey4);
                break;
            case "5":
                click(btnKey5);
                break;
            case "6":
                click(btnKey6);
                break;
            case "7":
                click(btnKey7);
                break;
            case "8":
                click(btnKey8);
                break;
            case "9":
                click(btnKey9);
                break;
            case "0":
                click(btnKey0);
                break;
        }
    }

    public boolean verifyConfirmTransactionLabel() {
        return waitForElementVisible(confirmTransactionLabel, 10);
    }

    public String getReceiverName() {
        return getElementText(receiverElement);
    }

    public String getAmount() {
        return getElementText(amountElement);
    }

    public boolean displayNotificationLabel() {
        return waitForElementVisible(notificationLabel, 10);
    }

    public boolean displayCreateASuccessfulTransferScheduleTxt() {
        return waitForElementVisible(createASuccessfulTransferScheduleTxt, 10);
    }

    public TransferAndReceiveMoneyScreen clickAgreeBtn() {
        click(agreeBtn);
        return new TransferAndReceiveMoneyScreen(appiumDriver);
    }
}
