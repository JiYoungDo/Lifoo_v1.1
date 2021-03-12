package com.example.lifoo_v11.src.FeedFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lifoo_v11.R;;
import com.example.lifoo_v11.src.xmlClass.RoundImageView;

import java.util.ArrayList;

public class FeedAdapter  extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private ArrayList<FeedItem> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    //전달된 객체 저장할 변수
    private FeedAdapter.OnItemClickListener mListener = null;

    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(FeedAdapter.OnItemClickListener listener)
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
    public FeedAdapter(ArrayList<FeedItem> mList) { this.mList = mList; }



    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public FeedAdapter.FeedViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.feed_item,viewGroup,false);
        FeedAdapter.FeedViewHolder viewHolder = new FeedAdapter.FeedViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.FeedViewHolder holder, int position) {

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

    }

}
