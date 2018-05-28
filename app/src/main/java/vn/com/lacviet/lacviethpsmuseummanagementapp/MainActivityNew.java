package vn.com.lacviet.lacviethpsmuseummanagementapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.CategogMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.IntroMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.LegislationMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.SearchMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.CustomArrayAdapter;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.MainscreenRecyclerViewAdapter;
import vn.com.lacviet.lacviethpsmuseummanagementapp.model.RecyclerViewExhibitModels;


public class MainActivityNew extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView tvTitleToolbar;
    //recyclerview
    private MainscreenRecyclerViewAdapter adapter_exhibit;
    private RecyclerView.LayoutManager layoutManager;
    private List<RecyclerViewExhibitModels> listExhibit;
    private int categoryPosition = 0;
    //menu
    private SpeedDialView speedDialView;
    //
    Spinner spinnerCategogy;
    List<String> rowItems;
    //menu
    private int arrImageViewId[] = {R.id.imvHomeMenu,R.id.imvIntroMenu,R.id.imvSearchMenu,R.id.imvCateMenu,R.id.imvLegisMenu,R.id.imvContactMenu,R.id.imvExitMenu};
    private ImageView arrImageView[] = new ImageView[arrImageViewId.length];



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        //set transparent stt bar
        StatusBarUtil.setTransparent(this);
        addControl();
        actionBar();
        addDataRecyclerView();
        showRecyclerView();
        addItemtoSpeedDialView();
        //addDataToSpinner();
        showIntroMenu();

    }

    private void showIntroMenu() {
        //show fragment
        IntroMenuFragment introMenuFragment = new IntroMenuFragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                .commit();
        //set color
        setDefaultIconMenu();
        arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));

    }


    private void addDataToSpinner() {
        String[] items = new String[] { "Tất cả","Giấy","Kim loại", "Sành sứ", "Đá", "Nhựa", "Khác" };

        rowItems = new ArrayList<String>();
        for (int i = 0; i < items.length; i++) {

            String item = new String(items[i]);
            rowItems.add(item);
        }

        spinnerCategogy = (Spinner)findViewById(R.id.snFilterCategory);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this,
                R.layout.item_list_spinner, R.id.tvListSpinner, rowItems);
        spinnerCategogy.setAdapter(adapter);




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
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter_exhibit = new MainscreenRecyclerViewAdapter(this, listExhibit);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_exhibit);
    }

    private void addDataRecyclerView() {
        listExhibit = new ArrayList<>();
        listExhibit.add(new RecyclerViewExhibitModels(1, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_trong_nhac));
        listExhibit.add(new RecyclerViewExhibitModels(2, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_binhtra));
        listExhibit.add(new RecyclerViewExhibitModels(3, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_chen));
        listExhibit.add(new RecyclerViewExhibitModels(4, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_dia));
        listExhibit.add(new RecyclerViewExhibitModels(5, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_binhtra));
        listExhibit.add(new RecyclerViewExhibitModels(6, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_trong_nhac));
        listExhibit.add(new RecyclerViewExhibitModels(7, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_binh));
        listExhibit.add(new RecyclerViewExhibitModels(8, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_trong_nhac));
        listExhibit.add(new RecyclerViewExhibitModels(9, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_trong_nhac));
        listExhibit.add(new RecyclerViewExhibitModels(10, "Trống nhạc", "Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng", R.drawable.img_trong_nhac));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:

                return true;
            case R.id.menu_user:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        tvTitleToolbar.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarMainscreen);
        recyclerView = findViewById(R.id.rcvMainScreen);
        navigationView = findViewById(R.id.ngvMainscreen);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        speedDialView = findViewById(R.id.fabMainscreen);
        spinnerCategogy = findViewById(R.id.snFilterCategory);
        //Menu
        int index = 0;
        for(index = 0;index<arrImageView.length;index++){
            arrImageView[index] = findViewById(arrImageViewId[index]);
            arrImageView[index].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imvHomeMenu:{

                break;
            }
            case R.id.imvIntroMenu:{
                //show fragment
                IntroMenuFragment introMenuFragment = new IntroMenuFragment();
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));
                break;
            }
            case R.id.imvSearchMenu:{
                //show fragment
                SearchMenuFragment searchMenuFragment = new SearchMenuFragment();
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, searchMenuFragment, searchMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_yellow));
                break;
            }
            case R.id.imvCateMenu:{
                //show fragment
                CategogMenuFragment cateMenuFragment = new CategogMenuFragment();
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, cateMenuFragment, cateMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_yellow));
                break;
            }
            case R.id.imvLegisMenu:{
                //show fragment
                LegislationMenuFragment legisMenuFragment = new LegislationMenuFragment();
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, legisMenuFragment, legisMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_yellow));
                break;
            }
            case R.id.imvContactMenu:{

            }
            case R.id.imvExitMenu:{

                break;
            }
            default:break;
        }

    }
    private void setDefaultIconMenu(){
        int index =0;
        for(index =0;index<arrImageView.length;index++){
            switch (index){
                case 0:{
                    break;
                }
                case 1:{
                    arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_circle));
                }
                case 2:{
                    arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_circle));
                }
                case 3:{
                    arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_circle));
                }
                case 4:{
                    arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_circle));
                }
                case 5:{
                    arrImageView[5].setImageDrawable(getResources().getDrawable(R.drawable.ic_contact_circle));
                }
                case 6:{

                }
            }
        }
    }

}
