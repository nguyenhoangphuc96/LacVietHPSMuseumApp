package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabExpertiseFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabGeneralInfoFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabHistoryFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabLocationFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabPropertieFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.TabSupplierFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class InfoDetailPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;

    public InfoDetailPagerAdapter(FragmentManager fragmentManager,Context context) {
        super(fragmentManager);
        this.context = context;
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
                title = "Thông tin chung";
                break;
            case 1:
                title = "Thẩm định";
                break;
            case 2:
                title = "Lưu giữ";
                break;
            case 3:
                title = "Thuộc tính";
                break;
            case 4:
                title = "Nguồn cung cấp";
                break;
            case 5:
                title = "Lịch sử";
                break;
        }
        return title;
    }


}