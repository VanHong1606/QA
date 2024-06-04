package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class AddReceiverScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Địa chỉ nhận hoa\"]/following-sibling::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement listReceiverBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Hoa gửi tặng cho\"]/parent::XCUIElementTypeOther/XCUIElementTypeButton")
    @AndroidFindBy(xpath = "")
    private WebElement chooseRelationship;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Giờ nhận hàng\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement chooseTime;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Lời nhắn cho người nhận\"]/following-sibling::XCUIElementTypeTextView")
    @AndroidFindBy(xpath = "")
    private WebElement messageInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Ghi chú cho nhà cung cấp\"]/following-sibling::XCUIElementTypeTextView")
    @AndroidFindBy(xpath = "")
    private WebElement noteInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Thêm người nhận\"]")
    @AndroidFindBy(xpath = "")
    private WebElement addReceiverBtn;

    public AddReceiverScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public AddressReceiveFlowersScreen clickListReceiverBtn() {
        click(listReceiverBtn);
        return new AddressReceiveFlowersScreen(appiumDriver);
    }

    private void clickChooseRelationship() {
        click(chooseRelationship);
    }

    private void clickChooseTime() {
        click(chooseTime);
    }

    public void chooseRelationship(String relationship) {
        clickChooseRelationship();
        WebElement relationshipElement = findElement("//XCUIElementTypeStaticText[@name='" + relationship + "']/parent::XCUIElementTypeCell");
        click(relationshipElement);
    }

    public void chooseTime(String time) {
        clickChooseTime();
        WebElement timeElement = findElement("//XCUIElementTypeStaticText[@name='" + time + "']/parent::XCUIElementTypeCell");
        click(timeElement);
    }

    public void inputMessage(String message) {
        sendKeys(messageInput, message);
    }

    public void inputNote(String note) {
        sendKeys(noteInput, note);
    }

    public OrderFlowersScreen clickAddReceiverBtn() {
        click(addReceiverBtn);
        return new OrderFlowersScreen(appiumDriver);
    }
}
