package vn.vietinbank.mobile.screens.iPay.Gifts.Flowers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;

public class OrderFreshFlowersHomeScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Nơi giao hoa\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement chooseCity;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Chủ đề\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement chooseTopic;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Mức giá (VND)\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement choosePrice;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Thương hiệu\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement chooseBrand;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Tìm kiếm\"]")
    @AndroidFindBy(xpath = "")
    private WebElement searchBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Giỏ hàng\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement cartTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Lịch sử\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement historyTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Khuyến mãi\"]/parent::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "")
    private WebElement promotionTab;

    public OrderFreshFlowersHomeScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void chooseCity(String city) {
        click(chooseCity);
        WebElement cityElement = findElement("//XCUIElementTypeStaticText[@name='" + city + "']/parent::XCUIElementTypeCell");
        click(cityElement);
    }

    public void chooseTopic(String topic) {
        click(chooseTopic);
        WebElement topicElement = findElement("//XCUIElementTypeStaticText[@name='" + topic + "']/parent::XCUIElementTypeCell");
        click(topicElement);
    }

    public void choosePrice(String price) {
        click(choosePrice);
        WebElement priceElement = findElement("//XCUIElementTypeStaticText[@name='" + price + "']/parent::XCUIElementTypeCell");
        click(priceElement);
    }

    public void chooseBrand(String brand) {
        click(chooseBrand);
        WebElement brandElement = findElement("//XCUIElementTypeStaticText[@name='" + brand + "']/parent::XCUIElementTypeCell");
        click(brandElement);
    }

    public SearchResultFreshFlowersScreen clickSearchBtn() {
        click(searchBtn);
        return new SearchResultFreshFlowersScreen(appiumDriver);
    }

    public FlowerCartScreen clickCartTab() {
        click(cartTab);
        return new FlowerCartScreen(appiumDriver);
    }

}
