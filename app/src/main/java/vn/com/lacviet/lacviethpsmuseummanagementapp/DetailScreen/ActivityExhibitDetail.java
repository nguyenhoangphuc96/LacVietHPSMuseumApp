package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment1;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment2;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment3;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class ActivityExhibitDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);

        FragmentInfoDetail fragment1= new FragmentInfoDetail();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layoutExhibitDetail,fragment1,fragment1.getTag())
                .commit();
        FragmentImageDetail fragment2=new FragmentImageDetail();
        manager.beginTransaction()
                .replace(R.id.layoutExhibitImage,fragment2,fragment2.getTag())
                .commit();

    }
}
