package vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen.AdvancedSearchActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.SearchScreen.NomalSearchActivity;

public class SearchMenuFragment extends Fragment {
    TextView tvNormalSearch, tvAdvanceSearch;
    public SearchMenuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu_search,container,false);
        addControl(view);

        addEvent();
        return view;
    }

    private void addEvent() {
        tvNormalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNormalSearchActivity();
            }
        });
        tvAdvanceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdvanceSearchActivity();
            }
        });

    }

    private void startNormalSearchActivity() {
        Intent intent = new Intent(getActivity(), NomalSearchActivity.class);
        startActivity(intent);
    }
    private void startAdvanceSearchActivity() {
        Intent intent = new Intent(getActivity(), AdvancedSearchActivity.class);
        startActivity(intent);
    }

    private void addControl(View view) {
        tvAdvanceSearch = view.findViewById(R.id.tvAdvanceSearch);
        tvNormalSearch = view.findViewById(R.id.tvNormalSearch);
    }
}