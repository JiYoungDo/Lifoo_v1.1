package com.example.lifoo_v11.src.PhotoPostActivity;


import com.example.lifoo_v11.src.PhotoPostActivity.interfaces.PhotoPostActivityView;
import com.example.lifoo_v11.src.PhotoPostActivity.interfaces.PhotoPostRetrofitInterface;
import com.example.lifoo_v11.src.PhotoPostActivity.models.PhotoPostBody;
import com.example.lifoo_v11.src.PhotoPostActivity.models.PhotoPostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lifoo_v11.ApplicationClass.getRetrofit;

public class PhotoPostService {

    private final PhotoPostActivityView photoPostActivityView;

    public PhotoPostService(PhotoPostActivityView mPhotoPostActivityView) {
        this.photoPostActivityView = mPhotoPostActivityView;
    }

    // post 로그인 통신
    void postPosts(String postUrl, String postTitle, String postBody) {

        final PhotoPostRetrofitInterface photoPostRetrofitInterface = getRetrofit().create(PhotoPostRetrofitInterface.class);
        photoPostRetrofitInterface.PhotoPostTest(new PhotoPostBody(postUrl,postTitle,postBody)).enqueue(new Callback<PhotoPostResponse>() {


            // 통신 성공 시
            @Override
            public void onResponse(Call<PhotoPostResponse> call, Response<PhotoPostResponse> response) {
                final PhotoPostResponse photoPostResponse = response.body();

                if (photoPostResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    photoPostActivityView.PhotoPostFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                photoPostActivityView.PhotoPostSuccess(photoPostResponse.getMessage(),photoPostResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<PhotoPostResponse> call, Throwable t) {
                photoPostActivityView.PhotoPostFailure("통신 자체 실패",0);
            }
        });
    }

}
