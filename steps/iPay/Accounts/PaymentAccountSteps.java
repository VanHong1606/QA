package vn.vietinbank.mobile.steps.iPay.Accounts;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class PaymentAccountSteps extends InitObjects {

    public PaymentAccountSteps() {
        super();
    }

    @And("đến MH Chuyển trong & ngoài HT")
    public void den_mh_chuyen_trong_va_ngoai_ht() {
        transferMoneyScreen = paymentAccountScreen.moveToTransferMoneyInsideAndOutsideSystemScreen();
    }
}
