package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class DetailInfoFlowerSteps extends InitObjects {

    public DetailInfoFlowerSteps() {
        super();
    }

    @And("{string} số lượng {string} bó hoa")
    public void so_luong_hoa(String type, String volume) {
        detailInfoFlowerScreen.changeVolume(type, volume);
    }

    @And("nhấn Thêm vào giỏ đến MH Giỏ hàng hoa tươi")
    public void nhan_them_vao_gio_hang_den_mh_gio_hang_hoa_tuoi() {
        flowerCartScreen = detailInfoFlowerScreen.clickAddToCart();
    }
}
