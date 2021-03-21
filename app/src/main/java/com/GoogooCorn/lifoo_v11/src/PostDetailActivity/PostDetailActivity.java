package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

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

    LottieAnimationView lottieAnimationView;

    int get_imoge_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        /**
         * 앞선 feedfragment 에서 아이템 클릭시,
         * post_idx 를 sharedPreference에 저장 해놓은 것을 가져온다.
         * */
        String get_post_idx;
        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");

        int to = Integer.parseInt(get_post_idx); // to 잘 들어옴 .

        TryPostDetail(to);


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



        // 이모지
        imoji_cheer_up = findViewById(R.id.post_detail_iv_cheer_up_imoji);
        imoji_congratuation = findViewById(R.id.post_detail_iv_congratuation_imoji);
        imoji_don = findViewById(R.id.post_detail_iv_don_imoji);
        imoji_ewww = findViewById(R.id.post_detail_iv_ewww_imoji);
        imoji_fire = findViewById(R.id.post_detail_iv_fire_imoji);
        imoji_heart = findViewById(R.id.post_detail_iv_heart_imoji);
        imoji_question = findViewById(R.id.post_detail_iv_question_imoji);
        imoji_sad_happy = findViewById(R.id.post_detail_iv_sad_happy_imoji);
        imoji_wow = findViewById(R.id.post_detail_iv_wow_imoji);

        // 포스트 정보
        post_iv = findViewById(R.id.post_detail_iv_postImage);
        post_nick = findViewById(R.id.post_detail_writer_nickname_2);
        post_title = findViewById(R.id.post_detail_title);
        post_contents = findViewById(R.id.post_detail_contents);
        post_time = findViewById(R.id.post_detail_time);


        comments_recyclerview = findViewById(R.id.post_detail_comment_rv);

        lottieAnimationView = findViewById(R.id.post_detail_lottie);



    }



    private void TryPostDetail(int postIdx)
    {
        postDetailActivityService.getDetailPost(postIdx);
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

        switch (get_best_count)
        {
            case 1:
                lottie_file_name = "imoji_heart.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();

                break;
            case 2:
                lottie_file_name = "imoji_wow.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 3:
                lottie_file_name = "imoji_cheer_up.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 4:
                lottie_file_name = "imoji_don.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 5:
                lottie_file_name = "imoji_question.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 6:
                lottie_file_name = "imoji_congratuation.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 7:
                lottie_file_name = "imoji_ewww.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 8:
                lottie_file_name = "imoji_fire.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
            case 9:
                lottie_file_name = "imoji_sad_happy.json";
                lottieAnimationView.setAnimation(lottie_file_name);
                lottieAnimationView.loop(false);
                lottieAnimationView.playAnimation();
                break;
        }

//        if(get_imoge_count>50)
//        {
//            lottie_file_name_2 = "imoji_1000.json";
//            lottieAnimationView.setAnimation(lottie_file_name_2);
//            lottieAnimationView.loop(false);
//            lottieAnimationView.playAnimation();
//
//        }else if (get_imoge_count >25)
//        {
//            lottie_file_name_2 = "imoji_500.json";
//            lottieAnimationView.setAnimation(lottie_file_name_2);
//            lottieAnimationView.loop(false);
//            lottieAnimationView.playAnimation();
//
//        }else if (get_imoge_count >12)
//        {
//            lottie_file_name_2 = "imoji_100.json";
//            lottieAnimationView.setAnimation(lottie_file_name_2);
//            lottieAnimationView.loop(false);
//            lottieAnimationView.playAnimation();
//        }else
//        {
//            lottie_file_name_2 = "imoji_50.json";
//            lottieAnimationView.setAnimation(lottie_file_name_2);
//            lottieAnimationView.loop(false);
//            lottieAnimationView.playAnimation();
//        }




    }



}