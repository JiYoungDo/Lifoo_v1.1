package com.googoocorn.lifoo.src.PostDetailActivity;





import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.PostDetailRetrofitInterface;
import com.googoocorn.lifoo.src.PostDetailActivity.models.CommentBody;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetCommentResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetPostResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.PostDeleteResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.PostEditBody;
import com.googoocorn.lifoo.src.PostDetailActivity.models.PostImogeBody;
import com.googoocorn.lifoo.src.PostDetailActivity.models.ReportBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorn.lifoo.ApplicationClass.getRetrofit;

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

    // 게시물 수정
    void EditMyPost(int postIdx, PostEditBody body) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.EditMyPostTest(postIdx, body).enqueue(new Callback<GetPostResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<GetPostResponse> call, Response<GetPostResponse> response) {
                final GetPostResponse getPostResponse = response.body();

                if (getPostResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.EditPostFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.EditPostSuccess(getPostResponse, getPostResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<GetPostResponse> call, Throwable t) {
                postDetailActivityView.EditPostFailure("통신 자체 실패",0);
            }
        });
    }

    // 게시물 삭제
    void DeleteMyPost(int postIdx) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.DeleteMyPostTest(postIdx).enqueue(new Callback<PostDeleteResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                final PostDeleteResponse postDeleteResponse = response.body();

                if (postDeleteResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.DeletePostFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.DeletePostSuccess(postDeleteResponse, postDeleteResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                postDetailActivityView.DeletePostFailure("통신 자체 실패",0);
            }
        });
    }

    // 이모지 보내기
    void postImoge(String postIdx, String ImogeIdx ) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.PostImogeTest(new PostImogeBody(postIdx,ImogeIdx)).enqueue(new Callback<PostDeleteResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                final PostDeleteResponse postImogeResponse = response.body();

                if (postImogeResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.PostImogeFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.PostImogeSuccess(postImogeResponse.getMessage(), postImogeResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                postDetailActivityView.PostImogeFailure("통신 자체 실패",0);
            }
        });
    }

    // 댓글 조회 가져오기
    void getComments(int postIdx) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.GetCommentTest(postIdx,30,0).enqueue(new Callback<GetCommentResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<GetCommentResponse> call, Response<GetCommentResponse> response) {
                final GetCommentResponse getCommentResponse = response.body();

                if (getCommentResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.GetCommentsFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.GetCommentsSuccess(getCommentResponse, getCommentResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<GetCommentResponse> call, Throwable t) {
                postDetailActivityView.GetCommentsFailure("통신 자체 실패",0);
            }
        });
    }


    // 댓글 게시하기
    void postComments(String postIdx, String postBody) {

        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.PostCommentTest(new CommentBody(postIdx,postBody)).enqueue(new Callback<PostDeleteResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                final PostDeleteResponse postCommentResponse = response.body();

                if (postCommentResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.PostCommentsFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.PostCommentsSuccess(postCommentResponse.getMessage(), postCommentResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                postDetailActivityView.PostCommentsFailure("통신 자체 실패",0);
            }
        });
    }

    // 게시물 신고
    void ReportPost(String targetIdx) {
        final PostDetailRetrofitInterface postDetailRetrofitInterface = getRetrofit().create(PostDetailRetrofitInterface.class);
        postDetailRetrofitInterface.ReportPostTest(new ReportBody("POST", targetIdx)).enqueue(new Callback<PostDeleteResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                final PostDeleteResponse postDeleteResponse = response.body();

                if (postDeleteResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    postDetailActivityView.ReportPostFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                postDetailActivityView.ReportPostSuccess(postDeleteResponse.getMessage(),postDeleteResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                postDetailActivityView.ReportPostFailure("통신 자체 실패",0);
            }
        });
    }
}
