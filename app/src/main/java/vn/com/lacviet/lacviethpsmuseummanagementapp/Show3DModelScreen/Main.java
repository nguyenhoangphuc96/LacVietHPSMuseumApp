package vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.obj.ObjModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.ply.PlyModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.stl.StlModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Show3DModelScreen.util.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

/*
 * Copyright 2017 Dmitry Brant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Main extends AppCompatActivity {
    private static final int READ_PERMISSION_REQUEST = 100;
    private static final int OPEN_DOCUMENT_REQUEST = 101;

    private static final String[] SAMPLE_MODELS
            = new String[] { "go_hoa_20mb.stl" };
    private static int sampleModelIndex;

    private ModelViewerApplication app;
    @Nullable private ModelSurfaceView modelView;
    private ViewGroup containerView;
    private ProgressBar progressBar;
    //web api
    private ApiService mService;
    StlModel model;
    String decodeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d_viewer);
        app = ModelViewerApplication.getInstance();

        containerView = findViewById(R.id.container_view);
        progressBar = findViewById(R.id.model_progress_bar);

        loadData(savedInstanceState);




    }

    private void loadData(Bundle savedInstanceState) {
        mService = ApiUtils.getSOService();
        mService.getFile3dByID("2788").enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response.isSuccessful()) {
                    String temp = response.body();
                    byte[] bytes = decodeBase64ByteArray(temp);
                    //decodeString = decodeBase64(temp);
                    progressBar.setVisibility(View.GONE);
                    if(bytes != null)
                    {
                        try {
                            model = new StlModel(new ByteArrayInputStream(bytes));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (getIntent().getData() != null && savedInstanceState == null) {
                            beginLoadModel(getIntent().getData());
                        }
                        loadSampleModel(model);
                    }

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(Main.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Main.this, "Vui lòng kiểm tra kết nối!" , Toast.LENGTH_SHORT).show();
                Log.d("AnswersPresenter", "error loading from API");
            }
        });
    }
    private byte[] decodeBase64ByteArray(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return valueDecoded;
    }
    private String decodeBase64(String coded){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode(coded.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }
    @Override
    protected void onStart() {
        super.onStart();
        createNewModelView(app.getCurrentModel());
        if (app.getCurrentModel() != null) {
            setTitle(app.getCurrentModel().getTitle());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (modelView != null) {
            modelView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (modelView != null) {
            modelView.onResume();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_PERMISSION_REQUEST:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    beginOpenModel();
                } else {
                    Toast.makeText(this, R.string.read_permission_failed, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == OPEN_DOCUMENT_REQUEST && resultCode == RESULT_OK && resultData.getData() != null) {
            Uri uri = resultData.getData();
            grantUriPermission(getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            beginLoadModel(uri);
        }
    }

    private void checkReadPermissionThenOpen() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_PERMISSION_REQUEST);
        } else {
            beginOpenModel();
        }
    }

    private void beginOpenModel() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST);
    }

    private void beginLoadModel(@NonNull Uri uri) {
        progressBar.setVisibility(View.VISIBLE);
        //new ModelLoadTask().execute(uri);
        setCurrentModel(model);
    }

    private void createNewModelView(@Nullable Model model) {
        if (modelView != null) {
            containerView.removeView(modelView);
        }
        ModelViewerApplication.getInstance().setCurrentModel(model);
        modelView = new ModelSurfaceView(this, model);
        containerView.addView(modelView, 0);
    }

    private class ModelLoadTask extends AsyncTask<Uri, Integer, Model> {
        protected Model doInBackground(Uri... file) {
            InputStream stream = null;
            try {
                Uri uri = file[0];
                ContentResolver cr = getApplicationContext().getContentResolver();
                String fileName = getFileName(cr, uri);

                if ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(uri.toString()).build();
                    Response response = client.newCall(request).execute();

                    // TODO: figure out how to NOT need to read the whole file at once.
                    stream = new ByteArrayInputStream(response.body().bytes());
                } else {
                    stream = cr.openInputStream(uri);
                }

                if (stream != null) {
                    Model model;
                    if (!TextUtils.isEmpty(fileName)) {
                        if (fileName.toLowerCase().endsWith(".stl")) {
                            model = new StlModel(stream);
                        } else if (fileName.toLowerCase().endsWith(".obj")) {
                            model = new ObjModel(stream);
                        } else if (fileName.toLowerCase().endsWith(".ply")) {
                            model = new PlyModel(stream);
                        } else {
                            // assume it's STL.
                            model = new StlModel(stream);
                        }
                        model.setTitle(fileName);
                    } else {
                        // assume it's STL.
                        // TODO: autodetect file type by reading contents?
                        model = new StlModel(stream);
                    }
                    return model;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeSilently(stream);
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Model model) {
            if (isDestroyed()) {
                return;
            }
            if (model != null) {
                setCurrentModel(model);
            } else {
                Toast.makeText(getApplicationContext(), R.string.open_model_error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        }

        @Nullable
        private String getFileName(@NonNull ContentResolver cr, @NonNull Uri uri) {
            if ("content".equals(uri.getScheme())) {
                String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME};
                Cursor metaCursor = ContentResolverCompat.query(cr, uri, projection, null, null, null, null);
                if (metaCursor != null) {
                    try {
                        if (metaCursor.moveToFirst()) {
                            return metaCursor.getString(0);
                        }
                    } finally {
                        metaCursor.close();
                    }
                }
            }
            return uri.getLastPathSegment();
        }
    }

    private void setCurrentModel(@NonNull Model model) {
        createNewModelView(model);
        Toast.makeText(getApplicationContext(), R.string.open_model_success, Toast.LENGTH_SHORT).show();
        //setTitle(model.getTitle());
        progressBar.setVisibility(View.GONE);
    }


    private void loadSampleModel(StlModel model) {
        try {
            InputStream stream = getApplicationContext().getAssets()
                    .open(SAMPLE_MODELS[sampleModelIndex++ % SAMPLE_MODELS.length]);
            setCurrentModel(model);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(R.string.about_text)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
