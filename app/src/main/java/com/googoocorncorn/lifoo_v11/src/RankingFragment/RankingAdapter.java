package com.googoocorncorn.lifoo_v11.src.RankingFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    Context context;
    private ArrayList<RankingItem> rankingItems;

    // 생성자에서 List 객체를 전달
    public RankingAdapter(ArrayList<RankingItem> rankingItems){
        this.rankingItems=rankingItems;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    private RankingAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(RankingAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return this.rankingItems.size();
    }


    public class RankingViewHolder extends RecyclerView.ViewHolder {

        RoundImageView rankingImage;
        ImageView badgeImage;
        TextView rankingPostTitle;
        TextView rankingPostTime;
        TextView rankingPostEmoji;

        public RankingViewHolder(final View itemView) {
            super(itemView);
            this.rankingImage = itemView.findViewById(R.id.my_post_image);
            rankingImage.setRectRadius(15f);
            this.badgeImage = itemView.findViewById(R.id.my_post_badge);
            this.rankingPostTitle = itemView.findViewById(R.id.ranking_post_title);
            this.rankingPostTime = itemView.findViewById(R.id.ranking_post_time);
            this.rankingPostEmoji = itemView.findViewById(R.id.ranking_post_emojicounter);

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

//                        Intent intent = new Intent(context, PostDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ranking_item, viewGroup, false);
        RankingAdapter. RankingViewHolder viewHolder = new RankingAdapter.RankingViewHolder(view);
        this.context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        RankingItem rankingItem = rankingItems.get(position);

        String Alert_img_url;
        Alert_img_url = rankingItems.get(position).getRankingPost();
        Glide.with(this.context).load(Alert_img_url).into(holder.rankingImage);

        holder.badgeImage.setImageDrawable(rankingItem.getRankingBadge());
        holder.rankingPostTitle.setText(rankingItem.getRankingPostTitle());
        holder.rankingPostTime.setText(rankingItem.getRankingPostTime());
        holder.rankingPostEmoji.setText(rankingItem.getRankingPostEmoji() + " 개");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // post idx sharedpreferences에 담아서 postDetail에서 사용
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx", rankingItems.get(position).getRankingPostIdx().toString());

                editor.commit();

                Intent gotoDetailActivity = new Intent(context, PostDetailActivity.class);
                ((MainActivity)context).startActivity(gotoDetailActivity);

            }
        });
   }
}
