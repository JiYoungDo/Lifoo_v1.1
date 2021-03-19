package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostDetailRetrofitInterface {

    // 게시물 상세 조회
    @GET("/posts/{postIdx}")
    Call<GetPostResponse> GetDetailPostTest(
            @Path("postIdx") int postIdx);







}
