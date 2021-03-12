package com.example.lifoo_v11.src.RankingFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.PostDetailActivity.PostDetailActivity;
import com.example.lifoo_v11.src.xmlClass.RoundImageView;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    Context context;
    ArrayList<RankingItem> rankingItems;

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

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, null);
        return new RankingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        RankingItem rankingItem = rankingItems.get(position);

        holder.rankingImage.setImageDrawable(rankingItem.getRankingPost());
        holder.badgeImage.setImageDrawable(rankingItem.getRankingBadge());
        holder.rankingPostTitle.setText(rankingItem.getRankingPostTitle());
        holder.rankingPostTime.setText(rankingItem.getRankingPostTime());
        holder.rankingPostEmoji.setText(rankingItem.getRankingPostEmoji());


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
            this.rankingImage = itemView.findViewById(R.id.ranking_post_image);
            rankingImage.setRectRadius(15f);
            this.badgeImage = itemView.findViewById(R.id.ranking_post_badge);
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

                        Intent intent = new Intent(context, PostDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
        }
    }
}
