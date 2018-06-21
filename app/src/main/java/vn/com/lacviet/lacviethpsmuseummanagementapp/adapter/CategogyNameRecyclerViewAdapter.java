package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;

public class CategogyNameRecyclerViewAdapter extends RecyclerView.Adapter<CategogyNameRecyclerViewAdapter.ViewHolder> {

    private List<StuffModel> StuffList;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTv;

        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tvListMenuCategogy);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            StuffModel item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getSTUFFID());

            notifyDataSetChanged();

        }
    }

    public CategogyNameRecyclerViewAdapter(Context context, List<StuffModel> posts, PostItemListener itemListener) {
        StuffList = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public CategogyNameRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_list_text, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategogyNameRecyclerViewAdapter.ViewHolder holder, int position) {

        StuffModel item = StuffList.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getSTUFFNAME());
    }

    @Override
    public int getItemCount() {
        return StuffList.size();
    }

    public void updateAnswers(List<StuffModel> items) {
        StuffList = items;
        notifyDataSetChanged();
    }

    private StuffModel getItem(int adapterPosition) {
        return StuffList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }

}
