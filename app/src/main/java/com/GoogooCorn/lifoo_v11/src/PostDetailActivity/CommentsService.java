package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.CommentsActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.CommentsRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.CommentLikesResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class CommentsService {

    private final CommentsActivityView commentsActivityView;

    public CommentsService(CommentsActivityView mCommentsActivityView) {
        this.commentsActivityView = mCommentsActivityView;
    }

    // 댓글 좋아
    void postLikes(String commentIdx) {
        final CommentsRetrofitInterface commentsRetrofitInterface = getRetrofit().create(CommentsRetrofitInterface.class);
        commentsRetrofitInterface.PostCommentLikesTest(commentIdx).enqueue(new Callback<CommentLikesResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<CommentLikesResponse> call, Response<CommentLikesResponse> response) {
                final CommentLikesResponse commentLikesResponse = response.body();

                if (commentLikesResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    commentsActivityView.PostCommentsLikesFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                commentsActivityView.PostCommentsLikesSuccess(commentLikesResponse.getMessage(),commentLikesResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<CommentLikesResponse> call, Throwable t) {
                commentsActivityView.PostCommentsLikesFailure("통신 자체 실패",0);
            }
        });
    }

}
