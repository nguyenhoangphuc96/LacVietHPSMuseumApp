package vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;


import vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen.SearchResultActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllStuffJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.CategogyNameRecyclerViewAdapter;


public class CategogMenuFragment extends Fragment {
    private CategogyNameRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ApiService mService;
    //ProgressDialog
    private ProgressBar viewProgressBar;
    //
    String stuffId;
    String stuffName;
    //
    Boolean isCategoryLoaded = false;
    List<StuffModel> ListStuff;


    public CategogMenuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_categogy,container,false);

        addControl(view);
        showDataToRecyclerView();

        loadAnswers();

        addEvent();

        return  view;
    }




    private void showDataToRecyclerView() {
        mAdapter = new CategogyNameRecyclerViewAdapter(getContext(), new ArrayList<StuffModel>(0), new CategogyNameRecyclerViewAdapter.PostItemListener() {


            @Override
            public void onPostClick(String id) {
                if(id=="")
                {
                    startHome();
                }
                else
                {
                    startResultActivity(id);
                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
    private void startResultActivity(String stuffId) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, stuffId);
        startActivity(intent);
    }
    private void startHome()
    {
        Intent intent = new Intent(getContext(), MainActivityNew.class);
        startActivity(intent);
    }
    private void loadAnswers() {
        if (isCategoryLoaded)
        {
            mAdapter.updateAnswers(ListStuff);
            viewProgressBar.setVisibility(View.GONE);
        }
        else
        {
            mService.getAllStuffs().enqueue(new Callback<AllStuffJsonResponse>() {
                @Override
                public void onResponse(Call<AllStuffJsonResponse> call, Response<AllStuffJsonResponse> response) {

                    if(response.isSuccessful()) {
                        mAdapter.updateAnswers(response.body().getStuffModels());
                        ListStuff= response.body().getStuffModels();
                        isCategoryLoaded=true;
                        viewProgressBar.setVisibility(View.GONE);
                        Log.d("AnswersPresenter", "posts loaded from API");
                    }else {
                        int statusCode  = response.code();
                        Toast.makeText(getContext(), "Error"+statusCode+response.message(), Toast.LENGTH_SHORT).show();
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


    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
    }

private void addEvent() {

    }

    private void addControl(View view) {
        mService = ApiUtils.getSOService();
        mRecyclerView = view.findViewById(R.id.rcvCategogyNameMenu);
        //
        viewProgressBar = view.findViewById(R.id.pbCategogyMenu);



    }

}