package vn.vietinbank.mobile.steps.iPay.HomeScreen;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import vn.vietinbank.mobile.screens.iPay.HomeScreen.HomeScreen;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class HomeSteps extends InitObjects {

    public HomeSteps() {
        super();
    }

    AppiumDriver appiumDriver = Serenity.sessionVariableCalled("appiumDriver");

    @And("nhấn Đăng nhập khi vừa mở app")
    public void nhan_dang_nhap_khi_vua_mo_app() {
        homeScreen = new HomeScreen(appiumDriver);
        loginScreen = homeScreen.clickLoginBtn();
    }

    @And("nhấn Đăng nhập trên MH Home")
    public void nhan_dang_nhap_tren_mh_home() {
        loginScreen = homeScreen.clickLoginBtn();
    }

    @And("đến Danh sách tài khoản")
    public void den_danh_sach_tai_khoan() {
        listAccountsScreen = homeScreen.accountAndCardsSideBar().clickListAccountsTxt();
    }

    @And("đến Quét mã QR")
    public void den_quet_ma_qr() {
        scanQRScreen = homeScreen.favoriteServicesSection().clickScanQR();
    }

    @And("chọn icon tìm kiếm đến MH Tìm kiếm")
    public void chon_icon_tim_kiem_den_mh_tim_kiem() {
        searchFeatureScreen = homeScreen.clickIconSearchFeature();
    }

    @And("đến Tặng hoa tươi")
    public void den_tang_hoa_tuoi() {
        orderFreshFlowersHomeScreen = homeScreen.shoppingAndEntertainmentSection().clickGiveFreshFlowers();
    }

    @And("vào tab user")
    public void vao_tab_user() {
        userProfileScreen = homeScreen.bottomTabs().clickUserProfileTab();
    }
}
