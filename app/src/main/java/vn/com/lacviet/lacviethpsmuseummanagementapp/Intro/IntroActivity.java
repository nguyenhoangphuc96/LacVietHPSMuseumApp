package vn.com.lacviet.lacviethpsmuseummanagementapp.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ContactScreen.ContactActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ContactScreen.MapActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class IntroActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvTitleToolbar;
    WebView wvIntro;
    ProgressBar pbIntro;
    //api
    private ApiService mService;
    String data = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        addControl();
        actionBar();
        loadAnswer();
    }

    private void showWebView() {
        wvIntro.setWebViewClient(new WebViewClient());
        wvIntro.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
    }

    private void loadAnswer() {
        mService.getPage("gioi-thieu-chung").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    data=response.body();
                    pbIntro.setVisibility(View.GONE);
                    showWebView();

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(IntroActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }


    private void startMapActivity() {
        Intent intent = new Intent(IntroActivity.this,MapActivity.class);
        startActivity(intent);
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
        toolbar = findViewById(R.id.toolbarIntro);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        wvIntro = findViewById(R.id.wvIntro);
        pbIntro = findViewById(R.id.pbIntro);
        //
        mService = ApiUtils.getSOService();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_loading_from_API, Toast.LENGTH_SHORT).show();
    }


}
