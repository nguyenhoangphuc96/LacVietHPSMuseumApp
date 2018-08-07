package vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllMaterialTypeJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllStuffJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllTimeJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenWithImageModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.MaterialTypeModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.TimeModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.AdvancedSearchExpandListViewAdapter;

public class AdvancedSearchActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView tvTitleToolbar, tvSearch, tvNoResult;

    EditText edtSearch;
    Spinner spinnerSearch;
    String category = "any";
    String query = "";
    String stuffId = "";
    String materialtypeId = "";
    String timeId = "";

    //api
    private ApiService mService;
    List<StuffModel> ListStuffModel;
    List<TimeModel> ListTimeModel;
    List<MaterialTypeModel> ListMaterialTypeModel;

    ArrayList<String> listMaterialTypeName;
    ArrayList<String> listMaterialTypeId ;
    ArrayList<String> listTimeName;
    ArrayList<String> listTimeId;
    ArrayList<String> listStuffName;
    ArrayList<String> listStuffId;
    //
    Spinner spnMaterialType,spnStuff, spnTime;
    ArrayAdapter<String> materialAdapter,timeAdapter,stuffAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        addControl();
        actionBar();
        dropDownSpinnerCategory();
        dropDownSpinnerMaterialType();
        dropDownSpinnerTime();
        dropDownSpinnerStuff();

        loadAnswersMaterialType();
        loadAnswersTime();
        loadAnswersStuff();

        addEvent();


    }



    private void addEvent() {
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = edtSearch.getText().toString();
                startResultAdvanceActivity(category,query,stuffId,materialtypeId,timeId);

            }
        });
        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    query = edtSearch.getText().toString();
                    startResultAdvanceActivity(category,query,stuffId,materialtypeId,timeId);
                    return true;
                }
                return false;
            }
        });
    }
    private void startResultAdvanceActivity(String category , String query,String stuffId,String materialtypeId,String timeId) {
        Intent intent = new Intent(this, AdvancedResultActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.CATEGORY, category);
        intent.putExtra(key.QUERY, query);
        intent.putExtra(key.STUFF_ID, stuffId);
        intent.putExtra(key.MATERIAL_ID, materialtypeId);
        intent.putExtra(key.TIME_ID, timeId);


        startActivity(intent);
    }

    private void loadAnswersMaterialType() {
        mService.getAllMaterialType().enqueue(new Callback<AllMaterialTypeJsonResponse>() {
            @Override
            public void onResponse(Call<AllMaterialTypeJsonResponse> call, Response<AllMaterialTypeJsonResponse> response) {

                if (response.isSuccessful()) {
                    ListMaterialTypeModel = response.body().getMaterialTypeModels();
                    for (MaterialTypeModel materialTypeModel : ListMaterialTypeModel) {
                        listMaterialTypeName.add(materialTypeModel.getMATERIALNAME());
                        listMaterialTypeId.add(materialTypeModel.getMATERIALTYPE1());

                    }
                    materialAdapter.notifyDataSetChanged();
                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(AdvancedSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllMaterialTypeJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    private void loadAnswersTime() {
        mService.getAllTimes().enqueue(new Callback<AllTimeJsonResponse>() {
            @Override
            public void onResponse(Call<AllTimeJsonResponse> call, Response<AllTimeJsonResponse> response) {

                if (response.isSuccessful()) {
                    ListTimeModel = response.body().getTimeModels();
                    for (TimeModel timeModel : ListTimeModel) {
                        listTimeName.add(timeModel.getTIMENAME());
                        listTimeId.add(timeModel.getTIMEID());
                    }
                    timeAdapter.notifyDataSetChanged();

                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(AdvancedSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllTimeJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    private void loadAnswersStuff() {

        mService.getAllStuffs().enqueue(new Callback<AllStuffJsonResponse>() {
            @Override
            public void onResponse(Call<AllStuffJsonResponse> call, Response<AllStuffJsonResponse> response) {

                if (response.isSuccessful()) {
                    ListStuffModel = response.body().getStuffModels();
                    for (StuffModel stuffModel : ListStuffModel) {
                        listStuffName.add(stuffModel.getSTUFFNAME());
                        listStuffId.add(stuffModel.getSTUFFID());
                    }
                    stuffAdapter.notifyDataSetChanged();
                    Log.d("AnswersPresenter", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Toast.makeText(AdvancedSearchActivity.this, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllStuffJsonResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });


    }

    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_loading_from_API, Toast.LENGTH_SHORT).show();
    }

    /*private void addDataToExpListView() {

        listDataHeader.add("Loại hiện vật");
        listDataHeader.add("Thời kỳ");
        listDataHeader.add("Chất liệu");


        listDataChil.put(listDataHeader.get(0), listMaterialTypeName);
        listDataChil.put(listDataHeader.get(1), listTimeName);
        listDataChil.put(listDataHeader.get(2), listStuffName);
    }*/

   /* private void showExpandListView() {
        mdapter = new AdvancedSearchExpandListViewAdapter(this, listDataHeader, listDataChil);
        expListView.setAdapter(mdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                v.setSelected(true);
                return true;
            }
        });

    }*/

    private void dropDownSpinnerCategory() {
        spinnerSearch = findViewById(R.id.spinnerAdvancedSearch);

        String[] items = new String[]{"Bất kỳ", "Tên hiện vật", "Tên khác",
                "Số kiểm kê", "Số lượng", "Thành phần", "Niên đại"};
        String[] arrCategory = new String[]{"ANY", "EXHIBITNAME", "OTHERNAME",
                "CODEID", "NUMBER", "ELEMENT", "AGES"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(adapter);

        spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setTextSize(13);
                }
                category = arrCategory[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void dropDownSpinnerMaterialType() {

        materialAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listMaterialTypeName);

        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMaterialType.setAdapter(materialAdapter);



        spnMaterialType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlueLight));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);
                }
                materialtypeId = listMaterialTypeId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void dropDownSpinnerTime() {
        timeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listTimeName);

        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTime.setAdapter(timeAdapter);



        spnTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlueLight));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);
                }

                timeId = listTimeId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void dropDownSpinnerStuff() {
        stuffAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listStuffName);

        stuffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnStuff.setAdapter(stuffAdapter);



        spnStuff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlueLight));
                    ((TextView) parent.getChildAt(0)).setTextSize(14);
                }

                stuffId = listStuffId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
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
        toolbar = findViewById(R.id.toolbarAdvancedSearch);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        tvSearch = findViewById(R.id.tvSearch);
        edtSearch = findViewById(R.id.edtAdvancedSearch);
        //
        mService = ApiUtils.getSOService();
        //

        //
        spnMaterialType = findViewById(R.id.spinnerMaterialType);
        spnStuff =findViewById(R.id.spinnerStuff);
        spnTime = findViewById(R.id.spinnerTime);
        //

        listMaterialTypeName = new ArrayList<String>();
        listMaterialTypeId = new ArrayList<String>();
        listTimeName = new ArrayList<String>();
        listTimeId = new ArrayList<String>();
        listStuffName = new ArrayList<String>();
        listStuffId = new ArrayList<String>();
        //

    }
}
