package vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.RecyclerViewAdapter_ExhibitCategory;
import vn.com.lacviet.lacviethpsmuseummanagementapp.model.ExhibitModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter_ExhibitCategory adapter_exhibitCategory;
    private RecyclerView.LayoutManager layoutManager;
    private List<ExhibitModels> listExhibit;
    private ImageView imgBackCategoryTitle,imgFowardCategoryTitle;
    private ArrayList<String> listCategory;
    private int categoryPosition = 0;
    private TextView tvTitleCategory;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment3, container, false);
        
        addControls(view);
        addEvents();
        addDataRecyclerView();
        showRecyclerView();
        addCategoryData();
        showDefaultCategory();

        return view;
    }

    private void showDefaultCategory() {
        String cate = listCategory.get(categoryPosition);
        tvTitleCategory.setText(cate);
    }

    private void addCategoryData() {
        listCategory = new ArrayList<>();
        listCategory.add("Đồ gỗ");
        listCategory.add("Đồ dệt");
        listCategory.add("Đồ kim loại");
    }

    private void showRecyclerView() {
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter_exhibitCategory = new RecyclerViewAdapter_ExhibitCategory(getContext(),listExhibit);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_exhibitCategory);

    }

    private void addDataRecyclerView() {
        listExhibit = new ArrayList<>();
        listExhibit.add(new ExhibitModels(1,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(2,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(3,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(4,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(5,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(6,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
    }

    private void addControls(View view) {
        recyclerView = view.findViewById(R.id.rcvShowCategoryExhibit);
        imgBackCategoryTitle = view.findViewById(R.id.imgBackCategoryTitle);
        imgFowardCategoryTitle = view.findViewById(R.id.imgFowardCategoryTitle);
        tvTitleCategory = view.findViewById(R.id.tvTitleCategory);
    }

    private void addEvents() {
        imgBackCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackCategory();
            }
        });
        
        imgFowardCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFowardCategory();
            }
        });
    }

    private void onFowardCategory() {
        categoryPosition = categoryPosition < listCategory.size()-1 ? ++categoryPosition : listCategory.size()-1;
        String cate = listCategory.get(categoryPosition);
        tvTitleCategory.setText(cate);
    }

    private void onBackCategory() {
        categoryPosition = categoryPosition > 0 ? --categoryPosition : 0;
        String cate = listCategory.get(categoryPosition);
        tvTitleCategory.setText(cate);
    }

}
