package vn.vietinbank.mobile.steps.iPay.Accounts;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static vn.vietinbank.utils.data.GetData.getDataFromFile;

public class AccountSteps extends InitObjects {

    public AccountSteps() {
        super();
    }

    @And("chuyển bằng {string} {string}")
    public void chuyen_bang(String key, String value) {
        value = getDataFromFile(key, value);
        paymentAccountScreen = listAccountsScreen.moveToAccountNumber(value);
    }
}
