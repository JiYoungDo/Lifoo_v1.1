package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;
import com.GoogooCorn.lifoo_v11.src.xmlClass.RoundImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;

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

        holder.btnDelete.setVisibility(View.GONE);
        holder.btnEdit.setVisibility(View.GONE);

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
                context.startActivity(gotoDetailActivity);
            }
        });
        holder.mypostImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // post idx sharedpreferences에 담아서 editPost와 deletePost에서 사용
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mypageItems.get(position).getPostIdx().toString());
                editor.commit();

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 해당 게시물 삭제
                        Toast.makeText(context, "삭제 클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.btnEdit.setVisibility(View.VISIBLE);
                holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 해당 게시물 편짐
                        // 해당 게시물 삭제
                        Toast.makeText(context, "편집 클릭", Toast.LENGTH_SHORT).show();
                    }
                });

//                ViewAllPostDialog viewAllPostDialog = new ViewAllPostDialog(context);
//                viewAllPostDialog.show();

                return true;
            }
        });


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

