package vn.vietinbank.mobile.steps.iPay.HomeScreen;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class SearchFeatureSteps extends InitObjects {

    public SearchFeatureSteps() {
        super();
    }

    @And("nhập tìm kiếm và đến MH {string}")
    public void nhap_tim_kiem_va_den_mh(String feature) {
        if (feature.equals("Gửi tiền mừng lễ, Tết")) {
            sendCongratulatoryMoneyScreen = searchFeatureScreen.goToSendCongratulatoryMoneyScreen(feature);
        }
    }
}
