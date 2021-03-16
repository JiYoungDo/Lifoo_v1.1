package com.example.lifoo_v11.src.AlertFragment.interfaces;

import com.example.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//서비스 인터페이스
public interface AlertFragmentRetrofitInterface {

    // 알림 조회
    @GET("/alarms")
    Call<AlertFragmentResponse> GetAlarmTest();
}
