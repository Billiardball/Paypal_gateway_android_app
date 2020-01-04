package com.shop.arram.utils;

import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator_other {
    public static final String API_BASE_URL_new = "https://arram.de";


    private static OkHttpClient.Builder httpClient_new = new OkHttpClient.Builder();

    private static Retrofit.Builder builder_new =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL_new)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit_new = builder_new.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient_new.interceptors().contains(interceptor)) {

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.level(HttpLoggingInterceptor.Level.BASIC);
                logging.level(HttpLoggingInterceptor.Level.HEADERS);
                logging.level(HttpLoggingInterceptor.Level.BODY);

                httpClient_new.addInterceptor(interceptor);
                httpClient_new.addInterceptor(logging);

                builder_new.client(httpClient_new.build());
                retrofit_new = builder_new.build();
            }
        }

        return retrofit_new.create(serviceClass);
    }
}
