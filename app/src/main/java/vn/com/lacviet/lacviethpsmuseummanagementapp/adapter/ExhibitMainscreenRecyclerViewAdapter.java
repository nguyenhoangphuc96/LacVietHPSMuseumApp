package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenWithImageModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class ExhibitMainscreenRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitMainscreenRecyclerViewAdapter.ViewHolder> {

    private List<ExhibitMainScreenWithImageModel> ExhibitList;
    private Context mContext;
    private PostItemListener mItemListener;
    private int id;
    //web api
    private ApiService mService;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvName, tvDescription;
        public ImageView imvExhibit;
        private ProgressBar progressBar;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvExhibitName);
            tvDescription = itemView.findViewById(R.id.tvExhibitDescription);
            imvExhibit = itemView.findViewById(R.id.imgExhibit);
            progressBar = itemView.findViewById(R.id.pbItemMainScreen);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ExhibitMainScreenWithImageModel item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.geteXHID());

            /*notifyDataSetChanged();*/
        }
    }

    public ExhibitMainscreenRecyclerViewAdapter(Context context, List<ExhibitMainScreenWithImageModel> posts, PostItemListener itemListener) {
        ExhibitList = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ExhibitMainscreenRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_recyclerview_mainscreen, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        //web api
        mService = ApiUtils.getSOService();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExhibitMainscreenRecyclerViewAdapter.ViewHolder holder, int position) {

        ExhibitMainScreenWithImageModel item = ExhibitList.get(position);
        holder.tvName.setText(item.geteXHIBITNAME());
        holder.tvDescription.setText(item.getdESCRIPTION());


       /* //get ID and load image by id
        id = item.getEXHID();
        loadImage(id,holder);*/

        if (item.geteIMAGE() != null) {
            holder.progressBar.setVisibility(View.GONE);
            showImage(item.geteIMAGE(), holder);
        } else {
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.imvExhibit.setImageDrawable(null);
        }
        if(item.geteIMAGE()==""){
            holder.progressBar.setVisibility(View.GONE);
            holder.imvExhibit.setImageDrawable(mContext.getResources().getDrawable(R.drawable.img_no_image));
        }
    }


    @Override
    public int getItemCount() {
        return ExhibitList.size();
    }

    public void updateAnswers(List<ExhibitMainScreenWithImageModel> items) {
        ExhibitList = items;
        notifyDataSetChanged();
    }


    public void updateMoreAnswers(List<ExhibitMainScreenWithImageModel> items) {
        ExhibitList.addAll(items);
        notifyDataSetChanged();
    }

    private ExhibitMainScreenWithImageModel getItem(int adapterPosition) {
        return ExhibitList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }

    public List<ExhibitMainScreenWithImageModel> getTestList() {
        return ExhibitList;
    }

    private void showImage(String imageString, ViewHolder holder) {
        try {
            Bitmap bmp = Util.StringToBitMap(imageString);
            holder.imvExhibit.setImageBitmap(bmp);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void showErrorMessage() {
        Toast.makeText(mContext, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

}