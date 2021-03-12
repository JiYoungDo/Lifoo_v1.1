package com.example.lifoo_v11.src.MypageFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.xmlClass.RoundImageView;

import java.util.ArrayList;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.MypageViewHolder> {
    Context context;
    ArrayList<MypageItem> mypageItems;

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

    @NonNull
    @Override
    public MypageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypost_item, null);
        return new MypageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MypageViewHolder holder, int position) {
        MypageItem mypageItem = mypageItems.get(position);

        holder.mypostImage.setImageDrawable(mypageItem.getMyPost());
        holder.badgeImage.setImageDrawable(mypageItem.getMyBadge());


    }

    @Override
    public int getItemCount() {
        return this.mypageItems.size();
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
}
