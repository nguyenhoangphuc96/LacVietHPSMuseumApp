package vn.com.lacviet.lacviethpsmuseummanagementapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment1;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment2;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen.Fragment3;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment1 fragment1= new Fragment1();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layout1,fragment1,fragment1.getTag())
                .commit();
        Fragment2 fragment2= new Fragment2();
        manager.beginTransaction()
                .replace(R.id.layout2,fragment2,fragment2.getTag())
                .commit();
        Fragment3 fragment3= new Fragment3();
        manager.beginTransaction()
                .replace(R.id.layout3,fragment3,fragment3.getTag())
                .commit();
    }



}
