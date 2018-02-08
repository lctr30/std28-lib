package com.std28.lib.http.interfaces;

/**
 * Created by Mauricio Barcelo on 08/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface ErrorCode {
    int OK = 0;
    int TIMEOUT = -1;
    int PARSER = -2;
    int SERVER = -3;
    int NO_INTERNET = -4;
    int UNKNOWN = -5;
}
