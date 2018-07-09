package vn.com.lacviet.lacviethpsmuseummanagementapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ContactScreen.ContactActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.LoadMore.EndlessRecyclerViewScrollListener;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.CategogMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.IntroMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.LegislationMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.SearchMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen.NomalSearchActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllExhibitJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllResultJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllStuffJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenWithImageModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.CategoryNameDialogRCVAdapter;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.ExhibitMainscreenRecyclerViewAdapter;


public class MainActivityNew extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView tvTitleToolbar, txtTitleCategory, tvAllCategory;


    //menu
    private int arrImageViewId[] = {R.id.imvHomeMenu, R.id.imvIntroMenu, R.id.imvSearchMenu, R.id.imvCateMenu, R.id.imvLegisMenu, R.id.imvContactMenu, R.id.imvConfig, R.id.imvExitMenu};
    private ImageView arrImageView[] = new ImageView[arrImageViewId.length];
    //Dialog

    AlertDialog alertDialog;
    RecyclerView rcvCategogyName;
    RelativeLayout loToolBar, rlTitleCategory;
    ProgressBar pbDialog;
    //RecyclerView api
    private ExhibitMainscreenRecyclerViewAdapter mAdapter;
    private ApiService mService;
    private CategoryNameDialogRCVAdapter dialogAdapter;
    //ProgressDialog
    private ProgressBar pbMainScreen;
    //load more
    private int indexPage = 1;
    private int size = 20;
    private ArrayList<ExhibitMainScreenModel> mainScreenModelArrayList;

    private EndlessRecyclerViewScrollListener scrollListener;
    int totalItem = 20;
    int totalMaxSize;
    //
    ArrayList<ExhibitMainScreenWithImageModel> ListExhibit;
    //filter
    String stuffId;
    Boolean isFilter = false;
    String stuffName;
    //
    Boolean isCategoryLoaded = false;
    List<StuffModel> ListStuff;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        //set transparent stt bar
        /*StatusBarUtil.setTransparent(this);
        StatusBarUtil.setTransparentForDrawerLayout(this,drawerLayout);*/
        /*getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );*/
        addControl();
        actionBar();
        showDataToRecyclerView();
        loadAnswers(indexPage, size);
        showIntroMenu();
        setPositionTextViewTittleCategogy();
        addEvent();

    }


    public void showErrorMessage() {
        Toast.makeText(MainActivityNew.this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }


    private void loadAnswers(int indexPage, int size) {
    /*   new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        mService.getExhibitByPage(indexPage, size).enqueue(new Callback<AllExhibitJsonResponse>() {
            @Override
            public void onResponse(Call<AllExhibitJsonResponse> call, Response<AllExhibitJsonResponse> response) {

                if (response.isSuccessful()) {
                    pbMainScreen.setVisibility(View.GONE);
                    totalMaxSize = response.body().getTotal();
                    ListExhibit = new ArrayList<>();

                    ArrayList<ExhibitMainScreenModel> list = (ArrayList<ExhibitMainScreenModel>) response.body().getExhibitModels();

                    for (ExhibitMainScreenModel em : list) {
                        ListExhibit.add(new ExhibitMainScreenWithImageModel(em.getEXHID(), em.getEXHIBITNAME(), em.getDESCRIPTION(), null));
                    }

                    mAdapter.updateMoreAnswers(ListExhibit);
                    //mAdapter.updateAnswers(response.body().getExhibitModels());

                    for (ExhibitMainScreenWithImageModel tm : mAdapter.getTestList()) {
                        loadImage(tm);
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllExhibitJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    private void loadImage(ExhibitMainScreenWithImageModel mod) {
     /*   //get image default
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).run();*/

        mService.getExhibitImageById(mod.geteXHID()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    try {
                        int index = mAdapter.getTestList().indexOf(mod);
                        mAdapter.getTestList().get(index).seteIMAGE(response.body());

                        mAdapter.notifyItemChanged(index);
                        Log.d("AnswersPresenter", "Image loaded!!!!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading image!!!");

            }
        });
    }

    private void showDataToRecyclerView() {
        mAdapter = new ExhibitMainscreenRecyclerViewAdapter(this, new ArrayList<ExhibitMainScreenWithImageModel>(0), new ExhibitMainscreenRecyclerViewAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                startDetailActivity((int) id);

            }
        });
        /*RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(layoutManager);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (isFilter) {
                    showMoreResultByStuffId(page, size, stuffId);
                } else {
                    loadMoreAnswers(page, size);

                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }


    private void loadMoreAnswers(int i, int size) {
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(th, ""+i, Toast.LENGTH_SHORT).show();


            }
        }).run();*/

        mService.getExhibitByPage(i + 1, size).enqueue(new Callback<AllExhibitJsonResponse>() {
            @Override
            public void onResponse(Call<AllExhibitJsonResponse> call, Response<AllExhibitJsonResponse> response) {

                if (response.isSuccessful()) {
                    //mAdapter.updateMoreAnswers(response.body().getExhibitModels());
                    ListExhibit = new ArrayList<>();
                    ArrayList<ExhibitMainScreenModel> list = (ArrayList<ExhibitMainScreenModel>) response.body().getExhibitModels();

                    for (ExhibitMainScreenModel em : list) {
                        ListExhibit.add(new ExhibitMainScreenWithImageModel(em.getEXHID(), em.getEXHIBITNAME(), em.getDESCRIPTION(), null));
                    }

                    mAdapter.updateMoreAnswers(ListExhibit);
                    //mAdapter.updateAnswers(response.body().getExhibitModels());


                    int start = totalItem;
                    int end = totalItem + size;
                    totalItem += size;
                    if (totalItem > totalMaxSize) {
                        totalItem = totalMaxSize;
                        end = totalMaxSize;
                    }

                    for (int i = start; i < end; i++) {
                        loadImage(mAdapter.getTestList().get(i));
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllExhibitJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });

    }


    private void startDetailActivity(int id) {
        Intent intent = new Intent(this, ExhibitDetailActivityNew.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, id);
        startActivity(intent);
    }

    private void setPositionTextViewTittleCategogy() {

        loToolBar = findViewById(R.id.loToolBar);
        rlTitleCategory = findViewById(R.id.rlTitleCategory);
        ViewTreeObserver vto = toolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = loToolBar.getMeasuredWidth();
                int height = loToolBar.getMeasuredHeight();
                int tvWidth = txtTitleCategory.getMeasuredWidth();
                int tvHeight = txtTitleCategory.getMeasuredHeight();
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins((width / 2) - (tvWidth / 2), height - (tvHeight / 2) + 4, 0, 0);
                rlTitleCategory.setLayoutParams(lp);
            }
        });
    }

    private void addEvent() {
        txtTitleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewDialog();
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                showView(txtTitleCategory);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        /*//All filter
        tvAllCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitleCategory.setText("Tất cả");
                loadAnswers(indexPage,size);
            }
        });*/

    }

    private void showViewDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_categogy, null);
        rcvCategogyName = view.findViewById(R.id.rcvCategoryName);

        dialogAdapter = new CategoryNameDialogRCVAdapter(this, new ArrayList<StuffModel>(0), new CategoryNameDialogRCVAdapter.PostItemListener() {


            @Override
            public void onPostClick(String id) {
                alertDialog.hide();
                isFilter = true;
                totalItem = 20;
                totalMaxSize = 0;
                indexPage = 1;
                size = 20;
                showExhibitByStuffId(indexPage, size, id);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCategogyName.setLayoutManager(layoutManager);
        rcvCategogyName.setAdapter(dialogAdapter);
        rcvCategogyName.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        rcvCategogyName.addItemDecoration(dividerItemDecoration);
        loadAnswersDialog();
        alertDialog = new AlertDialog.Builder(this, R.style.CustomDialog)
                .setView(view)
                .create();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        alertDialog.setCancelable(true);
        alertDialog.show();


    }

    private void showExhibitByStuffId(int i, int s, String id) {
        stuffId = id;
        //Get stuff name


        mService.getStuffById(stuffId).enqueue(new Callback<StuffModel>() {
            @Override
            public void onResponse(Call<StuffModel> call, Response<StuffModel> response) {
                if (response.isSuccessful()) {
                    stuffName = response.body().getSTUFFNAME();
                    txtTitleCategory.setText(stuffName);
                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StuffModel> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");
            }
        });
        //When choose all stuff
        if(stuffId.equals("0"))
        {
            Intent intent = new Intent(MainActivityNew.this, MainActivityNew.class);
            startActivity(intent);
        }
        //
        mService.getResultByStuff(i, s, stuffId).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
                    pbMainScreen.setVisibility(View.GONE);

                    ListExhibit = new ArrayList<>();

                    ArrayList<ExhibitMainScreenModel> list = (ArrayList<ExhibitMainScreenModel>) response.body().getExhibitMainScreenModels();

                    for (ExhibitMainScreenModel em : list) {
                        ListExhibit.add(new ExhibitMainScreenWithImageModel(em.getEXHID(), em.getEXHIBITNAME(), em.getDESCRIPTION(), null));
                    }

                    mAdapter.updateAnswers(ListExhibit);
                    //mAdapter.updateAnswers(response.body().getExhibitModels());

                    for (ExhibitMainScreenWithImageModel tm : mAdapter.getTestList()) {
                        loadImage(tm);
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllResultJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    private void showMoreResultByStuffId(int page, int size, String stuffId) {
        mService.getResultByStuff(page + 1, size, stuffId).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
                    totalMaxSize = response.body().getTotal();
                    //mAdapter.updateMoreAnswers(response.body().getExhibitModels());
                    ListExhibit = new ArrayList<>();
                    ArrayList<ExhibitMainScreenModel> list = (ArrayList<ExhibitMainScreenModel>) response.body().getExhibitMainScreenModels();

                    for (ExhibitMainScreenModel em : list) {
                        ListExhibit.add(new ExhibitMainScreenWithImageModel(em.getEXHID(), em.getEXHIBITNAME(), em.getDESCRIPTION(), null));
                    }

                    mAdapter.updateMoreAnswers(ListExhibit);
                    //mAdapter.updateAnswers(response.body().getExhibitModels());


                    int start = totalItem;
                    int end = totalItem + size;
                    totalItem += size;
                    if (totalItem > totalMaxSize) {
                        totalItem = totalMaxSize;
                        end = totalMaxSize;
                    }

                    for (int i = start; i < end; i++) {
                        loadImage(mAdapter.getTestList().get(i));
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllResultJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    private void loadAnswersDialog() {
        if(isCategoryLoaded)
        {
            dialogAdapter.updateAnswers(ListStuff);
        }
        else {
            mService.getAllStuffs().enqueue(new Callback<AllStuffJsonResponse>() {
                @Override
                public void onResponse(Call<AllStuffJsonResponse> call, Response<AllStuffJsonResponse> response) {

                    if (response.isSuccessful()) {
                        dialogAdapter.updateAnswers(response.body().getStuffModels());
                        ListStuff = response.body().getStuffModels();
                        isCategoryLoaded = true;

                        Log.d("AnswersPresenter", "posts loaded from API");
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(MainActivityNew.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AllStuffJsonResponse> call, Throwable t) {
                    showErrorMessage();
                    Log.d("AnswersPresenter", "error loading from API");

                }
            });
        }

    }

    private void showIntroMenu() {
        //show fragment
        IntroMenuFragment introMenuFragment = new IntroMenuFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                .commit();
        //set color
        setDefaultIconMenu();
        arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));

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
                startNormalSearchActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void startNormalSearchActivity() {
        Intent intent = new Intent(MainActivityNew.this, NomalSearchActivity.class);
        startActivity(intent);
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
                //hide text view categogy
                hideView(txtTitleCategory);

            }
        });


    }

    public void hideView(View view) {


        view.setVisibility(View.INVISIBLE);


    }

    public void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarMainscreen);
        recyclerView = findViewById(R.id.rcvMainScreen);
        navigationView = findViewById(R.id.ngvMainscreen);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        //Menu
        int index = 0;
        for (index = 0; index < arrImageView.length; index++) {
            arrImageView[index] = findViewById(arrImageViewId[index]);
            arrImageView[index].setOnClickListener(this);
        }
        txtTitleCategory = findViewById(R.id.txtTitleCategory);
        //api
        mService = ApiUtils.getSOService();
        //
        pbMainScreen = findViewById(R.id.pbMainScreen);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imvHomeMenu: {
                Intent intent = new Intent(MainActivityNew.this, MainActivityNew.class);
                startActivity(intent);

                break;
            }
            case R.id.imvIntroMenu: {
                //show fragment
                IntroMenuFragment introMenuFragment = new IntroMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));
                break;
            }
            case R.id.imvSearchMenu: {
                //show fragment
                SearchMenuFragment searchMenuFragment = new SearchMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, searchMenuFragment, searchMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_yellow));
                break;
            }
            case R.id.imvCateMenu: {
                //show fragment
                CategogMenuFragment cateMenuFragment = new CategogMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, cateMenuFragment, cateMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_yellow));
                break;
            }
            case R.id.imvLegisMenu: {
                //show fragment
                LegislationMenuFragment legisMenuFragment = new LegislationMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, legisMenuFragment, legisMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_yellow));
                break;
            }
            case R.id.imvContactMenu: {
                startContactActivity();
                break;

            }
            case R.id.imvConfig: {
                startConfigActivity();
                break;

            }
            case R.id.imvExitMenu: {
                onBackPressed();

                break;
            }
            default:
                break;
        }

    }

    private void startConfigActivity() {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
        finish();
    }

    private void startContactActivity() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Đồng ý thoát khỏi ứng dụng?");
        alertDialogBuilder
                .setMessage("Chọn có để thoát")
                .setCancelable(false)
                .setPositiveButton("Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setDefaultIconMenu() {
        int index = 0;
        for (index = 0; index < arrImageView.length; index++) {
            switch (index) {
                case 0: {
                    break;
                }
                case 1: {
                    arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_circle));
                }
                case 2: {
                    arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_circle));
                }
                case 3: {
                    arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_circle));
                }
                case 4: {
                    arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_circle));
                }
                case 5: {
                    arrImageView[5].setImageDrawable(getResources().getDrawable(R.drawable.ic_contact_circle));
                }
                case 6: {

                }
                default:
                    break;
            }
        }
    }

}
