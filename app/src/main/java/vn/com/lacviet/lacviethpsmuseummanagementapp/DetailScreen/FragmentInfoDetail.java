package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.PagerAdapterInfoDetail;

public class FragmentInfoDetail extends Fragment {

    private ViewPager pager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_info_detail, container, false);

        // Set up the ViewPager with the sections adapter.
        pager = view.findViewById(R.id.container);
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutDetail);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        PagerAdapterInfoDetail adapter = new PagerAdapterInfoDetail(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        /*pager.setAdapter(mSectionsPagerAdapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));*/
        return view;

    }






}
