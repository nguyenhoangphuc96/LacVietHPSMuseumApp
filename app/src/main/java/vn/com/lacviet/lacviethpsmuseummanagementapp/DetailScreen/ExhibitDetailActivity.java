package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class ExhibitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);

        InfoDetailFragment fragment1= new InfoDetailFragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layoutExhibitDetail,fragment1,fragment1.getTag())
                .commit();
        ImageDetailFragment fragment2=new ImageDetailFragment();
        manager.beginTransaction()
                .replace(R.id.layoutExhibitImage,fragment2,fragment2.getTag())
                .commit();
    }

}
