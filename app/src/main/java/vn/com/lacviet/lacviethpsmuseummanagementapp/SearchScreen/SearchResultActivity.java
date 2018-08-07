package vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.LoadMore.EndlessRecyclerViewScrollListener;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllResultJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenWithImageModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.ExhibitMainscreenRecyclerViewAdapter;

public class SearchResultActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView tvTitleToolbar;
    ProgressBar pbResult;
    //RecyclerView api
    private ExhibitMainscreenRecyclerViewAdapter mAdapter;
    private ApiService mService;
    //load more
    private int indexPage = 1;
    private int size = 20;
    int totalItem = 20;

    private EndlessRecyclerViewScrollListener scrollListener;

    //
    ArrayList<ExhibitMainScreenWithImageModel> ListExhibit;
    //
    int totalMaxSize;
    String stuffName;
    String stuffId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_exhibit);
        addControl();
        actionBar();
        showDataToRecyclerView();
        loadAnswers(indexPage, size);


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
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMoreAnswers(page, size);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadAnswers(int indexPage, int size) {
    /*   new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        //Get stuffId
        Bundle extras = this.getIntent().getExtras();
        KeyString key = new KeyString();
        stuffId = extras.getString(key.ITEM_KEY);
        //Get stuffName
        stuffName = extras.getString(key.STUFF_NAME);
        if(stuffName == null)
        {
            mService.getStuffById(stuffId).enqueue(new Callback<StuffModel>() {
                @Override
                public void onResponse(Call<StuffModel> call, Response<StuffModel> response) {
                    if (response.isSuccessful()) {
                        stuffName = response.body().getSTUFFNAME();
                        tvTitleToolbar.setText("Danh mục: " + stuffName);
                        Log.d("AnswersPresenter", "posts loaded from API");
                    } else {
                        int statusCode = response.code();
                        Toast.makeText(SearchResultActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StuffModel> call, Throwable t) {
                    showErrorMessage();
                    Log.d("AnswersPresenter", "error loading from API");
                }
            });
        }
        tvTitleToolbar.setText("Danh mục: " + stuffName);
        //When choose all stuff
        if(stuffId.equals("0"))
        {
            Intent intent = new Intent(SearchResultActivity.this, MainActivityNew.class);
            startActivity(intent);
        }
        //
        mService.getResultByStuff(indexPage, size, stuffId).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
                    pbResult.setVisibility(View.GONE);
                    totalMaxSize = response.body().getTotal();
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
                    Toast.makeText(SearchResultActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllResultJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }



    private void loadMoreAnswers(int i, int size) {
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(th, ""+i, Toast.LENGTH_SHORT).show();


            }
        }).run();*/

        mService.getResultByStuff(i + 1, size, stuffId).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
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
                    Toast.makeText(SearchResultActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllResultJsonResponse> call, Throwable t) {
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
                    Toast.makeText(SearchResultActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading image!!!");

            }
        });
    }

    public void showErrorMessage() {
        Toast.makeText(SearchResultActivity.this, R.string.error_loading_from_API, Toast.LENGTH_SHORT).show();
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        tvTitleToolbar.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarResult);
        recyclerView = findViewById(R.id.rcvShowResult);
        tvTitleToolbar = findViewById(R.id.tvTitleShowExhibitToolbar);
        pbResult = findViewById(R.id.pbShowResult);
        //api
        mService = ApiUtils.getSOService();


    }

    private void startDetailActivity(int id) {
        Intent intent = new Intent(this, ExhibitDetailActivityNew.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, id);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
        Intent intent = new Intent(this, NomalSearchActivity.class);
        startActivity(intent);
    }
}
