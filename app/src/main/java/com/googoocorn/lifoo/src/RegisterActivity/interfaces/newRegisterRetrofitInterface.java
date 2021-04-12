package com.googoocorn.lifoo.src.RegisterActivity.interfaces;

import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterBody;
import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface newRegisterRetrofitInterface {

    //로컬 회원가입
    @POST("/users/local")
    Call<NewRegisterResponse> registerTest(
            @Body NewRegisterBody registerBody
    );

}
