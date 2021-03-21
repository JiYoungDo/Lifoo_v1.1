package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostDetailRetrofitInterface {

    // 게시물 상세 조회
    @GET("/posts/{postIdx}")
    Call<GetPostResponse> GetDetailPostTest(
            @Path("postIdx") int postIdx);


    // 댓글 상세 조회
    /**@GET("/posts/{postIdx}/comments")
    Call<GetCommentResponse> GetCommentTest(
            @Path("postIdx") int postIdx,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num); */





}
