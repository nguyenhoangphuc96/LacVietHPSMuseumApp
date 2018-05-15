package vn.com.lacviet.lacviethpsmuseummanagementapp.MainScreen;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private ViewPager mViewPager;
    private IntroViewPagerAdapter mPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment1, container, false);


        mViewPager = view.findViewById(R.id.view_pager);
        mDotsLayout = view.findViewById(R.id.layoutDots);
        // layouts of all welcome sliders
        // add few more layouts if you want
        mLayouts = new int[]{
                R.layout.fragment1_slide1,
                R.layout.fragment1_slide2,
                R.layout.fragment1_slide3};

        // adding bottom dots
        addBottomDots(0);


        mPagerAdapter = new IntroViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mViewPagerChangeListener);


        return view;
    }
    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("•"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInActive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }
    public class IntroViewPagerAdapter extends PagerAdapter {
        private LayoutInflater mInflater;

        public IntroViewPagerAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(mLayouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


}

