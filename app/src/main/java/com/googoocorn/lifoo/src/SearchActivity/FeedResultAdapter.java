package com.googoocorn.lifoo.src.SearchActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.FeedFragment.FeedItem;
import com.googoocorn.lifoo.src.PostDetailActivity.PostDetailActivity;
import com.googoocorn.lifoo.src.xmlClass.RoundImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

;

public class FeedResultAdapter extends RecyclerView.Adapter<FeedResultAdapter.FeedViewHolder> {

    private ArrayList<FeedItem> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    //전달된 객체 저장할 변수
    private FeedResultAdapter.OnItemClickListener mListener = null;

    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(FeedResultAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }


    @Override
    public int getItemCount() {
        // mList 비어 있으면 0리턴, 아니면 mList 사이즈 리턴
        return (null != mList ? mList.size():0);
    }



    public class FeedViewHolder extends RecyclerView.ViewHolder {

        RoundImageView Feed_img_1,Feed_img_2,Feed_img_3,Feed_img_4,Feed_img_5,Feed_img_6 ;
        TextView Feed_hour_1,Feed_hour_2,Feed_hour_3,Feed_hour_4,Feed_hour_5,Feed_hour_6;
        TextView Feed_imoji_1, Feed_imoji_2, Feed_imoji_3, Feed_imoji_4, Feed_imoji_5, Feed_imoji_6;
        TextView Feed_title_1, Feed_title_2, Feed_title_3,Feed_title_4,Feed_title_5,Feed_title_6;

        public FeedViewHolder(@NonNull View itemView) {

            super(itemView);

            this.Feed_img_1 = itemView.findViewById(R.id.feed_item_1);
            Feed_img_1.setRectRadius(15f);
            this.Feed_img_2 = itemView.findViewById(R.id.feed_item_2);
            Feed_img_2.setRectRadius(15f);
            this.Feed_img_3 = itemView.findViewById(R.id.feed_item_3);
            Feed_img_3.setRectRadius(15f);
            this.Feed_img_4 = itemView.findViewById(R.id.feed_item_4);
            Feed_img_4.setRectRadius(15f);
            this.Feed_img_5 = itemView.findViewById(R.id.feed_item_5);
            Feed_img_5.setRectRadius(15f);
            this.Feed_img_6 = itemView.findViewById(R.id.feed_item_6);
            Feed_img_6.setRectRadius(15f);


            this.Feed_hour_1 = itemView.findViewById(R.id.feed_hour_1);
            this.Feed_hour_2 = itemView.findViewById(R.id.feed_hour_2);
            this.Feed_hour_3 = itemView.findViewById(R.id.feed_hour_3);
            this.Feed_hour_4 = itemView.findViewById(R.id.feed_hour_4);
            this.Feed_hour_5 = itemView.findViewById(R.id.feed_hour_5);
            this.Feed_hour_6 = itemView.findViewById(R.id.feed_hour_6);

            this.Feed_imoji_1 = itemView.findViewById(R.id.feed_imoji_1);
            this.Feed_imoji_2 = itemView.findViewById(R.id.feed_imoji_2);
            this.Feed_imoji_3 = itemView.findViewById(R.id.feed_imoji_3);
            this.Feed_imoji_4 = itemView.findViewById(R.id.feed_imoji_4);
            this.Feed_imoji_5 = itemView.findViewById(R.id.feed_imoji_5);
            this.Feed_imoji_6 = itemView.findViewById(R.id.feed_imoji_6);

            this.Feed_title_1 = itemView.findViewById(R.id.feed_title_1);
            this.Feed_title_2 = itemView.findViewById(R.id.feed_title_2);
            this.Feed_title_3 = itemView.findViewById(R.id.feed_title_3);
            this.Feed_title_4 = itemView.findViewById(R.id.feed_title_4);
            this.Feed_title_5 = itemView.findViewById(R.id.feed_title_5);
            this.Feed_title_6 = itemView.findViewById(R.id.feed_title_6);




            // 뷰홀더가 만들어지는 시점에 클릭 이벤트 처리리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(v,pos);
                    }
                }
            });

        }
    }



    // 생성자에서 List 객체를 전달
    public FeedResultAdapter(ArrayList<FeedItem> mList) { this.mList = mList; }



    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public FeedResultAdapter.FeedViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.feed_item,viewGroup,false);
        FeedResultAdapter.FeedViewHolder viewHolder = new FeedResultAdapter.FeedViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedResultAdapter.FeedViewHolder holder, int position) {

        String url_1, url_2, url_3, url_4, url_5, url_6;
        url_1 = mList.get(position).getImg_url_1();
        url_2 = mList.get(position).getImg_url_2();
        url_3 = mList.get(position).getImg_url_3();
        url_4 = mList.get(position).getImg_url_4();
        url_5 = mList.get(position).getImg_url_5();
        url_6 = mList.get(position).getImg_url_6();

        Glide.with(this.context).load(url_1).into(holder.Feed_img_1);
        Glide.with(this.context).load(url_2).into(holder.Feed_img_2);
        Glide.with(this.context).load(url_3).into(holder.Feed_img_3);
        Glide.with(this.context).load(url_4).into(holder.Feed_img_4);
        Glide.with(this.context).load(url_5).into(holder.Feed_img_5);
        Glide.with(this.context).load(url_6).into(holder.Feed_img_6);


        holder.Feed_hour_1.setText(mList.get(position).getHour_1());
        holder.Feed_hour_2.setText(mList.get(position).getHour_2());
        holder.Feed_hour_3.setText(mList.get(position).getHour_3());
        holder.Feed_hour_4.setText(mList.get(position).getHour_4());
        holder.Feed_hour_5.setText(mList.get(position).getHour_5());
        holder.Feed_hour_6.setText(mList.get(position).getHour_6());


        holder.Feed_imoji_1.setText(mList.get(position).getImoji_1());
        holder.Feed_imoji_2.setText(mList.get(position).getImoji_2());
        holder.Feed_imoji_3.setText(mList.get(position).getImoji_3());
        holder.Feed_imoji_4.setText(mList.get(position).getImoji_4());
        holder.Feed_imoji_5.setText(mList.get(position).getImoji_5());
        holder.Feed_imoji_6.setText(mList.get(position).getImoji_6());


        holder.Feed_title_1.setText(mList.get(position).getTitle_1());
        holder.Feed_title_2.setText(mList.get(position).getTitle_2());
        holder.Feed_title_3.setText(mList.get(position).getTitle_3());
        holder.Feed_title_4.setText(mList.get(position).getTitle_4());
        holder.Feed_title_5.setText(mList.get(position).getTitle_5());
        holder.Feed_title_6.setText(mList.get(position).getTitle_6());

        holder.Feed_img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_1());
                editor.commit();
                Log.d("클릭","1");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);


            }
        });

        holder.Feed_img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_2());
                editor.commit();
                Log.d("클릭","2");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);
            }
        });

        holder.Feed_img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);

                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_3());
                editor.commit();
                Log.d("클릭","3");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);
            }
        });

        holder.Feed_img_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_4());
                editor.commit();
                Log.d("클릭","4");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);
            }
        });

        holder.Feed_img_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_5());
                editor.commit();
                Log.d("클릭","5");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);
            }
        });

        holder.Feed_img_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx",mList.get(position).getImoji_idx_6());
                editor.commit();
                Log.d("클릭","6");

                Intent gotoDetailActivity = new Intent(context,PostDetailActivity.class);
                ((SearchActivity)context).startActivity(gotoDetailActivity);
            }
        });


    }

}
