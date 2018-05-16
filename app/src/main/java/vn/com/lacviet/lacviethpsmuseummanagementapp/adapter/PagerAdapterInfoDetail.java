package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabExpertise;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabGeneralInfo;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabHistory;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabLocation;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabPropertie;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.FragmentTabSupplier;

public class PagerAdapterInfoDetail extends FragmentStatePagerAdapter {

    public PagerAdapterInfoDetail(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= new FragmentTabGeneralInfo();
                break;
            case 1:
                frag= new FragmentTabExpertise();
                break;
            case 2:
                frag= new FragmentTabLocation();
                break;
            case 3:
                frag= new FragmentTabPropertie();
                break;
            case 4:
                frag= new FragmentTabSupplier();
                break;
            case 5:
                frag= new FragmentTabHistory();
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
                title = "CUNG CẤP";
                break;
            case 5:
                title = "LỊCH SỬ";
                break;
        }
        return title;
    }
}