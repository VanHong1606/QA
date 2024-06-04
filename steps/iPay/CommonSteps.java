package vn.vietinbank.mobile.steps.iPay;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.Serenity;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static vn.vietinbank.runner.Runner.mobileDriver;


public class CommonSteps extends InitObjects {

    public CommonSteps() {
        super();
    }

    @Given("mở ứng dụng {string}")
    public void mo_ung_dung(String nameApp) {
        AppiumDriver appiumDriver;
        appiumDriver = mobileDriver.newDriver(nameApp);
        Serenity.setSessionVariable("nameApp").to(nameApp);
        Serenity.setSessionVariable("appiumDriver").to(appiumDriver);
    }

    @And("tắt ứng dụng")
    public void tat_ung_dung() {
        mobileDriver.closeApplication();
    }
}
