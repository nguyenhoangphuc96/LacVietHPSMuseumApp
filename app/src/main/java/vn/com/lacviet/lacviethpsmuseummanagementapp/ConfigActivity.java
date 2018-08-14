package vn.com.lacviet.lacviethpsmuseummanagementapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import vn.com.lacviet.lacviethpsmuseummanagementapp.Intro.IntroActivity;

public class ConfigActivity extends AppCompatActivity {
    EditText edtUrl;
    TextView tvOk;
    public static String configUrl="";
    public static String defaultUrl="demo.museum.vebrary.vn";
    //
    SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //set transparent stt bar
        StatusBarUtil.setTransparent(this);
        addControl();
        setEditText();
        addEvent();
        initSharedPre();
        //check saved config url
        checkConfigSaved();
    }

    private void initSharedPre() {
        mySharedPreferences = getSharedPreferences("MY_SETTING_CACHE", Activity.MODE_PRIVATE);
    }

    private void checkConfigSaved() {
        String cachedURL = mySharedPreferences.getString("baseURL","");
        if(!cachedURL.equals(""))
        {
            configUrl = cachedURL;
            startMainActivityNew(configUrl);
        }
    }

    private void saveCacheUrl() {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        // Store new primitive types in the shared preferences object.
        editor.putString("baseURL", configUrl);
        editor.commit();
    }

    private void setEditText() {
        edtUrl.setText(defaultUrl);
    }

    private void addEvent() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configUrl = edtUrl.getText().toString().trim();
                if(configUrl.startsWith("http://"))
                {
                    configUrl = configUrl.replace("http://","");
                    configUrl.trim();
                }
                if(configUrl.equals("demo.museum.vebrary.vn")) {
                    startMainActivityNew(configUrl);
                    saveCacheUrl();
                }
                else {
                    Toast.makeText(ConfigActivity.this, "URL invalid!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        edtUrl.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    configUrl = edtUrl.getText().toString().trim();
                    if(configUrl.startsWith("http://"))
                    {
                        configUrl = configUrl.replace("http://","");
                        configUrl.trim();
                    }
                    if(configUrl.equals("demo.museum.vebrary.vn")) {
                        startMainActivityNew(configUrl);
                        saveCacheUrl();
                    }
                    else {
                        Toast.makeText(ConfigActivity.this, "URL invalid!", Toast.LENGTH_SHORT).show();

                    }
                    return true;
                }
                return false;
            }
        });
    }
    private void startMainActivityNew(String configUrl){
        Intent intent = new Intent(ConfigActivity.this, MainActivityNew.class);
        KeyString key = new KeyString();
        intent.putExtra(key.CONFIG_URL, configUrl);
        startActivity(intent);
        finish();
    }
    private void addControl() {
        edtUrl = findViewById(R.id.edtConfig);
        tvOk = findViewById(R.id.tvOK);
    }
}
