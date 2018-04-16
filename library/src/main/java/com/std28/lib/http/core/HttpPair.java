package com.std28.lib.http.core;

/**
 * Created by Mauricio Barcelo on 17/03/18.
 * email: mauricio.barcelo30@gmail.com
 */

@SuppressWarnings("WeakerAccess")
public class HttpPair {

    @SuppressWarnings("WeakerAccess")
    public final BaseRequest request;
    @SuppressWarnings("WeakerAccess")
    public final BaseResponse response;

    @SuppressWarnings("WeakerAccess")
    public HttpPair(BaseRequest request, BaseResponse response) {
        super();
        this.request = request;
        this.response = response;
    }

}
