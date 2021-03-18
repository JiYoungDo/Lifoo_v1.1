package com.GoogooCorn.lifoo_v11.src.AlertFragment.interfaces;


import com.GoogooCorn.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;

public interface AlertFragmentActivityView {
    // 알림 조회 실패
    void GetAlertFailure(String message, int code);

    // 알림 조회 성공
    void GetAlertSuccess(AlertFragmentResponse alertFragmentResponse, int code);
}
