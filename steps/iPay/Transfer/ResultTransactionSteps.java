package vn.vietinbank.mobile.steps.iPay.Transfer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static vn.vietinbank.utils.converter.Converter.format;

public class ResultTransactionSteps extends InitObjects {

    public ResultTransactionSteps() {
        super();
    }

    @Then("hiển thị Chuyển tiền thành công với STK {string} NH {string} số tiền {string}")
    public void hien_thi_chuyen_tien_thanh_cong_voi_stk_nh_so_tien(String accountNumber, String bankName, String amount) {
        assertThat(resultTransactionScreen.verifyResultTransactionLabel(), equalTo(true));
        assertThat(resultTransactionScreen.getAccountNumber(), containsString(accountNumber));
        assertThat(resultTransactionScreen.getBankName(), containsString(bankName));
        String amountStr = format(amount);
        assertThat(resultTransactionScreen.getAmount(), containsString(amountStr));
    }

    @Then("hiển thị Chuyển tiền thành công với số thẻ {string} NH {string} số tiền {string}")
    public void hien_thi_chuyen_tien_thanh_cong_voi_so_the_nh_so_tien(String getCardCode, String bankName, String amount) {
        assertThat(resultTransactionScreen.verifyResultTransactionLabel(), equalTo(true));
        assertThat(resultTransactionScreen.getCardCode(), containsString(getCardCode));
        assertThat(resultTransactionScreen.getBankName(), containsString(bankName));
        String amountStr = format(amount);
        assertThat(resultTransactionScreen.getAmount(), containsString(amountStr));
    }

    @Then("hiển thị thông báo gửi tiền mừng hoàn tất")
    public void hien_thi_thong_bao_gui_tien_mung_hoan_tat() {
        assertThat(resultTransactionScreen.verifyCongratulatoryDepositHasBeenCompleted(), equalTo(true));
    }

    @And("quay lại home từ MH Kết quả GD")
    public void quay_lai_home_tu_mh_ket_qua_gd() {
        homeScreen = resultTransactionScreen.backToHome();
    }
}
