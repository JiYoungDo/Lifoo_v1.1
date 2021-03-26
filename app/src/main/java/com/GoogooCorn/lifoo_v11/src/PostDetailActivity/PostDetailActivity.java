package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertAdapter;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertItem;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.CommentsActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;
import com.airbnb.lottie.LottieAnimationView;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;


public class PostDetailActivity extends AppCompatActivity implements PostDetailActivityView, CommentsActivityView {

    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    Button back_btn , more_btn , share_btn , comments_btn;
    TextView title_nick, reaction_count , post_nick, post_title, post_contents, post_time;
    ImageView post_iv;
    ImageView imoji_cheer_up, imoji_congratuation, imoji_don,
            imoji_ewww, imoji_fire, imoji_heart,
            imoji_question, imoji_sad_happy, imoji_wow;

    CommentsService commentsService = new CommentsService(this);

//     RecyclerView comments_recyclerview;
    SwipeMenuListView comments_recyclerview;

    ArrayList<CommentItem> comment_list;
    private AppAdapter appAdapter;
    CommentAdapter commentAdapter;

    EditText comment_body_et;
    Button comment_post_btn;

    LottieAnimationView lottieAnimationView;

    public static Context mContext;

    // 클릭된 포스트 인덱스 , 클릭된 이모지 인덱스
    String get_post_idx , clicked_imoge_idx , clicked_comment_idx;

    int get_imoge_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        mContext = this;

        /**
         * 앞선 feedfragment 에서 아이템 클릭시,
         * post_idx 를 sharedPreference에 저장 해놓은 것을 가져온다.
         * */

        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");

        int to = Integer.parseInt(get_post_idx); // to 잘 들어옴 .

        comment_list = new ArrayList<CommentItem>();

        TryPostDetail(to); // 상세 포스트 조회
        TryGetComments(to); // 댓글 조회


        back_btn = findViewById(R.id.post_detail_btnBack);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 상단
        more_btn = findViewById(R.id.post_detail_more_btn);
        share_btn = findViewById(R.id.post_detail_btnShare);
        comments_btn = findViewById(R.id.post_detail_comments_btn);


        // 포스트 바로 아래
        title_nick = findViewById(R.id.post_detail_tv_title_nick);
        reaction_count = findViewById(R.id.post_detail_tv_reaction_count);

        lottieAnimationView = findViewById(R.id.post_detail_lottie);

        // 이모지
        imoji_cheer_up = findViewById(R.id.post_detail_iv_cheer_up_imoji);
        imoji_cheer_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "3";
                Log.d("이모지 클", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_cheer_up.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_congratuation = findViewById(R.id.post_detail_iv_congratuation_imoji);
        imoji_congratuation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "6";
                Log.d("이모지 클", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_congratuation.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_don = findViewById(R.id.post_detail_iv_don_imoji);
        imoji_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "4";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_don.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_ewww = findViewById(R.id.post_detail_iv_ewww_imoji);
        imoji_ewww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "7";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_ewww.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_fire = findViewById(R.id.post_detail_iv_fire_imoji);
        imoji_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "8";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_fire.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_heart = findViewById(R.id.post_detail_iv_heart_imoji);
        imoji_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "1";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_heart.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_question = findViewById(R.id.post_detail_iv_question_imoji);
        imoji_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "5";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_question.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_sad_happy = findViewById(R.id.post_detail_iv_sad_happy_imoji);
        imoji_sad_happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "9";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_sad_happy.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        imoji_wow = findViewById(R.id.post_detail_iv_wow_imoji);
        imoji_wow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_imoge_idx = "2";
                Log.d("이모지 클릭", get_post_idx+"&&"+clicked_imoge_idx);
                lottieAnimationView.setAnimation("imoji_wow.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                TryPostImoge(get_post_idx, clicked_imoge_idx);
            }
        });

        // 포스트 정보
        post_iv = findViewById(R.id.post_detail_iv_postImage);
        post_nick = findViewById(R.id.post_detail_writer_nickname_2);
        post_title = findViewById(R.id.post_detail_title);
        post_contents = findViewById(R.id.post_detail_contents);
        post_time = findViewById(R.id.post_detail_time);

        comments_recyclerview = findViewById(R.id.post_detail_comment_rv);
        appAdapter = new AppAdapter(comment_list);
        comments_recyclerview.setAdapter(appAdapter);



        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                //"edit" item
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setBackground(R.color.colorLightGray);
                editItem.setWidth(dp2px(60));
                editItem.setIcon(R.drawable.edit_icon);
                menu.addMenuItem(editItem);

