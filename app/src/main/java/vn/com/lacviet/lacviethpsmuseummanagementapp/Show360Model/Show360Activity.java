package vn.com.lacviet.lacviethpsmuseummanagementapp.Show360Model;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class Show360Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar, tvShow3D;
    private ImageView imageView;
    //web api
    private ApiService mService;
    //ProgressDialog
    private ProgressBar pbShowImage;
    //
    AnimationDrawable animation;
    //
    List<Integer> lstImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_360);
        addControl();
        actionBar();
        pbShowImage.setVisibility(View.GONE);

        initDataList();
        //startAnimation();
        show360();

    }

    private void show360() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                /*for(int i=0;i<lstImages.size();i++)
                {
                    imageView.setImageResource(lstImages.get(i));
                    handler.postDelayed(this, 500);
                }*/
                int i=0;
                imageView.setImageResource(lstImages.get(i));
                i++;
                if(i >=lstImages.size()) {
                    i = 0;
                }
                imageView.setImageResource(lstImages.get(i));
                handler.postDelayed(this, 500);

            }
        }, 500);
    }

    private void initDataList() {
        lstImages = new ArrayList<>();
        lstImages.add(R.drawable.img_background_config);
        lstImages.add(R.drawable.img_bg_cauhinh);
        lstImages.add(R.drawable.img_panarama);
        lstImages.add(R.drawable.img_no_image);
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarShowImageExhibit);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        tvShow3D = findViewById(R.id.tvShow3DImg);
        imageView = findViewById(R.id.imvShow360);
        //
        pbShowImage = findViewById(R.id.pbShowImage);
        //
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
    class Starter implements Runnable {
        public void run() {
            animation.start();
        }
    }

    private void startAnimation(){
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
