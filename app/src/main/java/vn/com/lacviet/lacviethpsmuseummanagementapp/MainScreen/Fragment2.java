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
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.RecyclerViewAdapter_ExhibitCollection;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_ExhibitCollection adapterCollection;
    private List<Integer> listImageExhibit;
    private boolean tvTitle1Clicked = true;
    private boolean tvTitle2Clicked = false;
    private boolean tvTitle3Clicked = false;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2, container, false);
        //
        final TextView tvTitle1=view.findViewById(R.id.tvTitleCollection1);
        final TextView tvTitle2=view.findViewById(R.id.tvTitleCollection2);
        final TextView tvTitle3=view.findViewById(R.id.tvTitleCollection3);

        recyclerView= view.findViewById(R.id.rcvShowCollectionExhibit);
        listImageExhibit = new ArrayList<>();
        listImageExhibit.add(R.drawable.img_chen);
        listImageExhibit.add(R.drawable.img_binhtra);
        listImageExhibit.add(R.drawable.img_dia);
        listImageExhibit.add(R.drawable.img_trong_nhac);
        listImageExhibit.add(R.drawable.img_binh);
        listImageExhibit.add(R.drawable.img_binhtra);

        adapterCollection = new RecyclerViewAdapter_ExhibitCollection(getContext(),listImageExhibit);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCollection);

        //
        tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
        tvTitle1.setTextColor(getResources().getColor(R.color.colorWhite));
        tvTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvTitle1Clicked) {
                    tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                    tvTitle1.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle2Clicked=tvTitle3Clicked=false;


                }
                else
                {
                    if(tvTitle2Clicked)
                    {
                        tvTitle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                        tvTitle2.setTextColor(getResources().getColor(R.color.colorBlack));
                        tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                        tvTitle1.setTextColor(getResources().getColor(R.color.colorWhite));
                        tvTitle1Clicked=true;
                        tvTitle2Clicked=tvTitle3Clicked=false;

                    }
                    else
                    {
                        tvTitle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                        tvTitle3.setTextColor(getResources().getColor(R.color.colorBlack));
                        tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                        tvTitle1.setTextColor(getResources().getColor(R.color.colorWhite));
                        tvTitle1Clicked=true;
                        tvTitle2Clicked=tvTitle3Clicked=false;
                    }
                }

            }
        });
        tvTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvTitle2Clicked) {
                tvTitle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                tvTitle2.setTextColor(getResources().getColor(R.color.colorWhite));
                tvTitle1Clicked=tvTitle3Clicked=false;


            }
            else
            {
                if(tvTitle1Clicked)
                {
                    tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                    tvTitle1.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTitle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                    tvTitle2.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle2Clicked=true;
                    tvTitle1Clicked=tvTitle3Clicked=false;

                }
                else
                {
                    tvTitle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                    tvTitle3.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTitle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                    tvTitle2.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle2Clicked=true;
                    tvTitle1Clicked=tvTitle3Clicked=false;
                }
            }
            }
        });tvTitle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvTitle3Clicked) {
                tvTitle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                tvTitle3.setTextColor(getResources().getColor(R.color.colorWhite));
                tvTitle2Clicked=tvTitle1Clicked=false;


            }
            else
            {
                if(tvTitle2Clicked)
                {
                    tvTitle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                    tvTitle2.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTitle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                    tvTitle3.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle3Clicked=true;
                    tvTitle2Clicked=tvTitle1Clicked=false;

                }
                else
                {
                    tvTitle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_unselected));
                    tvTitle1.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTitle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_text_selected));
                    tvTitle3.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle3Clicked=true;
                    tvTitle2Clicked=tvTitle1Clicked=false;
                }
            }
            }
        });


        return view;

        //
    }

  
}
