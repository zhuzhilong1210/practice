package com.base.toolkit;

import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Author: vincent
 * Date: 2018-12-04 16:23:00
 * Comment:
 */

public class HttpClientUtil {

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_JSON = "application/json";

    /**
     * 默认的 GET 请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 默认的 POST 请求
     * @param url
     * @param data
     * @return
     */
    public static String doPost(String url, String data) {
        return doPost(url, data, null);
    }

    /**
     * 发送 GET 请求
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, Headers headers) {
        try {
            Request request = new Request.Builder().url(url).headers(headers).build();
            Call call = okHttpClient.newCall(request);
            return Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送 POST 请求
     * @param url
     * @param data
     * @param headers
     * @return
     */
    public static String doPost(String url, String data, Headers headers) {
        try {
            if (headers == null || StringUtils.isEmpty(headers.get(CONTENT_TYPE))) {
                headers = Headers.of(CONTENT_TYPE, APPLICATION_JSON);
            }
            MediaType mediaType = MediaType.parse(headers.get(CONTENT_TYPE));
            RequestBody requestBody = RequestBody.create(mediaType, data);
            Request request = new Request.Builder().post(requestBody).url(url).headers(headers).build();
            Call call = okHttpClient.newCall(request);
            return Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
