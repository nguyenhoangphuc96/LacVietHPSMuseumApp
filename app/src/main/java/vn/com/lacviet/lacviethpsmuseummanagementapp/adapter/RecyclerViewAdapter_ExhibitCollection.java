package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class RecyclerViewAdapter_ExhibitCollection  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Integer> listImageExhibit;
    public RecyclerViewAdapter_ExhibitCollection(Context context, List<Integer> listImageExhibit)
    {
        this.context=context;
        this.listImageExhibit=listImageExhibit;
    }
    public class DataItem extends RecyclerView.ViewHolder {
        private ImageView imgExhibit;

        public DataItem(View itemView) {
            super(itemView);
            imgExhibit = (ImageView) itemView.findViewById(R.id.imgExhibitCollection);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        viewHolder = new RecyclerViewAdapter_ExhibitCollection.DataItem(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewAdapter_ExhibitCollection.DataItem mHolder = (RecyclerViewAdapter_ExhibitCollection.DataItem) holder;
        int item = listImageExhibit.get(position);
        mHolder.imgExhibit.setImageResource(item);
    }

    @Override
    public int getItemCount() {
        return listImageExhibit.size();
    }
}
