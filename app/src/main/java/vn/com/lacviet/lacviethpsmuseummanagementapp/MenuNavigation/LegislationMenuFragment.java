package vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class LegislationMenuFragment extends Fragment {
    public LegislationMenuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_legislation,container,false);
    }
}