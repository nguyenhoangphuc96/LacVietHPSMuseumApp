package vn.com.lacviet.lacviethpsmuseummanagementapp.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.ExhibitCategoryRecyclerViewAdapter;
import vn.com.lacviet.lacviethpsmuseummanagementapp.model.ExhibitModels;

public class ExhibitOfCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExhibitCategoryRecyclerViewAdapter adapterCategory;
    private RecyclerView.LayoutManager layoutManager;
    private List<ExhibitModels> listExhibit;
    private SpeedDialView speedDialView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit_of_caterory);
        addControls();
        addItemtoSpeedDialView();
        addData();
        showRecyclerView();
    }

    private void addItemtoSpeedDialView() {
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_contact, R.drawable.ic_contact)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Thông tin liên hệ")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_legislation, R.drawable.ic_legislation)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Văn bản pháp luật")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_category, R.drawable.ic_category)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Danh mục hiện vật")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_search, R.drawable.ic_search_white)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Tra cứu")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_intro, R.drawable.ic_intro)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Giới thiệu")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_home, R.drawable.ic_home)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorBlueLight, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabel("Trang chủ")
                        .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()))
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.transparentBlack, getTheme()))
                        .setLabelClickable(true)
                        .create()
        );
    }

    private void showRecyclerView() {
        adapterCategory = new ExhibitCategoryRecyclerViewAdapter(this,listExhibit);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCategory);
    }

    private void addData() {
        listExhibit = new ArrayList<>();
        listExhibit.add(new ExhibitModels(1,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(2,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(3,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(4,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(5,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(6,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(7,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(8,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
    }

    private void addControls() {
        recyclerView=findViewById(R.id.rcvShowExhibitOfCategory);
        speedDialView = findViewById(R.id.fabCategory);
    }
}