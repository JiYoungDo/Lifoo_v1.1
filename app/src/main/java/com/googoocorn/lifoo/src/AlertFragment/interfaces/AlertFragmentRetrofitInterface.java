package com.googoocorn.lifoo.src.AlertFragment.interfaces;

import com.googoocorn.lifoo.src.AlertFragment.models.AlertFragmentResponse;

import retrofit2.Call;
import retrofit2.http.GET;

//서비스 인터페이스
public interface AlertFragmentRetrofitInterface {

    // 알림 조회
    @GET("/alarms")
    Call<AlertFragmentResponse> GetAlarmTest();
}
