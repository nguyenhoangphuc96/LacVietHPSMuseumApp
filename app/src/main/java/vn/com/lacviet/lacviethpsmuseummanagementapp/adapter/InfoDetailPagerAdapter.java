package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabExpertiseFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabGeneralInfoFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabHistoryFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabLocationFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabPropertieFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabSupplierFragment;

public class InfoDetailPagerAdapter extends FragmentStatePagerAdapter {

    public InfoDetailPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= new TabGeneralInfoFragment();
                break;
            case 1:
                frag= new TabExpertiseFragment();
                break;
            case 2:
                frag= new TabLocationFragment();
                break;
            case 3:
                frag= new TabPropertieFragment();
                break;
            case 4:
                frag= new TabSupplierFragment();
                break;
            case 5:
                frag= new TabHistoryFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 6;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "THÔNG TIN CHUNG";
                break;
            case 1:
                title = "THẨM ĐỊNH";
                break;
            case 2:
                title = "LƯU GIỮ";
                break;
            case 3:
                title = "THUỘC TÍNH";
                break;
            case 4:
                title = "NGUỒN CUNG CẤP";
                break;
            case 5:
                title = "LỊCH SỬ";
                break;
        }
        return title;
    }
}