package com.matthewregis.randomquiz.data.remote.OpentdbApi;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.injection.ApplicationContext;
import com.matthewregis.randomquiz.util.HttpAsyncClientFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by reg on 27/11/2016.
 */

@Singleton
public class OpentdbApi {

    Context mContact;
    AsyncHttpClient asyncHttpClient;

    @Inject
    public OpentdbApi(@ApplicationContext Context context){
        this.mContact = context;
        asyncHttpClient = HttpAsyncClientFactory.GetInstance();
    }

    public void Get10RandomQuestions(JsonHttpResponseHandler jsonHttpResponseHandler){
        asyncHttpClient.get(String.format("%s%s", mContact.getString(R.string.opentdb_base_url), mContact.getString(R.string.opentdb_random_10_path)), jsonHttpResponseHandler);
    }
}
