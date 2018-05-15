package vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment3, container, false);
        recyclerView= view.findViewById(R.id.rcvShowCategoryExhibit);
        listExhibit = new ArrayList<>();
        listExhibit.add(new ExhibitModels(1,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(2,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(3,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(4,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(5,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(6,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        adapter_exhibitCategory = new RecyclerViewAdapter_ExhibitCategory(getContext(),listExhibit);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_exhibitCategory);
        return view;

    }

}
