package com.googoocorn.lifoo.src.PostDetailActivity.interfaces;


import com.googoocorn.lifoo.src.PostDetailActivity.models.CommentLikesResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.EditCommentsBody;
import com.googoocorn.lifoo.src.PostDetailActivity.models.ReportBody;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentsRetrofitInterface {

    // 좋아요 보내기 게시
    @POST("/comments/{commentIdx}/likes")
    Call<CommentLikesResponse> PostCommentLikesTest(
            @Path("commentIdx") String commentIdx);


    // 댓글 삭제
    @DELETE("/comments/{commentIdx}")
    Call<CommentLikesResponse> DelCommentTest(
            @Path("commentIdx") String commentIdx);


    // 댓글 수정
    @PUT("/comments/{commentIdx}")
    Call<CommentLikesResponse> EditCommentTest(
            @Path("commentIdx") String commentIdx,
            @Body EditCommentsBody body);


    // 좋아요 보내기 게시
    @POST("/reports")
    Call<CommentLikesResponse> ReportCommentTest(
            @Body ReportBody body);
}
