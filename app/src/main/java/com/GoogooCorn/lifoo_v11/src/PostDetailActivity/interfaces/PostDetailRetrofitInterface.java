package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameBody;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.CommentBody;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.CommentLikesResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostEditBody;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostImogeBody;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.ReportBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostDetailRetrofitInterface {

    // 게시물 상세 조회
    @GET("/posts/{postIdx}")
    Call<GetPostResponse> GetDetailPostTest(
            @Path("postIdx") int postIdx);


    // 댓글 상세 조회
    @GET("/posts/{postIdx}/comments")
    Call<GetCommentResponse> GetCommentTest(
            @Path("postIdx") int postIdx,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num);

    // 댓글 게시
    @POST("/comments")
    Call<PostDeleteResponse> PostCommentTest(
            @Body CommentBody body);

    // 게시물 수정
    @PATCH("/posts/{postIdx}")
    Call<GetPostResponse> EditMyPostTest(
            @Path("postIdx") int postIdx,
            @Body PostEditBody body
    );

    // 게시물 삭제
    @DELETE("/posts/{postIdx}")
    Call<PostDeleteResponse> DeleteMyPostTest(
            @Path("postIdx") int postIdx
    );

    // 이모지 등록
    @POST("/imoges")
    Call<PostDeleteResponse> PostImogeTest(
            @Body PostImogeBody body
    );

    // 게시물 신고
    @POST("/reports")
    Call<PostDeleteResponse> ReportPostTest(
            @Body ReportBody body);

}
