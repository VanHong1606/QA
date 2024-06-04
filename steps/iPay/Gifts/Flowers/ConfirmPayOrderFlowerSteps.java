package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class ConfirmPayOrderFlowerSteps extends InitObjects {

    public ConfirmPayOrderFlowerSteps() {
        super();
    }

    @And("nhấn Xác nhận đến MH Thanh toán đặt hoa")
    public void nhan_xac_nhan_den_mh_thanh_toan_dat_hoa() {
        payOrderFlowerScreen = confirmPayOrderFlowerScreen.clickConfirmBtn();
    }
}
