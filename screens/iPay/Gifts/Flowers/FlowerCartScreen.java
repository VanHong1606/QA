package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class FlowerCartScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Tất cả\"]")
    @AndroidFindBy(xpath = "")
    private WebElement allTxt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đặt mua hoa\"]")
    @AndroidFindBy(xpath = "")
    private WebElement orderFlowerBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic header trash\"]")
    @AndroidFindBy(xpath = "")
    private WebElement trashBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đồng ý\"]")
    @AndroidFindBy(xpath = "")
    private WebElement agreePopupBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic header back\"]")
    @AndroidFindBy(xpath = "")
    private WebElement backBtn;

    public FlowerCartScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void clickAllTxt() {
        click(allTxt);
    }

    public OrderFlowersScreen clickOrderFlowerBtn() {
        click(orderFlowerBtn);
        return new OrderFlowersScreen(appiumDriver);
    }

    public void removeCart() {
        click(trashBtn);
        if (waitForElementVisible(agreePopupBtn, 1)) {
            click(agreePopupBtn);
        }
    }

    public OrderFreshFlowersHomeScreen clickBackBtn() {
        click(backBtn);
        return new OrderFreshFlowersHomeScreen(appiumDriver);
    }
}
