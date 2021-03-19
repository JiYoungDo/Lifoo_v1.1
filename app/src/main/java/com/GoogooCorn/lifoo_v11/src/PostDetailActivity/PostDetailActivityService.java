package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;




import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class PostDetailActivityService {

    private final PostDetailActivityView postDetailActivityView;

    public PostDetailActivityService(PostDetailActivityView mPostDetailActivityView) {
        this.postDetailActivityView = mPostDetailActivityView;
    }

    // 게시물 상세 조회
    void getDetailPost(int postIdx) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.GetDetailPostTest(postIdx).enqueue(new Callback<GetPostResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<GetPostResponse> call, Response<GetPostResponse> response) {
                final GetPostResponse getPostResponse = response.body();

                if (getPostResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.GetPostDetailFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.GetPostDetailSuccess(getPostResponse, getPostResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<GetPostResponse> call, Throwable t) {
                postDetailActivityView.GetPostDetailFailure("통신 자체 실패",0);
            }
        });
    }




}
