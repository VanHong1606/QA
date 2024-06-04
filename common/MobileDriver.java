package vn.vietinbank.mobile.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static io.appium.java_client.remote.MobilePlatform.IOS;
import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.data.GetData.getValueFromJson;

public class MobileDriver {

    private static final Logger logger = LogManager.getLogger(MobileDriver.class);
    private static final String ADDRESS = "127.0.0.1";
    private static AppiumDriverLocalService service;
    private AppiumDriver appiumDriver;

    private final String devicesFilePath = String.format("src/test/java/vn/vietinbank/mobile/configs/%sDevices.json", PLATFORM);
    private final String platformVersion = getValueFromJson(devicesFilePath, String.format("$.devices.%s.platformVersion", udid));
    private final String deviceName = getValueFromJson(devicesFilePath, String.format("$.devices.%s.deviceName", udid));

    public MobileDriver() {
    }

    public static void stopAppiumServer() {
        service.stop();
    }

    private void startAppiumServer() {
        logger.info("Starting appium server");
        try {
            service = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().withIPAddress(ADDRESS).usingAnyFreePort()
                            .usingDriverExecutable(new File(NODE_PATH)).
                            withAppiumJS(new File(APPIUM_PATH)));
            service.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private URL getUrl() {
        return service.getUrl();
    }

    public AppiumDriver newDriver(String nameApp) {
        startAppiumServer();
        logger.info("Starting appium driver {}", PLATFORM);
        AppiumDriver driver;
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("PLATFORM", PLATFORM);
            dc.setCapability("appium:platformVersion", platformVersion);
            dc.setCapability("appium:udid", udid);
            dc.setCapability("appium:deviceName", deviceName);
            String appConfigsPath = "src/test/java/vn/vietinbank/mobile/configs/AppConfigs.json";
            if (PLATFORM.equals(IOS)) {
                String bundleId = getValueFromJson(appConfigsPath, String.format("$.%s.%s.bundleId", nameApp, PLATFORM));
                dc.setCapability("appium:bundleId", bundleId);
                dc.setCapability("appium:automationName", "XCUITest");
            } else {
                String appPackage = getValueFromJson(appConfigsPath, String.format("$.%s.%s.appPackage", nameApp, PLATFORM));
                String appActivity = getValueFromJson(appConfigsPath, String.format("$.%s.%s.appActivity", nameApp, PLATFORM));
                dc.setCapability("appium:appPackage", appPackage);
                dc.setCapability("appium:appActivity", appActivity);
                dc.setCapability("appium:automationName", "UIAutomator2");
            }
            dc.setCapability("noReset", "true");
            driver = new IOSDriver(getUrl(), dc);
            appiumDriver = driver;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("Driver {}: {}", PLATFORM, driver);
        return driver;
    }

    public void closeApplication() {
        String appID;
        if (PLATFORM.equals(IOS)) {
            appID = (String) appiumDriver.getCapabilities().getCapability("appium:bundleId");
        } else {
            appID = (String) appiumDriver.getCapabilities().getCapability("appium:appPackage");
        }
        if (appID != null)
            ((InteractsWithApps) appiumDriver).terminateApp(appID);
        stopAppiumServer();
    }
}
