package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;

public class ExhibitCollectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Integer> listImageExhibit;
    public ExhibitCollectionRecyclerViewAdapter(Context context, List<Integer> listImageExhibit)
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
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        viewHolder = new ExhibitCollectionRecyclerViewAdapter.DataItem(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                startDetailActivity(position);
            }
        });

        return viewHolder;
    }

    private void startDetailActivity(int position) {
        Intent intent = new Intent(context, ExhibitDetailActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, position);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExhibitCollectionRecyclerViewAdapter.DataItem mHolder = (ExhibitCollectionRecyclerViewAdapter.DataItem) holder;
        int item = listImageExhibit.get(position);
        mHolder.imgExhibit.setImageResource(item);
    }

    @Override
    public int getItemCount() {
        return listImageExhibit.size();
    }
}
