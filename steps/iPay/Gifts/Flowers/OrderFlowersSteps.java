package vn.vietinbank.mobile.steps.iPay.Gifts.Flowers;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class OrderFlowersSteps extends InitObjects {

    public OrderFlowersSteps() {
        super();
    }

    @And("chọn {string} giấu tên người gửi")
    public void chon_giau_ten_nguoi_gui(String answer) {
        orderFlowersScreen.clickHideSenderNameCheckbox(answer);
    }

    @And("nhập họ tên người gửi {string}")
    public void nhap_ho_ten_nguoi_gui(String fullName) {
        orderFlowersScreen.inputFullName(fullName);
    }

    @And("nhập SĐT người gửi {string}")
    public void nhap_sdt_nguoi_gui(String mobilePhone) {
        orderFlowersScreen.inputMobilePhone(mobilePhone);
    }

    @And("nhập email người gửi {string}")
    public void nhap_email_nguoi_gui(String email) {
        orderFlowersScreen.inputEmail(email);
    }

    @And("chọn {string} yêu cầu xuất hoá đơn")
    public void chon_yeu_cau_xuat_hoa_don(String answer) {
        orderFlowersScreen.clickRequestInvoiceCheckbox(answer);
    }

    @And("nhập Tên công ty nhận hoá đơn {string}")
    public void nhap_ten_cong_ty_nhan_hoa_don(String companyName) {
        orderFlowersScreen.inputCompanyName(companyName);
    }

    @And("nhập MST công ty nhận hoá đơn {string}")
    public void nhap_mst_cong_ty_nhan_hoa_don(String taxCode) {
        orderFlowersScreen.inputTaxCode(taxCode);
    }

    @And("nhập Địa chỉ công ty nhận hoá đơn {string}")
    public void nhap_dia_chi_cong_ty_nhan_hoa_don(String addressCompany) {
        orderFlowersScreen.inputCompanyAddress(addressCompany);
    }

    @And("nhập Email công ty nhận hoá đơn {string}")
    public void nhap_email_cong_ty_nhan_hoa_don(String emailToReceiveInvoice) {
        orderFlowersScreen.inputEmailToReceiveInvoice(emailToReceiveInvoice);
    }

    @And("nhấn Nhập để trở lại MH")
    public void nhan_nhap_de_tro_lai_mh() {
        orderFlowersScreen.clickReturnBtn();
    }

    @And("nhấn Đặt mua hoa đến MH Xác nhận thanh toán")
    public void nhan_dat_mua_hoa_den_mh_xac_nhan_thanh_toan() {
        confirmPayOrderFlowerScreen = orderFlowersScreen.clickOrderFlowerBtn();
    }

    @And("chọn ngày nhận hoa ngẫu nhiên")
    public void chon_ngay_nhan_hoa_ngau_nhien() {
        dateReceiveFlowersScreen = orderFlowersScreen.clickDateReceiveFlowers();
        orderFlowersScreen = dateReceiveFlowersScreen.tapRandomDate();
    }
}
