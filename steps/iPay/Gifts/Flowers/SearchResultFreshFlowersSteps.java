package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class SearchResultFreshFlowersSteps extends InitObjects {

    public SearchResultFreshFlowersSteps() {
        super();
    }

    @And("chọn bó hoa thứ {string}")
    public void chon_bo_hoa_thu(String index) {
        detailInfoFlowerScreen = searchResultFreshFlowersScreen.clickFlowerByIndex(index);
    }
}
