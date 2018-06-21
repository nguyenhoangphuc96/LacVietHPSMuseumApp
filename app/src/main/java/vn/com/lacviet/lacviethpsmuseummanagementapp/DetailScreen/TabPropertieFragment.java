package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog.CustomProgressDialogTwo;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class TabPropertieFragment extends Fragment {
    //web api
    private ApiService mService;
    private TextView tvDescPhysic,tvColor,tvMakeTech,tvMaintance,tvRelics;
    //ProgressDialog
    private ProgressBar viewProgressBar;
    private CustomProgressDialogTwo customProgressDialogTwo;

    public TabPropertieFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_propertie,container,false);
        addControl(view);
        loadAnswers();
        return view;
    }
    private void showProgressDialog() {
        viewProgressBar.setVisibility(View.GONE);
        customProgressDialogTwo.show();
    }

    private void loadAnswers() {
        //get id
        Bundle extras = getActivity().getIntent().getExtras();
        KeyString key = new KeyString();
        int id = extras.getInt(key.ITEM_KEY);

        mService.getExhibitById(id).enqueue(new Callback<ExhibitModel>() {
            @Override
            public void onResponse(Call<ExhibitModel> call, Response<ExhibitModel> response) {

                if(response.isSuccessful()) {
                    showDataToView(response.body());
                    Log.d("AnswersPresenter", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(getContext(), "Error"+statusCode+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExhibitModel> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }


    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
    }
    private void showDataToView(ExhibitModel exhibitModel) {
        tvDescPhysic.setText(exhibitModel.getDESCPHYSICS());
        tvColor.setText(exhibitModel.getCOLOR());
        tvMaintance.setText(exhibitModel.getMAINTENANCENAME());
        tvMakeTech.setText(exhibitModel.getMAKETECHNAME());
        tvRelics.setText(exhibitModel.getRELICS());






    }

    private void addControl(View view) {

        mService = ApiUtils.getSOService();
        tvDescPhysic=view.findViewById(R.id.tvDescPhysics);
        tvColor=view.findViewById(R.id.tvColor);
        tvMaintance=view.findViewById(R.id.tvMaintance);
        tvMakeTech=view.findViewById(R.id.tvMakeTech);
        tvRelics=view.findViewById(R.id.tvRelics);
        //
        viewProgressBar = view.findViewById(R.id.viewProgressBar);
        customProgressDialogTwo = new CustomProgressDialogTwo(getContext(),R.drawable.ic_vn_drum);



    }
}
