package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.InfoDetailPagerAdapter;

public class InfoDetailFragment extends Fragment {


    private ViewPager pager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_info_detail, container, false);
        addControls(view);
        showDataToView();


        return view;

    }

    private void showDataToView() {
        // Set up the ViewPager with the sections adapter.
        FragmentManager manager = getActivity().getSupportFragmentManager();
        InfoDetailPagerAdapter adapter = new InfoDetailPagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);}

    private void addControls(View view) {
        pager = view.findViewById(R.id.container);
        tabLayout = view.findViewById(R.id.tabLayoutDetail);
    }


}
