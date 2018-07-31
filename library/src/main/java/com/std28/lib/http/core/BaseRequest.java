package com.std28.lib.http.core;



import com.std28.lib.defs.Consts;
import com.std28.lib.http.interfaces.ErrorCode;
import com.std28.lib.http.interfaces.Response;
import com.std28.lib.implementations.PreferencesImp;
import com.std28.lib.utils.NetworkUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
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
        GET,
        PUT,
        DELETE
    }

    public static final String TAG = "BaseRequest";
    private HashMap<String, String> mParameters;
    private String endpoint;
    private Method method;
    private ArrayList<String> toUrlList;

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

    private String addedToUrl(ArrayList<String> toAdd) {
        String[] array = new String[toAdd.size()];
        return StringUtils.join(toAdd.toArray(array), "/");
    }

    public String getEndpoint() {
        if (toUrlList != null && !toUrlList.isEmpty()) {
            ArrayList<String> toAdd = new ArrayList<>(toUrlList);
            toAdd.add(0, endpoint);
            return addedToUrl(toAdd);
        }
        return endpoint;
    }

    public BaseRequest addToUrl(String toUrl) {
        if (toUrlList == null) {
            toUrlList = new ArrayList<>();
        }
        toUrlList.add(toUrl);
        return this;
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
            res.setCode(ErrorCode.NO_INTERNET);
            res.onError();
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
            res.setCode(ErrorCode.NO_INTERNET);
            res.onError();
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

    public void get(Response httpResponse) {
        setMethod(Method.GET);
        call(httpResponse);
    }

    public BaseResponse get() {
        setMethod(Method.GET);
        return call();
    }

    public void post(Response httpResponse) {
        setMethod(Method.POST);
        call(httpResponse);
    }

    public BaseResponse post() {
        setMethod(Method.POST);
        return call();
    }

    public void put(Response httpResponse) {
        setMethod(Method.PUT);
        call(httpResponse);
    }

    public BaseResponse put() {
        setMethod(Method.PUT);
        return call();
    }

    public void delete(Response httpResponse) {
        setMethod(Method.DELETE);
        call(httpResponse);
    }

    public BaseResponse delete() {
        setMethod(Method.DELETE);
        return call();
    }





}
