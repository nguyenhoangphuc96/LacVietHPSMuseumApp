package vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.LoadMore.EndlessRecyclerViewScrollListener;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllResultJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenWithImageModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.ExhibitMainscreenRecyclerViewAdapter;

public class NomalSearchActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView tvTitleToolbar, tvSearch,tvNoResult,tvAdvanceSearch;
    RecyclerView recyclerView;
    ProgressBar pbSearch;
    EditText edtSearch;
    Spinner spinnerSearch;
    Boolean isSearched = false;
    //RecyclerView api
    private ExhibitMainscreenRecyclerViewAdapter mAdapter;
    private ApiService mService;
    //
    ArrayList<ExhibitMainScreenWithImageModel> ListExhibit;
    //
    int  totalMaxSize;
    //load more
    private int indexPage = 1;
    private int size = 20;
    private EndlessRecyclerViewScrollListener scrollListener;
    int totalItem = 20;
    String category = "any";
    String query = "";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addControl();
        actionBar();
        dropDownSpinner();
        showDataToRecyclerView();
        checkToShowProgressBar();
        addEvent();


    }

    private void checkToShowProgressBar() {
        if(!isSearched)
        {
            pbSearch.setVisibility(View.GONE);

        }
        else
        {
        }
    }

    private void addEvent() {
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnswers(indexPage,size);
                isSearched = true;
            }
        });
        tvAdvanceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdvanceSearchActivity();
            }
        });
        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    loadAnswers(indexPage,size);
                    isSearched = true;

                    return true;
                }
                return false;
            }
        });
    }
    private void loadMoreAnswers(int i, int size) {
        query =edtSearch.getText().toString();

     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(th, ""+i, Toast.LENGTH_SHORT).show();


            }
        }).run();*/

        //
        mService.getResultSearchNormal(i+1, size, category,query).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
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
                    Toast.makeText(NomalSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllResultJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });

    }
    private void loadAnswers(int indexPage, int size) {
        query =edtSearch.getText().toString();



        //
        mService.getResultSearchNormal(indexPage, size, category,query).enqueue(new Callback<AllResultJsonResponse>() {
            @Override
            public void onResponse(Call<AllResultJsonResponse> call, Response<AllResultJsonResponse> response) {

                if (response.isSuccessful()) {
                    pbSearch.setVisibility(View.GONE);
                    totalMaxSize = response.body().getTotal();
                    ListExhibit = new ArrayList<>();

                    ArrayList<ExhibitMainScreenModel> list = (ArrayList<ExhibitMainScreenModel>) response.body().getExhibitMainScreenModels();

                    for (ExhibitMainScreenModel em : list) {
                        ListExhibit.add(new ExhibitMainScreenWithImageModel(em.getEXHID(), em.getEXHIBITNAME(), em.getDESCRIPTION(), null));
                    }

                    mAdapter.updateAnswers(ListExhibit);
                    //Check result
                    if(mAdapter.getTestList().size()==0)
                    {
                        tvNoResult.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        tvNoResult.setVisibility(View.GONE);
                    }
                    //mAdapter.updateAnswers(response.body().getExhibitModels());

                    for (ExhibitMainScreenWithImageModel tm : mAdapter.getTestList()) {
                        loadImage(tm);
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(NomalSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
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

        mService.getExhibitImageById(mod.geteXHID(),true).enqueue(new Callback<String>() {
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
                    Toast.makeText(NomalSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(NomalSearchActivity.this, R.string.error_loading_from_API, Toast.LENGTH_SHORT).show();
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
    private void startDetailActivity(int id) {
        Intent intent = new Intent(this, ExhibitDetailActivityNew.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, id);
        startActivity(intent);
    }

    private void dropDownSpinner() {
        spinnerSearch = findViewById(R.id.spinnerSearch);

        String[] items = new String[] { "Bất kỳ", "Tên hiện vật", "Tên khác",
                "Số kiểm kê", "Số lượng","Thành phần", "Niên đại" };
        String[] arrCategory = new String[] {"ANY", "EXHIBITNAME","OTHERNAME",
                "CODEID","NUMBER","ELEMENT","AGES"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(adapter);

        spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setTextSize(13);
                }
                category = arrCategory[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarSearch);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        tvSearch = findViewById(R.id.tvSearch);
        recyclerView = findViewById(R.id.rcvSearchNormal);
        pbSearch = findViewById(R.id.pbSearchNormal);
        edtSearch = findViewById(R.id.edtSearchNormal);
        tvNoResult = findViewById(R.id.tvNoResult);
        tvAdvanceSearch = findViewById(R.id.tvAdvanceSearch);
        //api
        mService = ApiUtils.getSOService();

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
    private void startAdvanceSearchActivity() {
        Intent intent = new Intent(this, AdvancedSearchActivity.class);
        startActivity(intent);
    }
}
