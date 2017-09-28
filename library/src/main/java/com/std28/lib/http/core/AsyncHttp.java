package com.std28.lib.http.core;

import android.os.AsyncTask;
import android.util.Log;


import com.std28.lib.defs.Consts;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class AsyncHttp
    implements Consts
{

    public static String TAG = "AsyncHttp";

    private static final OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
    }


    public AsyncHttp() {
        super();
    }

    public void execute(BaseRequest request, BaseResponse response) {
        AsyncRequest task = new AsyncRequest(request, response);
        task.execute();
    }

    public static void syncCall(BaseRequest request, BaseResponse response) {
        try {
            String url = request.buildUrl();
            String authToken = request.getAuthToken();

            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
            builder.addHeader("Content-Type","application/json");
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

            if (DEBUG) {
                if (request.getMethod() == BaseRequest.Method.GET) {
                    StrBuilder buffer = new StrBuilder();
                    buffer.append("http GET '");
                    buffer.append(url);
                    if (StringUtils.isNotBlank(authToken)) {
                        buffer.append("' 'Authorization: ");
                        buffer.append(authToken);
                        buffer.append("'");
                    }
                    Log.d(TAG, buffer.toString());
                }
                if (request.getMethod() == BaseRequest.Method.POST) {
                    String args  = request.getJSONArgs().toString();
                    StrBuilder buffer = new StrBuilder();
                    buffer.append("http POST '");
                    buffer.append(url);
                    if (StringUtils.isNotBlank(authToken)) {
                        buffer.append("' 'Authorization: ");
                        buffer.append(authToken);
                        buffer.append("'");
                    }
                    buffer.append("' <<<' ");
                    buffer.append(args);
                    buffer.append("'");
                    Log.d(TAG, buffer.toString());
                }
            }

            okhttp3.Request req = builder.build();
            okhttp3.Response resp  = client.newCall(req).execute();
            String text = resp.body().string();
            Log.d(TAG, text);
            response.setText(text);
            JSONObject jsonObject = new JSONObject(text);
            response.setJsonObject(jsonObject);
            if (resp.code() == 200) {
                response.setCode(BaseResponse.Code.OK);
            } else {
                response.setCode(BaseResponse.Code.SERVER);
                if (jsonObject.has("e")) {
                    JSONObject error = jsonObject.getJSONObject("e");
                    response.setErrorCode(error.getInt("code"));
                    response.setErrorMessage(error.getString("message"));
                }
            }
        } catch (SocketTimeoutException t) {
            response.setCode(BaseResponse.Code.TIMEOUT);
        } catch (JSONException jsonEx) {
            response.setCode(BaseResponse.Code.PARSER);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(BaseResponse.Code.UNKNOWN);
        }
    }




    class AsyncRequest extends AsyncTask<Void, Void, Void> {


        BaseRequest request;
        BaseResponse response;

        AsyncRequest(BaseRequest request, BaseResponse response) {
            super();
            this.request = request;
            this.response = response;
        }

        @Override
        protected Void doInBackground(Void... none) {
            syncCall(this.request, this.response);
            return null;
        }

        protected void onPostExecute(Void params) {
            if (this.response.getCode() == BaseResponse.Code.OK) {
                JSONObject jsonObject = this.response.getJsonObject();
                if (jsonObject.has("p")) {
                    try {
                        this.response.onResponse(jsonObject.getJSONObject("p"));
                        return;
                    } catch (JSONException e) {
                            e.printStackTrace();
                    }
                }
                this.response.onResponse(jsonObject);
            } else {
                this.response.onError(this.response.getErrorMessage());
            }
        }

    }






}
