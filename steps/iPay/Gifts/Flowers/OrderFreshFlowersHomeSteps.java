package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class OrderFreshFlowersHomeSteps extends InitObjects {

    public OrderFreshFlowersHomeSteps() {
        super();
    }

    @And("chọn nơi giao hoa {string}")
    public void chon_noi_giao_hoa(String city) {
        orderFreshFlowersHomeScreen.chooseCity(city);
    }

    @And("chọn chủ đề {string}")
    public void chon_chu_de(String topic) {
        orderFreshFlowersHomeScreen.chooseTopic(topic);
    }

    @And("chọn mức giá {string}")
    public void chon_muc_gia(String price) {
        orderFreshFlowersHomeScreen.choosePrice(price);
    }

    @And("chọn thương hiệu {string}")
    public void chon_thuong_hieu(String brand) {
        orderFreshFlowersHomeScreen.chooseBrand(brand);
    }

    @And("nhấn Tìm kiếm đến MH Kết quả tìm hoa tươi")
    public void nhan_tim_kiem_den_mh_ket_qua_tim_hoa_tuoi() {
        searchResultFreshFlowersScreen = orderFreshFlowersHomeScreen.clickSearchBtn();
    }

    @And("đến tab Giỏ hàng")
    public void den_tab_gio_hang() {
        flowerCartScreen = orderFreshFlowersHomeScreen.clickCartTab();
    }
}
