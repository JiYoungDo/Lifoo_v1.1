package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.GoogooCorn.lifoo_v11.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{


    private ArrayList<CommentItem> mList;
    Context context;

    // 리스너 인터페이스 정의하기
    public interface OnItemClickListener
    {
    void onItemClick(View v, int pos);
    }


    //전달된 객체 저장할 변수
    private CommentAdapter.OnItemClickListener mListener = null;

    // 리스너 객체 전달 메서드
    public void setOnItemClickListener(CommentAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }


    @Override
    public int getItemCount() {
        // mList 비어 있으면 0리턴, 아니면 mList 사이즈 리턴
        return (null != mList ? mList.size():0);
    }



    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView Comment_nick_name;
        TextView Comment_contents;
        Button Comment_like_btn;
        Button Comment_declare;
        Button Comment_comment;
        TextView Comment_like_count;


        public CommentViewHolder(@NonNull View itemView) {

            super(itemView);

            this.Comment_nick_name = itemView.findViewById(R.id.comment_view_writer_nickname);
            this.Comment_contents = itemView.findViewById(R.id.comment_view_content);
            this.Comment_like_btn = itemView.findViewById(R.id.comment_view_btnLike);
            this.Comment_declare = itemView.findViewById(R.id.comment_view_btnReport);
            this.Comment_comment = itemView.findViewById(R.id.comment_view_btnAddComment);
            this.Comment_like_count = itemView.findViewById(R.id.comment_view_like_count);


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
    public void CommentViewHolder(ArrayList<CommentItem> mList) { this.mList = mList; }



    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public CommentAdapter.CommentViewHolder  onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_view,viewGroup,false);
        CommentAdapter. CommentViewHolder viewHolder = new CommentAdapter.CommentViewHolder(view);
        this.context =viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {

       int like_count = mList.get(position).getComment_like_count();


        holder.Comment_nick_name.setText(mList.get(position).getComment_nick_name());
        holder.Comment_contents.setText(mList.get(position).getComment_body());
        holder.Comment_like_count.setText("좋아요 "+String.valueOf(like_count));

        // 좋아요
        holder.Comment_like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // 신고하기
        holder.Comment_declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // 댓글 달기
        holder.Comment_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }










}
