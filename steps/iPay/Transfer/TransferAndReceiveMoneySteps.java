package vn.vietinbank.mobile.steps.iPay.Transfer;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransferAndReceiveMoneySteps extends InitObjects {

    public TransferAndReceiveMoneySteps() {
        super();
    }

    @And("hiển thị MH Chuyển trong & ngoài HT")
    public void hien_thi_mh_chuyen_trong_va_ngoai_ht() {
        assertThat(transferAndReceiveMoneyScreen.displayTransferAndReceiveMoneyTitle(), equalTo(true));
    }
}
