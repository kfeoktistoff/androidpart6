package org.magnum.mobilecloud.assignment3.app.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import org.magnum.mobilecloud.assignment3.app.client.UnsafeHttpsClient;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;
import org.magnum.mobilecloud.assignment3.app.utils.SecuredRestBuilder;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class VideoSpiceService extends RetrofitGsonSpiceService {
    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(VideoSvcApi.class);
    }

    @Override
    protected String getServerUrl() {
        return VideoSvcApi.URL;
    }

    @Override
    protected Builder createRestAdapterBuilder() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        return new SecuredRestBuilder()
                .setLoginEndpoint(getServerUrl() + VideoSvcApi.TOKEN_PATH)
                .setEndpoint(getServerUrl())
                .setUsername("user0")
                .setPassword("pass")
                .setClientId("mobile")
                .setClient(new OkClient(UnsafeHttpsClient.getUnsafeOkHttpClient()))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL);
    }
}