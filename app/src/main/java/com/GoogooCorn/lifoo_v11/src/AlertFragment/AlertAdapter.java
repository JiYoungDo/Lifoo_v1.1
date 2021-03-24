package com.GoogooCorn.lifoo_v11.src.AlertFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private ArrayList<AlertItem> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    //전달된 객체 저장할 변수
    private AlertAdapter.OnItemClickListener mListener = null;
    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }


    @Override
    public int getItemCount() {
        // mList 비어 있으면 0리턴, 아니면 mList 사이즈 리턴
        return (null != mList ? mList.size():0);
    }



    public class AlertViewHolder extends RecyclerView.ViewHolder {

        RoundImageView Alert_img;
        TextView Alert_txt;
        TextView Alert_time;

        public AlertViewHolder(@NonNull View itemView) {

            super(itemView);

            this.Alert_img = itemView.findViewById(R.id.alert_item_iv);
            Alert_img.setRectRadius(15f);
            this.Alert_txt = itemView.findViewById(R.id.alert_item_tv_reaction_nick_name);
            this.Alert_time = itemView.findViewById(R.id.alert_item_tv_time);


            // 뷰홀더가 만들어지는 시점에 클릭 이벤트 처리
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
    public AlertAdapter(ArrayList<AlertItem> mList) { this.mList = mList; }


    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public AlertAdapter.AlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alert_item,viewGroup,false);
        AlertAdapter. AlertViewHolder viewHolder = new AlertAdapter.AlertViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {

        String Alert_img_url;
        Alert_img_url = mList.get(position).getAlert_img();
        Glide.with(this.context).load(Alert_img_url).into(holder.Alert_img);

        holder.Alert_txt.setText(mList.get(position).getAlert_string());
        holder.Alert_time.setText(mList.get(position).getAlert_hour());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // post idx sharedpreferences에 담아서 postDetail에서 사용
                sSharedPreferences = context.getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove("clicked_post_idx");
                editor.putString("clicked_post_idx", mList.get(position).getAlert_postIdx().toString());

                editor.remove("clicked_imoge_idx");
                editor.putString("clicked_imoge_idx", mList.get(position).getAlert_imoji().toString());

                editor.remove("from_alert_activity");
                editor.putString("from_alert_activity", "yes");

                editor.commit();

                Intent gotoDetailActivity = new Intent(context, PostDetailActivity.class);
                ((MainActivity)context).startActivity(gotoDetailActivity);
            }
        });

    }
}
