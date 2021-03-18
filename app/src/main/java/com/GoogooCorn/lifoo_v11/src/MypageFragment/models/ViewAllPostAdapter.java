package com.GoogooCorn.lifoo_v11.src.MypageFragment.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.xmlClass.RoundImageView;

import java.util.ArrayList;

public class ViewAllPostAdapter extends RecyclerView.Adapter<ViewAllPostAdapter.MypageViewHolder>{
    Context context;
    ArrayList<MypageItem> mypageItems;

    public ViewAllPostAdapter(ArrayList<MypageItem> MypageItems){
        this.mypageItems = MypageItems;
    }

    //커스텀 리스너 인터페이스
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    public interface OnItemLongClickListener
    {
        void onItemLongClick(View v, int pos);
    }

    //리스너 객체를 전달하는 메소드와 전달된 객체를 저장할 변수(mListener) 추가
    private ViewAllPostAdapter.OnItemClickListener mListener = null;
    private ViewAllPostAdapter.OnItemLongClickListener onItemLongClickListener = null;

    public void setOnItemClickListener(ViewAllPostAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(ViewAllPostAdapter.OnItemLongClickListener listener)
    {
        this.onItemLongClickListener = listener;
    }

    @NonNull
    @Override
    public MypageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypost_view_all_item, null);
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
        ImageView btnEdit;
        ImageView btnDelete;

        public MypageViewHolder(final View itemView) {
            super(itemView);
            this.mypostImage = itemView.findViewById(R.id.view_all_my_post_image);
            mypostImage.setRectRadius(15f);
            this.badgeImage = itemView.findViewById(R.id.view_all_my_post_badge);
            this.btnEdit = itemView.findViewById(R.id.btn_contect_edit);
            this.btnDelete = itemView.findViewById(R.id.btn_content_delete);


//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//                        // click event
////                        v.findViewById(R.id.view_all_mypost_background).setBackgroundColor(Color.parseColor("#99000000"));
//                        btnEdit.setVisibility(View.VISIBLE);
//                        btnDelete.setVisibility(View.VISIBLE);
//                    }
//                    return true;
//                }
//            });

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

            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        // click event
                        onItemLongClickListener.onItemLongClick(v,pos);

                    }
                    return true;
                }
            });

        }
    }
}
