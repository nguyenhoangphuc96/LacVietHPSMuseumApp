package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog.CustomProgressDialogTwo;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.Main;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.InfinityCycleImageViewAdapter;

public class ShowImageExhibitActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView tvTitleToolbar, tvShow3D;
    private ImageView imageView;
    //web api
    private ApiService mService;
    //ProgressDialog
    private ProgressBar pbShowImage;
    //
    int id;
    List<Bitmap> lstImages = new ArrayList<>();
    HorizontalInfiniteCycleViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_only);
        addControl();
        actionBar();
        loadAnswers();
        addEvent();


    }

    private void loadAnswers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //get id
                Bundle extras = getIntent().getExtras();
                KeyString key = new KeyString();
                id = extras.getInt(key.ITEM_KEY);


                //Get list image
                mService.getAllExhibitImageById(id,false).enqueue(new Callback<ImageByIDResponse>() {

                    @Override
                    public void onResponse(Call<ImageByIDResponse> call, Response<ImageByIDResponse> response) {
                        if (response.isSuccessful()) {
                            //initDataInfinityCycle(response.body().getExhibitImages());
                            showImage(response.body().getExhibitImages().get(0));
                            pbShowImage.setVisibility(View.GONE);
                            Log.d("AnswersPresenter", "posts loaded from API");
                        } else {
                            int statusCode = response.code();
                            Toast.makeText(ShowImageExhibitActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageByIDResponse> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");
                    }
                });
            }
        }).start();

    }
    private void showImage(String imageString) {
        if (imageString != "") {
            Bitmap bmp = Util.StringToBitMap(imageString);
            imageView.setImageBitmap(bmp);
        } else {
            imageView.setImageResource(R.drawable.img_no_image);
        }

    }
    public void showErrorMessage() {
        Toast.makeText(ShowImageExhibitActivity.this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }


    private void startShow3DActivity() {
        Intent intent = new Intent(ShowImageExhibitActivity.this, Main.class);
//        KeyString key = new KeyString();
//        intent.putExtra(key.ITEM_KEY, position);
        startActivity(intent);
    }


    private void addEvent() {
        tvShow3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShow3DActivity();
            }
        });

    }

    private void addControl() {
        pager = findViewById(R.id.horizontal_cycle);
        toolbar = findViewById(R.id.toolbarShowImageExhibit);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        tvShow3D = findViewById(R.id.tvShow3DImg);
        //
        pbShowImage = findViewById(R.id.pbShowImage);
        //
        mService = ApiUtils.getSOService();
        //
        lstImages = new ArrayList<>();
        imageView = findViewById(R.id.imvShowExhibit);
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

    /*private void initDataInfinityCycle(List<String> exhibitImages) {

        for (int i = 0; i < exhibitImages.size(); i++) {
            lstImages.add(Util.StringToBitMap(exhibitImages.get(i)));

        }

        showDatatoInfinityCycle(lstImages);


    }

    private void showDatatoInfinityCycle(List<Bitmap> lstImages) {
        InfinityCycleImageViewAdapter adapter = new InfinityCycleImageViewAdapter(lstImages, this);
        pager.setAdapter(adapter);

    }*/
}