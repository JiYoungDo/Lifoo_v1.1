package com.googoocorn.lifoo.src.PostDetailActivity;

import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.CommentsActivityView;
import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.CommentsRetrofitInterface;
import com.googoocorn.lifoo.src.PostDetailActivity.models.CommentLikesResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.EditCommentsBody;
import com.googoocorn.lifoo.src.PostDetailActivity.models.ReportBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorn.lifoo.ApplicationClass.getRetrofit;

public class CommentsService {

    private final CommentsActivityView commentsActivityView;

    public CommentsService(CommentsActivityView mCommentsActivityView) {
        this.commentsActivityView = mCommentsActivityView;
    }

    // 댓글 좋아요
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


    // 댓글 삭제
    void delComment (String commentIdx) {
        final CommentsRetrofitInterface commentsRetrofitInterface = getRetrofit().create(CommentsRetrofitInterface.class);
        commentsRetrofitInterface.DelCommentTest(commentIdx).enqueue(new Callback<CommentLikesResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<CommentLikesResponse> call, Response<CommentLikesResponse> response) {
                final CommentLikesResponse deleteResponse = response.body();

                if (deleteResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    commentsActivityView.DeleteCommentsFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                commentsActivityView.DeleteCommentsSuccess(deleteResponse.getMessage(),deleteResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<CommentLikesResponse> call, Throwable t) {
                commentsActivityView.DeleteCommentsFailure("통신 자체 실패",0);
            }
        });
    }

    // 댓글 수정
    void EditComment (String commentIdx, String CommentBody) {
        final CommentsRetrofitInterface commentsRetrofitInterface = getRetrofit().create(CommentsRetrofitInterface.class);
        commentsRetrofitInterface.EditCommentTest(commentIdx,new EditCommentsBody(CommentBody)).enqueue(new Callback<CommentLikesResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<CommentLikesResponse> call, Response<CommentLikesResponse> response) {
                final CommentLikesResponse editResponse = response.body();

                if (editResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    commentsActivityView.EditommentsFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                commentsActivityView.EditCommentsSuccess(editResponse.getMessage(),editResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<CommentLikesResponse> call, Throwable t) {
                commentsActivityView.EditommentsFailure("통신 자체 실패",0);
            }
        });
    }


    // 댓글 신고
    void ReportComment (String targetIdx) {
        final CommentsRetrofitInterface commentsRetrofitInterface = getRetrofit().create(CommentsRetrofitInterface.class);
        commentsRetrofitInterface.ReportCommentTest(new ReportBody("COMMENT",targetIdx)).enqueue(new Callback<CommentLikesResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<CommentLikesResponse> call, Response<CommentLikesResponse> response) {
                final CommentLikesResponse reportresponse = response.body();

                if (reportresponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    commentsActivityView.ReportommentsFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                commentsActivityView.ReportCommentsSuccess(reportresponse.getMessage(),reportresponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<CommentLikesResponse> call, Throwable t) {
                commentsActivityView.ReportommentsFailure("통신 자체 실패",0);
            }
        });
    }
}
