package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertAdapter;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertItem;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;


public class PostDetailActivity extends AppCompatActivity implements PostDetailActivityView {

    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    Button back_btn , more_btn , share_btn , comments_btn;
    TextView title_nick, reaction_count , post_nick, post_title, post_contents, post_time;
    ImageView post_iv;
    ImageView imoji_cheer_up, imoji_congratuation, imoji_don,
            imoji_ewww, imoji_fire, imoji_heart,
            imoji_question, imoji_sad_happy, imoji_wow;


    RecyclerView comments_recyclerview;
    ArrayList comment_list;
    CommentAdapter commentAdapter;

    EditText comment_body_et;
    Button comment_post_btn;

    LottieAnimationView lottieAnimationView;

    // 클릭된 포스트 인덱스 , 클릭된 이모지 인덱스
    String get_post_idx , clicked_imoge_idx;

    int get_imoge_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        /**
         * 앞선 feedfragment 에서 아이템 클릭시,
         * post_idx 를 sharedPreference에 저장 해놓은 것을 가져온다.
         * */

        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");

        int to = Integer.parseInt(get_post_idx); // to 잘 들어옴 .
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        comments_recyclerview.setLayoutManager(linearLayoutManager);

        comment_list = new ArrayList<CommentItem>();

        commentAdapter = new CommentAdapter(comment_list);
        comments_recyclerview.setAdapter(commentAdapter);

        commentAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
            }
        });

        commentAdapter.notifyDataSetChanged();



        // 댓글 게시
        comment_body_et = findViewById(R.id.post_detail_et_post_comments);
        comment_post_btn = findViewById(R.id.post_detail_btn_post_comments);
        comment_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TryPostComments(get_post_idx,comment_body_et.getText().toString());

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
        // postDetailActivityService.postComments(postIdx,postBody);
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
        },3000);
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
                String comment_nick_name = getCommentResponse.getResult().getCommentLists().get(i).getCommentNickname();
                String comment_body = getCommentResponse.getResult().getCommentLists().get(i).getCommentBody();
                int comment_like_count = getCommentResponse.getResult().getCommentLists().get(i).getLikeNum();
                CommentItem commentItem = new CommentItem(comment_nick_name,comment_body,comment_like_count);

                comment_list.add(commentItem);

            }

        }

        commentAdapter.notifyDataSetChanged();



    }

    @Override
    public void PostCommentsFailure(String message, int code) {
        Log.d("포스트 게시 실패" , message+String.valueOf(code));


    }

    @Override
    public void PostCommentsSuccess(String message, int code) {
        Log.d("포스트 게시 성공" , message+String.valueOf(code));
        TryGetComments(Integer.parseInt(get_post_idx));
    }


}