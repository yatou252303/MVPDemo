package com.polymerization.core.retrofit.Exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.lib.dialogext.extoast.Ts;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * Created by Zaifeng on 2018/2/28.
 * 本地异常处理。包括解析异常等其他异常
 */

public class LocalException {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;

    public static final int UNLOGIN= -1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;

    public static ServiceException handleException(Throwable e) {
        ServiceException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            ex = new ServiceException(PARSE_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ConnectException) {
            //网络错误
            ex = new ServiceException(NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //连接错误
            ex = new ServiceException(NETWORK_ERROR, e.getMessage());
            Ts.show("网络异常，请检查网络");
            return ex;
        } else {
            //未知错误
            ex = new ServiceException(UNKNOWN, e.getMessage());
            return ex;
        }
    }
}