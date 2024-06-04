package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class SearchResultFreshFlowersScreen extends BaseScreen {

    public SearchResultFreshFlowersScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public DetailInfoFlowerScreen clickFlowerByIndex(String index) {
        WebElement flower = findElement("//XCUIElementTypeCollectionView/XCUIElementTypeCell[" + index + "]");
        click(flower);
        return new DetailInfoFlowerScreen(appiumDriver);
    }
}
