package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerAdapterImageDetail extends PagerAdapter {
    private LayoutInflater mInflater;
    private int mLayouts[];
    private Context mContext;

    public PagerAdapterImageDetail(Context context, int[] layouts) {
        super();
        mContext = context;
        mLayouts = layouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
