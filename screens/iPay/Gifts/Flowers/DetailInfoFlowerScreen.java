package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

import static vn.vietinbank.utils.Constants.MINUS;
import static vn.vietinbank.utils.Constants.PLUS;

public class DetailInfoFlowerScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Thêm vào giỏ\"]")
    @AndroidFindBy(xpath = "")
    private WebElement addToCart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Đặt ngay\"]")
    @AndroidFindBy(xpath = "")
    private WebElement orderNow;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic plus\"]")
    @AndroidFindBy(xpath = "")
    private WebElement plusVolumeIcon;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"ic minus\"]")
    @AndroidFindBy(xpath = "")
    private WebElement minusVolumeIcon;

    public DetailInfoFlowerScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void plusVolume(String volume) {
        int volumeInt = Integer.parseInt(volume);
        for (int i = 0; i < volumeInt; i++) {
            click(plusVolumeIcon);
        }
    }

    private void minusVolume(String volume) {
        int volumeInt = Integer.parseInt(volume);
        for (int i = 0; i < volumeInt; i++) {
            click(minusVolumeIcon);
        }
    }

    public void changeVolume(String type, String volume) {
        switch (type) {
            case PLUS:
                plusVolume(volume);
                break;
            case MINUS:
                minusVolume(volume);
                break;
            default:
                break;
        }
    }

    public FlowerCartScreen clickAddToCart() {
        click(addToCart);
        return new FlowerCartScreen(appiumDriver);
    }
}
