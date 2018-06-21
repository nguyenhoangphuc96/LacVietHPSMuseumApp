package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen.SearchResultActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.InfinityCycleImageViewAdapter;

public class TabGeneralInfoFragment extends Fragment {

    List<Bitmap> lstImages;
    HorizontalInfiniteCycleViewPager pager;
    ImageView imgExhibitDatail;
    //web api
    private ApiService mService;
    private TextView tvName, tvOtherName, tvStuff, tvNumber, tvFeild, tvCode, tvMaterial, tvElement, tvDesc;
    //
    ProgressBar pbImage, pbInfinity;
    //
    int id;
    String stuffId="";
    String stuffName;
    //
    TextView tvSameExhibit;
    RelativeLayout rlInfinity;

    public TabGeneralInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_general_info, container, false);

        addControls(view);
        loadAnswers();
        loadImageDefault();
        loadImageList();
        addEvents();
        return view;
    }

    private void loadImageList() {

        //Get list image
        mService.getAllExhibitImageById(id, true).enqueue(new Callback<ImageByIDResponse>() {

            @Override
            public void onResponse(Call<ImageByIDResponse> call, Response<ImageByIDResponse> response) {

                if (response.isSuccessful()) {
                    initDataInfinityCycle(response.body().getExhibitImages());
                    pbInfinity.setVisibility(View.GONE);
                    Log.d("AnswersPresenter", "Detail image list loaded!!!");
                } else {

                    int statusCode = response.code();
                    Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ImageByIDResponse> call, Throwable t) {
                rlInfinity.setVisibility(View.GONE);
                Log.d("AnswersPresenter", "error loading from API");
            }
        });

    }


    private void loadImageDefault() {

        //get image default
        mService.getExhibitImageById(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {

                    showImage(response.body());
                    pbImage.setVisibility(View.GONE);

                    Log.d("AnswersPresenter", "Detail image default loaded!!!");
                } else {

                    int statusCode = response.code();
                    Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });


    }


    private void loadAnswers() {

        //get id
        Bundle extras = getActivity().getIntent().getExtras();
        KeyString key = new KeyString();
        id = extras.getInt(key.ITEM_KEY);


        mService.getExhibitById(id).enqueue(new Callback<ExhibitModel>() {


            @Override
            public void onResponse(Call<ExhibitModel> call, Response<ExhibitModel> response) {

                if (response.isSuccessful()) {
                    showDataToView(response.body());
                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ExhibitModel> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });


    }

    private void showImage(String imageString) {
        if (imageString != "") {
            Bitmap bmp = Util.StringToBitMap(imageString);
            imgExhibitDatail.setImageBitmap(bmp);
        } else {
            imgExhibitDatail.setImageResource(R.drawable.img_no_image);
        }

    }

    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    private void showDataToView(ExhibitModel body) {

        tvName.setText(body.getEXHIBITNAME());
        tvOtherName.setText(body.getOTHERNAME());
        tvCode.setText(body.getCODEID());
        tvDesc.setText(body.getDESCRIPTION());
        tvStuff.setText(body.getSTUFFNAME());
        tvNumber.setText(body.getNUMBER().toString());
        tvElement.setText(body.getELEMENT());
        tvMaterial.setText(body.getMATERIALNAME());
        tvFeild.setText(body.getFIELDNAME());
        //
        stuffId = body.getSTUFF();
        stuffName = body.getSTUFFNAME();

    }

    private void addEvents() {
        imgExhibitDatail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShowImageActivity(id);
            }
        });
        pager.startAutoScroll(true);
        tvSameExhibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResultActivity(stuffId, stuffName);
            }
        });

    }

    private void startResultActivity(String stuffId, String stuffName) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, stuffId);
        intent.putExtra(key.STUFF_NAME, stuffName);
        startActivity(intent);
    }

    private void startShowImageActivity(int id) {
        Intent intent = new Intent(getContext(), ShowImageExhibitActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, id);
        getContext().startActivity(intent);
    }

    private void addControls(View view) {


        pager = view.findViewById(R.id.horizontal_cycle_mini);
        imgExhibitDatail = view.findViewById(R.id.imgExhibitDetail);
        //web api
        mService = ApiUtils.getSOService();
        tvName = view.findViewById(R.id.tvExhibitName);
        tvOtherName = view.findViewById(R.id.tvOtherName);
        tvStuff = view.findViewById(R.id.tvStuff);
        tvNumber = view.findViewById(R.id.tvNumber);
        tvFeild = view.findViewById(R.id.tvField);
        tvCode = view.findViewById(R.id.tvCodeID);
        tvMaterial = view.findViewById(R.id.tvMaterialName);
        tvElement = view.findViewById(R.id.tvElement);
        tvDesc = view.findViewById(R.id.tvDescription);

        //
        pbImage = view.findViewById(R.id.pbImageDetail);
        pbInfinity = view.findViewById(R.id.pbInfinitycycleview);
        //
        lstImages = new ArrayList<>();
        //
        tvSameExhibit = view.findViewById(R.id.tvSameExhibit);
        //
        rlInfinity = view.findViewById(R.id.rlInfiityCycleView);
    }

    private void initDataInfinityCycle(List<String> exhibitImages) {
        Bitmap bmp = Util.StringToBitMap(exhibitImages.get(0));
        Bitmap noImage = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_no_image);
        if (exhibitImages == null) {
            rlInfinity.setVisibility(View.GONE);
        } else if (exhibitImages.size() == 1) {
            rlInfinity.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < exhibitImages.size(); i++) {
                lstImages.add(Util.StringToBitMap(exhibitImages.get(i)));

            }
            showDatatoInfinityCycle(lstImages);
        }



    }

    private void showDatatoInfinityCycle(List<Bitmap> lstImages) {
        InfinityCycleImageViewAdapter adapter = new InfinityCycleImageViewAdapter(lstImages, getContext());
        pager.setAdapter(adapter);

    }
}
