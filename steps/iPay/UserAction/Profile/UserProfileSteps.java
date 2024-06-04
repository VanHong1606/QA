package vn.vietinbank.mobile.steps.iPay.UserAction.Profile;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class UserProfileSteps extends InitObjects {

    public UserProfileSteps() {
        super();
    }

    @And("nhấn Đăng xuất")
    public void nhan_dang_xuat() {
        homeScreen = userProfileScreen.clickBtnDangXuat();
    }
}
