package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class InfinityCycleImageViewAdapter extends PagerAdapter {

    List<Bitmap> lstImages;
    Context context;
    LayoutInflater layoutInflater;

    public InfinityCycleImageViewAdapter(List<Bitmap> lstImages, Context context) {
        this.lstImages = lstImages;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.card_item_image,container,false);
        ImageView imageView = view.findViewById(R.id.imgExhibitInfinityView);
        imageView.setImageBitmap(lstImages.get(position));
        container.addView(view);
        return view;
    }
}