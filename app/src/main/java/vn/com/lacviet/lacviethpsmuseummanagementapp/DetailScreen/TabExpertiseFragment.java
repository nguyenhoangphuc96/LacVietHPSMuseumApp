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

public class TabExpertiseFragment extends Fragment {
    //web api
    private ApiService mService;
    private TextView tvVerifiDate,tvExpertiseDate,tvExpertise,tvAge,tvSource,tvNoteSoure,tvTime,tvNotetime;
    //ProgressDialog
    private ProgressBar viewProgressBar;
    private CustomProgressDialogTwo customProgressDialogTwo;

    public TabExpertiseFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_expertise,container,false);
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
        Toast.makeText(getContext(), R.string.error_loading_from_API, Toast.LENGTH_SHORT).show();
    }
    private void showDataToView(ExhibitModel exhibitModel) {
        tvVerifiDate.setText(exhibitModel.getSTRVERIFICATIONDATE());
        tvExpertiseDate.setText(exhibitModel.getSTREXPERTISEDATE());
        tvExpertise.setText(exhibitModel.getEXPERTISENAME());
        tvSource.setText(exhibitModel.getSOURCENAME());
        tvNoteSoure.setText(exhibitModel.getRESOURCENOTE());
        tvTime.setText(exhibitModel.getTIMENAME());
        tvAge.setText(exhibitModel.getAGES());
        tvNotetime.setText(exhibitModel.getTIMENOTE());




    }

    private void addControl(View view) {

        mService = ApiUtils.getSOService();
        tvAge=view.findViewById(R.id.tvAges);
        tvExpertise=view.findViewById(R.id.tvExpertise);
        tvExpertiseDate=view.findViewById(R.id.tvExpertiseDate);
        tvNoteSoure=view.findViewById(R.id.tvNoteSource);
        tvNotetime=view.findViewById(R.id.tvNoteTime);
        tvSource=view.findViewById(R.id.tvSource);
        tvTime=view.findViewById(R.id.tvTime);
        tvVerifiDate=view.findViewById(R.id.tvVerifiDate);
        //
        viewProgressBar = view.findViewById(R.id.viewProgressBar);
        customProgressDialogTwo = new CustomProgressDialogTwo(getContext(),R.drawable.ic_vn_drum);

    }
}
