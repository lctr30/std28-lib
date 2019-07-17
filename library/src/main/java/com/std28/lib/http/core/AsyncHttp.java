package com.std28.lib.http.core;

import android.os.AsyncTask;
import android.util.Log;


import com.std28.lib.defs.Consts;
import com.std28.lib.http.interfaces.ErrorCode;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;


import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

@SuppressWarnings("WeakerAccess")
public class AsyncHttp
    implements Consts
{

    public static String TAG = "AsyncHttp";

    private static final OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
    }


    @SuppressWarnings("WeakerAccess")
    public AsyncHttp() {
        super();
    }

    public void execute(BaseRequest request, BaseResponse response) {
        HttpPair httpPair = new HttpPair(request, response);
        AsyncRequest task = new AsyncRequest();
        task.execute(httpPair);
    }

    public static void syncCall(BaseRequest request, BaseResponse response) {
        try {
            String url = request.buildUrl();
            String authToken = request.getAuthToken();

            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
            builder.addHeader("Content-Type", "application/json");
            builder.addHeader("Accept", "application/json");
            builder.url(url);
            Log.d(TAG, url);
            if (StringUtils.isNotBlank(authToken)) {
                builder.addHeader("Authorization", authToken);
            }
            if (request.getMethod() == BaseRequest.Method.POST) {
                JSONObject json = request.getJSONArgs();
                RequestBody body = RequestBody.create(JSON, json.toString());
                builder.post(body);
            }

            if (request.getMethod() == BaseRequest.Method.PUT) {
                JSONObject json = request.getJSONArgs();
                RequestBody body = RequestBody.create(JSON, json.toString());
                builder.put(body);
            }
            if (request.getMethod() == BaseRequest.Method.DELETE) {
                builder = builder.delete();
            }
            debugRequest(request, authToken);

            okhttp3.Request req = builder.build();
            okhttp3.Response resp = client.newCall(req).execute();
            //noinspection ConstantConditions
            String text = resp.body().string();
            Log.d(TAG, text);
            response.setText(text);
            response.setCode(resp.code());
        } catch (SocketTimeoutException t) {
            response.setCode(ErrorCode.TIMEOUT);
            response.setErrorMessage(t.getMessage());
        } catch (ConnectException ce) {
            response.setCode(ErrorCode.NO_CONNECT);
            response.setErrorMessage(ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ErrorCode.UNKNOWN);
            response.setErrorMessage(e.getMessage());
        }
    }




    static class AsyncRequest extends AsyncTask<HttpPair, Void, HttpPair> {

        @Override
        protected HttpPair doInBackground(HttpPair... objects) {
            HttpPair pair = objects[0];
            syncCall(pair.request, pair.response);
            return pair;
        }

        protected void onPostExecute(HttpPair httpPair) {
            if (httpPair.response.getCode() == ErrorCode.OK ||
                    httpPair.response.getCode() == ErrorCode.CREATED) {
                httpPair.response.onResponse();
            } else {
                httpPair.response.onError();
            }
        }
    }

    private static void debugRequest(BaseRequest request, String token) {
        StringBuilder builder = new StringBuilder("http ");
        String args  = request.getJSONArgs().toString();
        switch (request.getMethod()) {
            case GET:
                builder.append("GET ");
                args = null;
                break;
            case PUT:
                builder.append("PUT ");
                break;
            case POST:
                builder.append("POST ");
                break;
            case DELETE:
                builder.append("DELETE ");
                break;
            default:
                break;
        }
        builder.append("'");
        builder.append(request.buildUrl());
        builder.append("' ");
        if (StringUtils.isNotBlank(token)) {
            builder.append("'Authorization: ");
            builder.append(token);
            builder.append("' ");
        }
        if (StringUtils.isNotBlank(args)) {
            builder.append("<<< '");
            builder.append(args);
            builder.append("'");
        }
        debug(TAG, builder.toString());
    }

    private static void debug(String tag, String string) {
        if (DEBUG) {
            Log.d(tag, string);
        }
    }

}
