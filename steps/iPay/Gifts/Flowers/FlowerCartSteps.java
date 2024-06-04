package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class FlowerCartSteps extends InitObjects {

    public FlowerCartSteps() {
        super();
    }

    @And("tích ô Tất cả trên MH Giỏ hàng hoa tươi")
    public void tich_o_tat_ca_tren_mh_gio_hang_hoa_tuoi() {
        flowerCartScreen.clickAllTxt();
    }

    @And("nhấn Đặt mua hoa đến MH Đặt mua hoa")
    public void nhan_dat_mua_hoa_den_mh_dat_mua_hoa() {
        orderFlowersScreen = flowerCartScreen.clickOrderFlowerBtn();
    }

    @And("xoá giỏ hàng")
    public void xoa_gio_hang() {
        flowerCartScreen.removeCart();
    }

    @And("trở về MH Tặng hoa tươi")
    public void tro_ve_mh_tang_hoa_tuoi() {
        orderFreshFlowersHomeScreen = flowerCartScreen.clickBackBtn();
    }
}
