package com.std28.lib.http.interfaces;

/**
 * Created by Mauricio Barcelo on 08/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface ErrorCode {
    int OK = 200;
    int CREATED = 201;
    int TIMEOUT = -1;
    int PARSER = -2;
    int SERVER = -3;
    int NO_INTERNET = -4;
    int UNKNOWN = -5;
    int NO_CONNECT = -6;
}
