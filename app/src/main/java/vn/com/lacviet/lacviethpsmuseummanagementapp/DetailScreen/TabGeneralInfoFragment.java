package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.Key;

import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class TabGeneralInfoFragment extends Fragment {
    public TabGeneralInfoFragment() {
        // Required empty public constructor
    }
    
    private TextView tvExhibitName;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_general_info, container, false);

        addControls(view);
        addEvents();
        showDataToView();

        return view;
    }

    private void showDataToView() {
        Bundle extras = getActivity().getIntent().getExtras();
        KeyString key = new KeyString();
        int position = extras.getInt(key.ITEM_KEY);
        tvExhibitName.setText("vi tri: "+position);
    }

    private void addEvents() {
    }

    private void addControls(View view) {

        tvExhibitName = view.findViewById(R.id.tvExhibitName);
    }
}
