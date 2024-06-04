package vn.vietinbank.mobile.steps.iPay.UserAction.Login;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

import static vn.vietinbank.utils.Constants.*;
import static vn.vietinbank.utils.data.GetData.getDataFromFile;


public class LoginSteps extends InitObjects {

    public LoginSteps() {
        super();
    }

    @And("đăng nhập với mật khẩu {string}")
    public void dang_nhap_voi_mat_khau(String password) {
        password = getDataFromFile(PASSWORD, password);
        homeScreen = loginScreen.loginWithPassword(password);
    }

    @And("đăng nhập với tên đăng nhập {string} và mật khẩu {string}")
    public void dang_nhap_voi_ten_dang_nhap_va_mat_khau(String username, String password) {
        username = getDataFromFile(USERNAME, username);
        password = getDataFromFile(PASSWORD, password);
        otpScreen = loginScreen.loginWithUsernamePassword(username, password);
        homeScreen = otpScreen.inputOTP(OTP);
        homeScreen.closeAdsPopup();
    }

    @And("nhấn Đăng nhập bằng tài khoản khác")
    public void nhan_dang_nhap_bang_tai_khoan_khac() {
        loginScreen.clickLoginWithOtherAccountTxt();
    }
}
