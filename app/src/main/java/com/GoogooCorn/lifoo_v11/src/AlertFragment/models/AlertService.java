package com.GoogooCorn.lifoo_v11.src.AlertFragment.models;

import com.GoogooCorn.lifoo_v11.src.AlertFragment.interfaces.AlertFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.interfaces.AlertFragmentRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class AlertService {
    private final AlertFragmentActivityView alertFragmentActivityView;

    public AlertService(AlertFragmentActivityView mAlertFragmentActivityView) {
        this.alertFragmentActivityView = mAlertFragmentActivityView;
    }


    public void GetAlarms() {

        final AlertFragmentRetrofitInterface alertFragmentRetrofitInterface = getRetrofit().create(AlertFragmentRetrofitInterface.class);
        alertFragmentRetrofitInterface.GetAlarmTest().enqueue(new Callback<AlertFragmentResponse>() {

            // 통신 성공
            @Override
            public void onResponse(Call<AlertFragmentResponse> call, Response<AlertFragmentResponse> response) {
                final AlertFragmentResponse alertFragmentResponse = response.body();

                if (alertFragmentResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    alertFragmentActivityView.GetAlertFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                alertFragmentActivityView.GetAlertSuccess(alertFragmentResponse,alertFragmentResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<AlertFragmentResponse> call, Throwable t) {
                alertFragmentActivityView.GetAlertFailure("통신 자체 실패",0);
            }
        });
    }
}
