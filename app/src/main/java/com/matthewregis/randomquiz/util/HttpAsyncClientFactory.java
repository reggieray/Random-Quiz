package com.matthewregis.randomquiz.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.MySSLSocketFactory;

/**
 * Created by reg on 27/11/2016.
 */

public class HttpAsyncClientFactory {

    private static AsyncHttpClient mClient;

    /**
     *
     * @return
     */
    public static AsyncHttpClient GetInstance(){
        if(mClient==null){
            mClient = new AsyncHttpClient();
            mClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        }
        return mClient;
    }

    public static AsyncHttpClient GetNewInstance(){
        mClient = new AsyncHttpClient();
        mClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        return mClient;
    }




}
