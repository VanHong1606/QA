package vn.vietinbank.mobile.steps.iPay.Transfer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransferMoneyInsideSystemSteps extends InitObjects {

    public TransferMoneyInsideSystemSteps() {
        super();
    }

    @Then("hiển thị tên người nhận {string} trên MH Chuyển trong HT")
    public void hien_thi_ten_nguoi_nhan_tren_mh_chuyen_trong_ht(String receiverName) {
        assertThat(transferMoneyInsideSystemScreen.displayReceiverName(receiverName), equalTo(true));
    }

    @And("nhập số tiền {string} chuyển trong HT")
    public void nhap_so_tien_de_chuyen_trong_ht(String amount) {
        transferMoneyInsideSystemScreen.inputAmount(amount);
    }

    @And("nhấn Tiếp tục trên MH Chuyển trong HT sau khi nhập số tiền đến MH Xác nhận GD")
    public void nhan_tiep_tuc_tren_mh_chuyen_trong_ht_sau_khi_nhap_so_tien_den_mh_xac_nhan_gd() {
        confirmTransactionScreen = transferMoneyInsideSystemScreen.clickContinueBtnAfterInputAmount();
    }

    @And("nhấn Tiếp tục trên MH Chuyển trong HT")
    public void nhan_tiep_tuc_tren_mh_chuyen_trong_ht() {
        confirmTransactionScreen = transferMoneyInsideSystemScreen.clickContinueBtn();
    }

    @And("chọn thời gian chuyển {string}")
    public void chon_thoi_gian_chuyen(String transferTime) {
        transferMoneyInsideSystemScreen.chooseTransferTimeByText(transferTime);
    }

    @And("chọn tần suất {string}")
    public void chon_tan_suat(String transferFrequency) {
        transferMoneyInsideSystemScreen.chooseTransferFrequencyByText(transferFrequency);
    }

    @And("ẩn bàn phím trên MH Chuyển trong HT")
    public void an_ban_phim_tren_mh_chuyen_trong_ht() {
        transferMoneyInsideSystemScreen.clickHideKeyboardBtn();
    }

    @And("bỏ chọn Tham gia bảo hiểm an ninh mạng trên MH Chuyển trong HT")
    public void bo_chon_tham_gia_bao_hiem_an_ninh_mang_tren_mh_chuyen_trong_ht() {
        transferMoneyInsideSystemScreen.uncheckInsurance();
    }
}
