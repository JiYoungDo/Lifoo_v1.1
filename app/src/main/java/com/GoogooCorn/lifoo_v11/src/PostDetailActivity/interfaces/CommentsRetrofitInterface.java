package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;


import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.CommentLikesResponse;


import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentsRetrofitInterface {

    // 좋아요 보내기 게시
    @POST("/comments/{commentIdx}/likes")
    Call<CommentLikesResponse> PostCommentLikesTest(
            @Path("commentIdx") String commentIdx);



}
