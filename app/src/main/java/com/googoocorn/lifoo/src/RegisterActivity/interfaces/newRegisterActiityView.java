package com.googoocorn.lifoo.src.RegisterActivity.interfaces;

import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterResponse;
import com.googoocorn.lifoo.src.SocialLoginActivity.models.SocialLoginResponse;

public interface newRegisterActiityView {

    // Register 실패시
    void NewRegisterFailure(String message, int code);

    //Register 성공시
    void NewRegisterSuccess(NewRegisterResponse newRegisterResponse, String message, int code);

}
