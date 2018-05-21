package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivity;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.model.ExhibitModels;

public class ExhibitCategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ExhibitModels> listExhibit;

    public ExhibitCategoryRecyclerViewAdapter(Context context, List<ExhibitModels> listExhibit) {
        this.context = context;
        this.listExhibit = listExhibit;
    }
    public class DataItem extends RecyclerView.ViewHolder {
        private ImageView imgExhibit;
        private TextView tvExhibitName;
        private TextView tvExhibitDescription;
        private TextView tvExhibitSee;
        private TextView tvExhibitHeart;

        public DataItem(View itemView) {
            super(itemView);
            imgExhibit = (ImageView) itemView.findViewById(R.id.imgExhibit);
            tvExhibitName = (TextView) itemView.findViewById(R.id.tvExhibitName);
            tvExhibitDescription = (TextView) itemView.findViewById(R.id.tvExhibitDescription);
            tvExhibitSee = (TextView) itemView.findViewById(R.id.tvExhibitSee);
            tvExhibitHeart = (TextView) itemView.findViewById(R.id.tvExhibitHeart);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        viewHolder = new ExhibitCategoryRecyclerViewAdapter.DataItem(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ExhibitCategoryRecyclerViewAdapter.DataItem mHolder = (ExhibitCategoryRecyclerViewAdapter.DataItem) holder;
        ExhibitModels item = listExhibit.get(position);
        mHolder.imgExhibit.setImageResource(item.getImage());
        //click image to show detail
        mHolder.imgExhibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mHolder.getAdapterPosition();
                startDetailActivity(position);
            }
        });
        mHolder.tvExhibitName.setText(item.getExhibitName());
        mHolder.tvExhibitDescription.setText(item.getDescription());
        mHolder.tvExhibitSee.setText(item.getExhibitSee());
        mHolder.tvExhibitHeart.setText(item.getExhibitHeart());

    }

    @Override
    public int getItemCount() {
        return listExhibit.size();
    }
    private void startDetailActivity(int position) {
        Intent intent = new Intent(context, ExhibitDetailActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, position);
        context.startActivity(intent);
    }
}
