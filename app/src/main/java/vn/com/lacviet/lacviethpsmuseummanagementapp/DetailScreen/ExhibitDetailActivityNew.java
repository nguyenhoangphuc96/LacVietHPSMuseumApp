package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.InfoDetailPagerAdapter;

public class ExhibitDetailActivityNew extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvTitleToolbar;
    private ViewPager pager;
    private TabLayout tabLayout;
    private TextView tvSameExhibit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail_new);
        addControl();
        actionBar();
        showDataToView();
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarExhibitDetail);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        pager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabLayoutDetail);
        tvSameExhibit = findViewById(R.id.tvSameExhibit);
    }
    private void actionBar() {
        setSupportActionBar(toolbar);
        tvTitleToolbar.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);


    }
    private void showDataToView() {
        // Set up the ViewPager with the sections adapter.
        FragmentManager manager = getSupportFragmentManager();
        InfoDetailPagerAdapter adapter = new InfoDetailPagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);}
}
