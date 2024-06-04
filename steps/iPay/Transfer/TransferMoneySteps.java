package vn.vietinbank.mobile.steps.iPay.Transfer;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class TransferMoneySteps extends InitObjects {

    public TransferMoneySteps() {
        super();
    }

    @And("nhập STK muốn chuyển tiền ngoài HT {string}")
    public void nhap_stk_muon_chuyen_tien_ngoai_ht(String accountNumber) {
        transferMoneyScreen.inputAccountNumber(accountNumber);
    }

    @And("nhập STK muốn chuyển tiền trong HT {string}")
    public void nhap_stk_muon_chuyen_tien_trong_ht(String accountNumber) {
        transferMoneyScreen.inputAccountNumber(accountNumber);
    }

    @And("chọn NH để chuyển ngoài HT {string}")
    public void chon_nh_de_chuyen_tien_ngoai_ht(String bankName) {
        transferMoneyScreen.chooseBank(bankName);
    }

    @And("đến MH Chuyển ngoài HT sau khi chọn người thụ hưởng {string}")
    public void den_mh_chuyen_ngoai_ht_sau_khi_chon_nguoi_thu_huong(String beneficiary) {
        transferMoneyOutsideSystemScreen = transferMoneyScreen.chooseInListOfBeneficiariesOutside(beneficiary);
    }

    @And("đến MH Chuyển trong HT sau khi chọn người thụ hưởng {string}")
    public void den_mh_chuyen_trong_ht_sau_khi_chon_nguoi_thu_huong(String beneficiary) {
        transferMoneyInsideSystemScreen = transferMoneyScreen.chooseInListOfBeneficiariesInside(beneficiary);
    }

    @And("chọn tab Tới số thẻ")
    public void chọn_tab_toi_so_the() {
        transferMoneyScreen.clickToCardCodeTab();
    }

    @And("nhập số thẻ {string}")
    public void nhap_so_the(String cardCode) {
        transferMoneyScreen.cardCodeInput(cardCode);
    }

    @And("chọn Xong trên bàn phím")
    public void chon_xong_tren_ban_phim() {
        transferMoneyScreen.clickDoneBtnOnKeyBoard();
    }
}
