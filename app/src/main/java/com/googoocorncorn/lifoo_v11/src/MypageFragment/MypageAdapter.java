package com.googoocorncorn.lifoo_v11.src.MypageFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.googoocorncorn.lifoo_v11.R;
import com.googoocorncorn.lifoo_v11.src.MainActivity.MainActivity;
import com.googoocorncorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;
import com.googoocorncorn.lifoo_v11.src.xmlClass.RoundImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.googoocorncorn.lifoo_v11.ApplicationClass.TAG;
import static com.googoocorncorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.MypageViewHolder> {

    private ArrayList<MypageItem> mypageItems;
    Context context;

    public MypageAdapter(ArrayList<MypageItem> MypageItems){
        this.mypageItems = MypageItems;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    private MypageAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(MypageAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return (null != mypageItems ? mypageItems.size():0);
    }

    public class MypageViewHolder extends RecyclerView.ViewHolder {
        RoundImageView mypostImage;
        ImageView badgeImage;

        public MypageViewHolder(final View itemView) {
            super(itemView);
            this.mypostImage = itemView.findViewById(R.id.my_post_image);
            mypostImage.setRectRadius(15f);
            this.badgeImage = itemView.findViewById(R.id.my_post_badge);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        // click event
                        mListener.onItemClick(v,pos);

                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MypageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mypost_item, null);
        context = v.getContext();
        return new MypageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MypageViewHolder holder, int position) {
        MypageItem mypageItem = mypageItems.get(position);

        String post_img_url;
        post_img_url = mypageItems.get(position).getMyPost();
        Glide.with(this.context).load(post_img_url).into(holder.mypostImage);

        holder.badgeImage.setImageDrawable(mypageItem.getMyBadge());

        holder.mypostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // post idx sharedpreferences에 담아서 postDetail에서 사용
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mypageItems.get(position).getPostIdx().toString());
                editor.commit();

                Intent gotoDetailActivity = new Intent(context, PostDetailActivity.class);
                ((MainActivity)context).startActivity(gotoDetailActivity);
            }
        });
    }

}

