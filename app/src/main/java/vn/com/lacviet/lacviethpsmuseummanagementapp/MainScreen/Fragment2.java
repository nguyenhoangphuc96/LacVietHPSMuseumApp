package vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.ExhibitCollectionRecyclerViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ExhibitCollectionRecyclerViewAdapter adapterCollection;
    private List<Integer> listImageExhibit;
    private int arrTextViewId[] = {R.id.tvTitleCollection1,R.id.tvTitleCollection2,R.id.tvTitleCollection3,R.id.tvTitleCollection4};
    private TextView arrTextView[] = new TextView[arrTextViewId.length];

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2, container, false);
        //
        addControls(view);
        addEvents();
        addDataRecyclerView();
        showRecyclerView();
        setSelectedTextViewTitleCollection(arrTextView[0]);
        return view;

    }

    private void showRecyclerView() {
        adapterCollection = new ExhibitCollectionRecyclerViewAdapter(getContext(),listImageExhibit);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCollection);
    }

    private void addDataRecyclerView() {
        listImageExhibit = new ArrayList<>();
        listImageExhibit.add(R.drawable.img_chen);
        listImageExhibit.add(R.drawable.img_binhtra);
        listImageExhibit.add(R.drawable.img_dia);
        listImageExhibit.add(R.drawable.img_trong_nhac);
        listImageExhibit.add(R.drawable.img_binh);
        listImageExhibit.add(R.drawable.img_binhtra);

    }

    private void addControls(View view) {
        recyclerView= view.findViewById(R.id.rcvShowCollectionExhibit);
        int index = 0;
        for(index = 0;index<arrTextView.length;index++){
            arrTextView[index] = view.findViewById(arrTextViewId[index]);
            arrTextView[index].setOnClickListener(this);
        }
    }

    private void addEvents() {
        int index = 0;
        for(index = 0;index<arrTextView.length;index++){
            arrTextView[index].setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tvTitleCollection1:{
                setDefaultTextViewTitleCollection();
                setSelectedTextViewTitleCollection(arrTextView[0]);
                break;
            }
            case R.id.tvTitleCollection2:{
                setDefaultTextViewTitleCollection();
                setSelectedTextViewTitleCollection(arrTextView[1]);
                break;
            }
            case R.id.tvTitleCollection3:{
                setDefaultTextViewTitleCollection();
                setSelectedTextViewTitleCollection(arrTextView[2]);
                break;
            }
            case R.id.tvTitleCollection4:{
                setDefaultTextViewTitleCollection();
                setSelectedTextViewTitleCollection(arrTextView[3]);
                break;
            }
            default:break;
        }
    }

    private void setSelectedTextViewTitleCollection(TextView tv){
        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
        tv.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setDefaultTextViewTitleCollection(){
        int index =0;
        for(index =0;index<arrTextView.length;index++){
            arrTextView[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
            arrTextView[index].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }
}
