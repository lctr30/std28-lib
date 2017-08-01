package com.std28.lib.http.core;



import com.std28.lib.defs.Consts;
import com.std28.lib.http.interfaces.Response;
import com.std28.lib.implementations.PreferencesImp;
import com.std28.lib.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

public abstract class BaseRequest
    implements Consts
{

    public enum Method {
        POST,
        GET
    }

    public static final String TAG = "BaseRequest";
    private HashMap<String, String> mParameters;
    private String endpoint;
    private Method method;

    public BaseRequest() {
        super();
        mParameters = new HashMap<>();
    }

    public abstract String getBaseUrl();

    protected BaseRequest addParam(String key, String value) {
        mParameters.put(key, value);
        return this;
    }

    protected BaseRequest addParam(String key, long value) {
        mParameters.put(key, Long.toString(value));
        return this;
    }

    protected BaseRequest addParam(String key, int value) {
        mParameters.put(key, Integer.toString(value));
        return this;
    }

    protected BaseRequest addParam(String key, boolean value) {
        mParameters.put(key, Boolean.toString(value));
        return this;
    }


    public String getEndpoint() {
        return endpoint;
    }

    protected BaseRequest setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    protected BaseRequest setMethod(Method method) {
        this.method = method;
        return this;
    }

    public String getUrl() {
        return getBaseUrl() + getEndpoint();
    }

    public void call(Response httpResponse) {
        BaseResponse res = new BaseResponse(httpResponse);
        if (NetworkUtils.isOnline()) {
            assert getMethod() != null : "Method is null";
            assert getEndpoint() != null: "Endpoint is null";
            res.setRequest(this);
            new AsyncHttp().execute(this, res);
        } else {
            res.setCode(BaseResponse.Code.NO_INTERNET);
            res.onError(res.getErrorMessage());
        }
    }

    public BaseResponse call() {
        BaseResponse res = new BaseResponse();
        if (NetworkUtils.isOnline()) {
            assert getMethod() != null : "Method is null";
            assert getEndpoint() != null: "Endpoint is null";
            res.setRequest(this);
            AsyncHttp.syncCall(this, res);
        } else {
            res.setCode(BaseResponse.Code.NO_INTERNET);
            res.onError(res.getErrorMessage());
        }
        return res;
    }

    public String getAuthToken() {
        return PreferencesImp.Builder().getPreferenceString("token", null);
    }

    public static void setAuthToken(String token) {
        PreferencesImp.Builder().setPreferenceString("token",token );
    }


    public String buildUrl() {
        HttpUrl.Builder builder = HttpUrl.parse(getUrl()).newBuilder();
        if (method == Method.GET) {
            for (Map.Entry<String, String> entry : mParameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addQueryParameter(key, value);
            }
        }
        return builder.build().toString();
    }

    public RequestBody getBody() {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : mParameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.add(key, value);
        }
        return builder.build();
    }

    public JSONObject getJSONArgs() {
        return new JSONObject(mParameters);
    }

}
