package vn.com.lacviet.lacviethpsmuseummanagementapp.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.RecyclerViewAdapter_ExhibitCategory;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.RecyclerViewAdapter_SearchResult;
import vn.com.lacviet.lacviethpsmuseummanagementapp.model.ExhibitModels;

public class ExhibitOfCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_ExhibitCategory adapterCategory;
    private RecyclerView.LayoutManager layoutManager;
    private List<ExhibitModels> listExhibit;
    private FloatingActionButton mFAB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit_of_caterory);
        recyclerView=findViewById(R.id.rcvShowExhibitOfCategory);
        //mFAB = findViewById(R.id.menu);
        listExhibit = new ArrayList<>();
        listExhibit.add(new ExhibitModels(1,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(2,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(3,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(4,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(5,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_binhtra,"740","145" ));
        listExhibit.add(new ExhibitModels(6,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(7,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        listExhibit.add(new ExhibitModels(8,"Trống nhạc","Trống nhạc là hiện vật do Toà thánh Ngọc Sắc trao tặng...",R.drawable.img_trong_nhac,"740","145" ));
        adapterCategory = new RecyclerViewAdapter_ExhibitCategory(this,listExhibit);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCategory);
        //Floating Action Button


    }
}