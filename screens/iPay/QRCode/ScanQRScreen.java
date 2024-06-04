package vn.vietinbank.mobile.screens.iPay.QRCode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import vn.vietinbank.mobile.common.BaseScreen;
import vn.vietinbank.mobile.screens.iPay.Transfer.TransferMoneyInsideSystemScreen;
import vn.vietinbank.mobile.screens.iPay.Transfer.TransferMoneyOutsideSystemScreen;

import java.time.Duration;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.PLATFORM;

public class ScanQRScreen extends BaseScreen {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Album\"]")
    @AndroidFindBy(xpath = "")
    private WebElement albumTxt;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"Album\"])[2]")
    @AndroidFindBy(xpath = "")
    private WebElement albumTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Chọn\"]")
    @AndroidFindBy(xpath = "")
    private WebElement chooseTxt;

    public ScanQRScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void swipeUpToAlbum() {
        Point start = new Point(215, 900);
        Point end = new Point(215, 200);
        swipe(start, end, Duration.ofSeconds(2));
    }

    private void clickAlbumTxt() {
        click(albumTxt);
    }

    private void clickAlbumByNameTab() {
        click(albumTab);
    }

    private void clickChooseTxt() {
        click(chooseTxt);
    }

    private void clickAlbumByName(String albumName) {
        WebElement album = null;
        if (PLATFORM.equals(IOS)) {
            album = findElement("//XCUIElementTypeOther[@name='" + albumName + "']");
        }
        click(album);
    }

    private void clickImageByIndex(String index, String albumName) {
        WebElement firstImage = null;
        if (PLATFORM.equals(IOS)) {
            if (albumName == null) {
                firstImage = findElement("(//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[" + index + "])[4]");
            } else {
                firstImage = findElement("(//XCUIElementTypeOther[@name='" + albumName + "']//XCUIElementTypeImage)[" + index + "]");
            }
        }
        click(firstImage);
    }

    public TransferMoneyOutsideSystemScreen chooseImageInAlbumByIndex(String index, String albumName) {
        swipeUpToAlbum();
        clickAlbumTxt();
        delay(3000);
        clickAlbumByNameTab();
        clickAlbumByName(albumName);
        clickImageByIndex(index, albumName);
        clickChooseTxt();
        return new TransferMoneyOutsideSystemScreen(appiumDriver);
    }

    public TransferMoneyInsideSystemScreen chooseImageInAlbumDefaultByIndex(String index) {
        swipeUpToAlbum();
        clickAlbumTxt();
        delay(3000);
        clickImageByIndex(index, null);
        clickChooseTxt();
        return new TransferMoneyInsideSystemScreen(appiumDriver);
    }
}
