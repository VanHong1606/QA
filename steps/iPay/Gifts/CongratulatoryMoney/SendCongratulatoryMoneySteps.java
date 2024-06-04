package vn.vietinbank.mobile.steps.iPay.Gifts.CongratulatoryMoney;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class SendCongratulatoryMoneySteps extends InitObjects {

    public SendCongratulatoryMoneySteps() {
        super();
    }

    @And("thêm mới người nhận {string}")
    public void them_moi_nguoi_nhan(String receiver) {
        sendCongratulatoryMoneyScreen.addNewReceiver(receiver);
    }

    @And("nhập số tiền muốn gửi cho từng người {string}")
    public void nhap_so_tien_muon_gui_cho_tung_nguoi(String amount) {
        sendCongratulatoryMoneyScreen.inputAmountToSend(amount);
    }

    @And("nhập lời chúc muốn gửi {string}")
    public void nhap_loi_chuc_muon_gui(String wishes) {
        sendCongratulatoryMoneyScreen.inputWishesToSend(wishes);
    }

    @And("nhấn Tiếp tục đến MH Xác nhận GD")
    public void nhan_tiep_tuc_den_mh_xac_nhan_gd() {
        confirmTransactionScreen = sendCongratulatoryMoneyScreen.clickContinueBtn();
    }
}
