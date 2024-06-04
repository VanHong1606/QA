package vn.vietinbank.mobile.screens.iPay.Transfer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.PLATFORM;

public class TransferMoneyScreen extends BaseScreen {
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Số tài khoản\"]/parent::XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "")
    private WebElement accountNumberInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Chọn ngân hàng nhận\"]/following-sibling::XCUIElementTypeButton")
    @AndroidFindBy(xpath = "")
    private WebElement listBankSelect;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Tìm theo tên ngân hàng\"]")
    @AndroidFindBy(xpath = "")
    private WebElement searchBankInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tiếp tục\"]")
    @AndroidFindBy(xpath = "")
    private WebElement continueBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tham gia miễn phí Bảo hiểm an ninh mạng\"]/parent::XCUIElementTypeOther/XCUIElementTypeButton")
    @AndroidFindBy(xpath = "")
    private WebElement cybersecurityCoverageCheckbox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeButton[2]")
    @AndroidFindBy(xpath = "")
    private WebElement searchInListOfBeneficiariesBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Nhập từ khóa để tìm\"]")
    @AndroidFindBy(xpath = "")
    private WebElement searchInListOfBeneficiariesInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tới số tài khoản\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement toAccountNumberTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tới số thẻ\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement toCardCodeTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Nhập số in trên thẻ\"]/parent::XCUIElementTypeOther/XCUIElementTypeTextField")
    @AndroidFindBy(xpath = "")
    private WebElement cardCodeInput;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Xong\"]")
    @AndroidFindBy(xpath = "")
    private WebElement doneBtnOnKeyBoard;

    public TransferMoneyScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void clickToAccountNumberTab() {
        click(toAccountNumberTab);
    }

    public void clickToCardCodeTab() {
        click(toCardCodeTab);
    }

    public void cardCodeInput(String cardCode) {
        sendKeys(cardCodeInput, cardCode);
    }

    public void clickDoneBtnOnKeyBoard() {
        click(doneBtnOnKeyBoard);
    }

    private void clickSearchBeneficiariesBtn() {
        click(searchInListOfBeneficiariesBtn);
    }

    private void inputSearchBeneficiaries(String beneficiary) {

        sendKeys(searchInListOfBeneficiariesInput, beneficiary);
    }

    private void clickBeneficiaryByName(String beneficiary) {
        WebElement beneficiaryElement = null;
        if (PLATFORM.equals(IOS)) {
            beneficiaryElement = findElement("//XCUIElementTypeStaticText[contains(@name, '" + beneficiary + "')]");
        }
        click(beneficiaryElement);
    }

    public TransferMoneyOutsideSystemScreen chooseInListOfBeneficiariesOutside(String beneficiary) {
        clickSearchBeneficiariesBtn();
        inputSearchBeneficiaries(beneficiary);
        clickBeneficiaryByName(beneficiary);
        return new TransferMoneyOutsideSystemScreen(appiumDriver);
    }

    public TransferMoneyInsideSystemScreen chooseInListOfBeneficiariesInside(String beneficiary) {
        clickSearchBeneficiariesBtn();
        inputSearchBeneficiaries(beneficiary);
        clickBeneficiaryByName(beneficiary);
        return new TransferMoneyInsideSystemScreen(appiumDriver);
    }

    public TransferMoneyOutsideSystemScreen clickContinueBtn() {
        click(continueBtn);
        return new TransferMoneyOutsideSystemScreen(appiumDriver);
    }

    public void inputAccountNumber(String accountNumber) {
        sendKeys(accountNumberInput, accountNumber);
    }

    private void clickListBankSelect() {
        click(listBankSelect);
    }

    private void inputSearchBank(String bankName) {
        sendKeys(searchBankInput, bankName);
    }

    private void clickBankInListSearch(String bankName) {
        WebElement bank = findElement("//XCUIElementTypeStaticText[@name=\"Vietcombank\"]/parent::XCUIElementTypeCell[1]");
        click(bank);
    }

    public void chooseBank(String bankName) {
        clickListBankSelect();
        inputSearchBank(bankName);
        clickBankInListSearch(bankName);
    }
}
