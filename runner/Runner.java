package vn.vietinbank.runner;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import net.serenitybdd.core.Serenity;
import org.junit.runner.RunWith;
import vn.vietinbank.mobile.common.MobileDriver;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"vn/vietinbank/mobile/steps/iPay", "vn/vietinbank/api/steps", "vn/vietinbank/api/steps/QRSmartPOS"})

public class Runner {

    public static MobileDriver mobileDriver;

    @Before
    public static void before() {
        mobileDriver = new MobileDriver();
    }

    @After
    public static void after() {
        AppiumDriver appiumDriver = Serenity.sessionVariableCalled("appiumDriver");
        if (appiumDriver != null) {
            mobileDriver.closeApplication();
        }
    }
}
