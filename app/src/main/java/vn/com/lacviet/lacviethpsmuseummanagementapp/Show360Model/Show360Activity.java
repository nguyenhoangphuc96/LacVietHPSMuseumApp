package vn.com.lacviet.lacviethpsmuseummanagementapp.Show360Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.Main;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.stl.StlModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class Show360Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar, tvNoResult;
    private ImageView imageView;
    //web api
    private ApiService mService;
    //ProgressDialog
    private ProgressBar pbShowImage;
    //
    AnimationDrawable animation;
    //
    List<String> lstImages;
    int id;
    //
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    ProgressBar mProgress;
    ImageView imvProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_360);
        addControl();
        actionBar();
        pbShowImage.setVisibility(View.GONE);
        getDataFromPreviousActivity();
        showProcessBar();
        loadDataList(id);
        //startAnimation();


    }

    private void getDataFromPreviousActivity() {
        Bundle extras = getIntent().getExtras();
        KeyString key = new KeyString();
        id = extras.getInt(key.ITEM_KEY);
    }

    private void loadDataList(int id) {
        mService = ApiUtils.getSOService();
        mService.getExhibitImage360ById(id, true).enqueue(new Callback<ImageByIDResponse>() {

            @Override
            public void onResponse(Call<ImageByIDResponse> call, Response<ImageByIDResponse> response) {
                if (response.isSuccessful()) {
                    lstImages = new ArrayList<>();
                    lstImages = response.body().getExhibitImages();
                    //
                    if (lstImages != null) {
                        //
                        mProgress.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);
                        imvProgress.setVisibility(View.GONE);
                        //
                        pbShowImage.setVisibility(View.GONE);
                        show360();
                    } else {
                        //
                        mProgress.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);
                        imvProgress.setVisibility(View.GONE);
                        //
                        pbShowImage.setVisibility(View.GONE);
                        tvNoResult.setVisibility(View.VISIBLE);
                    }
                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(Show360Activity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageByIDResponse> call, Throwable t) {
                //
                mProgress.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                imvProgress.setVisibility(View.GONE);
                //
                pbShowImage.setVisibility(View.GONE);
                tvNoResult.setVisibility(View.VISIBLE);
                //Toast.makeText(Show360Activity.this, "Vui lòng kiểm tra kết nối!" , Toast.LENGTH_SHORT).show();
                Log.d("AnswersPresenter", "error loading from API");
            }
        });
    }

    private void showProcessBar() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


        tv = (TextView) findViewById(R.id.tv);
        imvProgress = findViewById(R.id.imvcircularProgress);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(100); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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

    int i = 0;

    private void show360() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                showImage(lstImages.get(0));
                //imageView.setImageResource(lstImages.get(0));
                if (i >= lstImages.size()) {
                    i = 0;
                }
                showImage(lstImages.get(i));
                //imageView.setImageResource(lstImages.get(i));
                i++;
                handler.postDelayed(this, 200);

            }
        }, 200);
    }


    private void addControl() {
        toolbar = findViewById(R.id.toolbarShowImageExhibit);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        tvNoResult = findViewById(R.id.tvNoResult);
        imageView = findViewById(R.id.imvShow360);
        //
        pbShowImage = findViewById(R.id.pbShowImage);
        //


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

    class Starter implements Runnable {
        public void run() {
            animation.start();
        }
    }

    private void startAnimation() {
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.img_background_config), 300);
        animation.addFrame(getResources().getDrawable(R.drawable.img_bg_cauhinh), 300);
        animation.addFrame(getResources().getDrawable(R.drawable.img_panarama), 300);
        animation.addFrame(getResources().getDrawable(R.drawable.img_no_image), 300);
        animation.setOneShot(false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500, 500);
        params.alignWithParent = true;
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        imageView.setLayoutParams(params);
        imageView.setImageDrawable(animation);
        imageView.post(new Starter());
    }
}
