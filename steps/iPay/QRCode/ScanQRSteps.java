package vn.vietinbank.mobile.steps.iPay.QRCode;

import io.cucumber.java.en.And;
import vn.vietinbank.mobile.screens.iPay.InitObjects;

public class ScanQRSteps extends InitObjects {

    public ScanQRSteps() {
        super();
    }

    @And("chọn ảnh thứ {string} trong album {string} đến MH Chuyển ngoài HT")
    public void chon_anh_thu_trong_album_den_mh_chuyen_ngoai_ht(String index, String albumName) {
        transferMoneyOutsideSystemScreen = scanQRScreen.chooseImageInAlbumByIndex(index, albumName);
    }

    @And("chọn ảnh thứ {string} trong album mặc định đến MH Chuyển trong HT")
    public void chon_anh_thu_trong_album_mac_dinh_den_mh_chuyen_ngoai_ht(String index) {
        transferMoneyInsideSystemScreen = scanQRScreen.chooseImageInAlbumDefaultByIndex(index);
    }
}
