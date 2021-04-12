package com.googoocorn.lifoo.config;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.googoocorn.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor{

    @NotNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }
        return chain.proceed(builder.build());
    }

}
