package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class PayOrderFlowerSteps extends InitObjects {

    public PayOrderFlowerSteps() {
        super();
    }

    @And("nhấn Tiếp tục thanh toán đến MH Xác nhận GD")
    public void nhan_tiep_tuc_thanh_toan_den_mh_xac_nhan_gd() {
        confirmTransactionScreen = payOrderFlowerScreen.clickContinueBtn();
    }
}
