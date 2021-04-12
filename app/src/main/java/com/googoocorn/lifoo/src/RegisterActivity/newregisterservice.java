package com.googoocorn.lifoo.src.RegisterActivity;



import com.googoocorn.lifoo.src.RegisterActivity.interfaces.newRegisterActiityView;
import com.googoocorn.lifoo.src.RegisterActivity.interfaces.newRegisterRetrofitInterface;
import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterBody;
import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorn.lifoo.ApplicationClass.getRetrofit;

public class newregisterservice {

    private final newRegisterActiityView newRegisterActivityView;

    public newregisterservice(newRegisterActiityView mnewRegisterActivityView) {
        this.newRegisterActivityView = mnewRegisterActivityView;
    }


    // 회원 가입 통신
    void PostRegister(String nick_name, String id, String pw, String pw_confirm) {

        final newRegisterRetrofitInterface  newRegisterRetrofitInterface = getRetrofit().create(newRegisterRetrofitInterface.class);
        newRegisterRetrofitInterface.registerTest(new NewRegisterBody(nick_name,id,pw,pw_confirm)).enqueue(new Callback<NewRegisterResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<NewRegisterResponse> call, Response<NewRegisterResponse> response) {

                final NewRegisterResponse registerResponse = response.body();

                if (registerResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    newRegisterActivityView.NewRegisterFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                newRegisterActivityView.NewRegisterSuccess(registerResponse,registerResponse.getMessage(), registerResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<NewRegisterResponse> call, Throwable t) {
                newRegisterActivityView.NewRegisterFailure("통신 자체 실패",0);
            }
        });
    }
}