                //"delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(R.color.colorRed);
                deleteItem.setWidth(dp2px(60));
                deleteItem.setIcon(R.drawable.delete_icon);
                menu.addMenuItem(deleteItem);
            }
        };

        comments_recyclerview.setMenuCreator(creator);

        comments_recyclerview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch(index)
                {
                    case 0:
                        //edit
                        break;

                    case 1:
                        //delete
                        AlertDialog.Builder builder = new AlertDialog.Builder(PostDetailActivity.this);

                        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 삭제 작업
                                Log.d("포지션 값", String.valueOf(position));
                                TryDelCommentTest(comment_list.get(position).getComment_idx());
                            }
                        });
                        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.setMessage("해당 댓글을 정말 삭제하시겠어요?");
                        builder.setTitle("삭제 알림창");
                        builder.show();
                        break;
                }
                return false;
            }
        });

        comments_recyclerview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                //swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        comments_recyclerview.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });



        // 댓글 게시
        comment_body_et = findViewById(R.id.post_detail_et_post_comments);
        comment_post_btn = findViewById(R.id.post_detail_btn_post_comments);
        comment_post_btn.bringToFront();

        comment_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TryPostComments(get_post_idx,comment_body_et.getText().toString());
                comment_body_et.setText("");

            }
        });

    }



    private void TryPostDetail(int postIdx)
    {
        postDetailActivityService.getDetailPost(postIdx);
    }

    private void TryPostImoge(String postIdx, String ImogeIdx) {postDetailActivityService.postImoge(postIdx,ImogeIdx);}

    private void TryGetComments(int postIdx)
    {
        postDetailActivityService.getComments(postIdx);
    }

    private void TryPostComments(String postIdx,String postBody)
    {
        postDetailActivityService.postComments(postIdx,postBody);
    }



    @Override
    public void GetPostDetailFailure(String message, int code) {
        Log.d("POST 상세 조회 실패!" , message + " " +String.valueOf(code));

    }
    @Override
    public void GetPostDetailSuccess(GetPostResponse getPostResponse, int code) {
        Log.d("POST 상세 조회 성공!" , " " +String.valueOf(code));

        String get_host_name , get_post_title, get_post_contents , get_post_time , lottie_file_name, lottie_file_name_2;
        String get_img_url;
        int get_best_count;

        get_host_name = getPostResponse.getResult().getHostNickname();
        get_post_title = getPostResponse.getResult().getPostTitle();
        get_post_contents = getPostResponse.getResult().getPostBody();
        get_imoge_count = getPostResponse.getResult().getTotalImoge();
        get_post_time = getPostResponse.getResult().getCreatedAt();
        get_img_url = getPostResponse.getResult().getPostUrl();

        Glide.with(this).load(get_img_url).into(post_iv);

        get_best_count = getPostResponse.getResult().getMostImoge();


        title_nick.setText(get_host_name);
        reaction_count.setText(String.valueOf(get_imoge_count)+"명이 리액션을 보냈습니다.");
        post_nick.setText(get_host_name);
        post_title.setText(get_post_title);
        post_contents.setText(get_post_contents);
        post_time.setText(get_post_time.substring(2,10));


        Log.d("가장 많이 눌린 이모지 뭔데?", String.valueOf(get_best_count));
        Log.d(" 이모지 총 개수 몇갠데?", String.valueOf(get_imoge_count));


        if(get_imoge_count > 50)
        {
            lottieAnimationView.setAnimation("imoji_1000.json");
            lottieAnimationView.loop(false);
            lottieAnimationView.playAnimation();

        }else if (get_imoge_count >25)
        {
            lottieAnimationView.setAnimation("imoji_500.json");
            lottieAnimationView.loop(false);
            lottieAnimationView.playAnimation();

        }else if (get_imoge_count > 12)
        {
            lottieAnimationView.setAnimation("imoji_100.json");
            lottieAnimationView.loop(false);
            lottieAnimationView.playAnimation();
        }else
        {
            lottieAnimationView.setAnimation("imoji_50.json");
            lottieAnimationView.loop(false);
            lottieAnimationView.playAnimation();
        }




    }
    @Override
    public void EditPostFailure(String message, int code) {

    }
    @Override
    public void EditPostSuccess(GetPostResponse getPostResponse, int code) {

    }
    @Override
    public void DeletePostFailure(String message, int code) {

    }
    @Override
    public void DeletePostSuccess(PostDeleteResponse postDeleteResponse, int code) {

    }
    @Override
    public void PostImogeFailure(String message, int code) {
        Log.d("이모지 포스트 실패", message+String.valueOf(code));
    }
    @Override
    public void PostImogeSuccess(String message, int code) {
        Log.d("이모지 포스트 성공", message+String.valueOf(code));


        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                TryPostDetail(Integer.parseInt(get_post_idx));
            }
        },2500);
    }


    @Override
    public void GetCommentsFailure(String message, int code) {
        Log.d("댓글 조회 성공", message+"&&"+String.valueOf(code));
    }

    @Override
    public void GetCommentsSuccess(GetCommentResponse getCommentResponse, int code) {

        Log.d("댓글 조회 성공", getCommentResponse.getMessage()+"&&"+String.valueOf(code));


        if(!(code == 3210))
        {
            int list_size = getCommentResponse.getResult().getCommentLists().size();
            for(int i = 0; i<list_size; i++)
            {
                String comment_nick_name = getCommentResponse.getResult().getCommentLists().get(list_size - (i+1)).getCommentNickname();
                String comment_body = getCommentResponse.getResult().getCommentLists().get(list_size - (i+1)).getCommentBody();
                int comment_like_count = getCommentResponse.getResult().getCommentLists().get(list_size - (i+1)).getLikeNum();
                String comment_idx = String.valueOf(getCommentResponse.getResult().getCommentLists().get(list_size - (i+1)).getCommentIdx());
                String is_clicked = String.valueOf(getCommentResponse.getResult().getCommentLists().get(list_size-((i+1))).getIsLikeClicked());
                CommentItem commentItem = new CommentItem(comment_nick_name,comment_body,comment_like_count,comment_idx,is_clicked);

                comment_list.add(commentItem);
            }

            appAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void PostCommentsFailure(String message, int code) {
        Log.d("포스트 게시 실패" , message+String.valueOf(code));


    }

    @Override
    public void PostCommentsSuccess(String message, int code) {
        Log.d("포스트 게시 성공" , message+String.valueOf(code));
        // 댓글 보냈으니까 화면 갱신.
        comment_list.clear();
        appAdapter.notifyDataSetChanged();
        TryGetComments(Integer.parseInt(get_post_idx));

    }

    class AppAdapter extends BaseAdapter
    {

        private ArrayList<CommentItem> mList;

        public AppAdapter(ArrayList<CommentItem> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() { return mList.size();}

        @Override
        public Object getItem(int position) { return mList.get(position);}

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.comment_view,null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder)convertView.getTag();
            CommentItem item =(CommentItem) getItem(position);


            int like_count = mList.get(position).getComment_like_count();
            holder.Comment_nick_name.setText(mList.get(position).getComment_nick_name());
            holder.Comment_contents.setText(mList.get(position).getComment_body());
            holder.Comment_like_count.setText("좋아요 "+String.valueOf(like_count));

            if(mList.get(position).getIs_clicked().equals("N"))
            {
                holder.Comment_like_btn.setImageResource(R.drawable.heart_not_filled);
            }else if(comment_list.get(position).getIs_clicked().equals("Y")){
                holder.Comment_like_btn.setImageResource(R.drawable.heart_filled);
            }else {
            }

            // 좋아요
            holder.Comment_like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** 댓글 좋아요 api */
                    if(mList.get(position).getIs_clicked().equals("N"))
                    {
                        holder.Comment_like_btn.setImageResource(R.drawable.heart_filled);
                        holder.Comment_like_count.setText("좋아요 "+ String.valueOf(like_count+1));
                        TryPostCommetLikeTest(mList.get(position).getComment_idx());

                    }else if(mList.get(position).getIs_clicked().equals("Y")){

                        holder.Comment_like_btn.setImageResource(R.drawable.heart_not_filled);
                        holder.Comment_like_count.setText("좋아요 "+String.valueOf(like_count-1));
                        TryPostCommetLikeTest(mList.get(position).getComment_idx());
                    }else{
                    }

                }
            });


            // 신고하기
            holder.Comment_declare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** 댓글 신고 api */

                }
            });


            // 댓글 달기
            holder.Comment_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** 댓글 달기 api */
                }
            });

            return convertView;
        }

        class ViewHolder
        {
            TextView Comment_nick_name;
            TextView Comment_contents;
            ImageButton Comment_like_btn;
            Button Comment_declare;
            Button Comment_comment;
            TextView Comment_like_count;

            public ViewHolder(View view)
            {
                Comment_nick_name = (TextView)view.findViewById(R.id.comment_view_writer_nickname);
                Comment_contents = (TextView)view.findViewById(R.id.comment_view_content);
                Comment_like_btn = (ImageButton)view.findViewById(R.id.comment_view_iv_btnLike);
                Comment_declare = (Button)view.findViewById(R.id.comment_view_btnReport);
                Comment_comment = (Button)view.findViewById(R.id.comment_view_btnAddComment);
                Comment_like_count = (TextView)view.findViewById(R.id.comment_view_like_count);

                view.setTag(this);
            }
        }

        public boolean getSwipeEnableByPosition(int position)
        {
            if(position % 2 == 0)
            {
                return false;
            }
            return true;
        }
    }

    private int dp2px(int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.action_left)
        {
            comments_recyclerview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            return true;
        }
        if(id == R.id.action_right)
        {
            comments_recyclerview.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void TryPostCommetLikeTest(String commentIdx)
    {
        commentsService.postLikes(commentIdx);
    }

    @Override
    public void PostCommentsLikesFailure(String message, int code) {
        Log.d("댓글 좋아요 실패", message+"&&"+String.valueOf(code));
    }

    @Override
    public void PostCommentsLikesSuccess(String message, int code) {
        Log.d("댓글 좋아요 성공", message+"&&"+String.valueOf(code));
    }


    private void TryDelCommentTest(String commentIdx)
    {
        commentsService.delComment(commentIdx);
    }

    @Override
    public void DeleteCommentsFailure(String message, int code) {
        Log.d("댓글 삭제 실", message+"&&"+String.valueOf(code));
    }

    @Override
    public void DeleteCommentsSuccess(String message, int code) {
        Log.d("댓글 삭패제 성", message+"&&"+String.valueOf(code));
        TryGetComments(Integer.parseInt(get_post_idx));
    }


}