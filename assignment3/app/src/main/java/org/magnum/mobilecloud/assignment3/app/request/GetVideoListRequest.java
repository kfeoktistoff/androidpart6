package org.magnum.mobilecloud.assignment3.app.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;

import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class GetVideoListRequest extends RetrofitSpiceRequest<List, VideoSvcApi> {
    public GetVideoListRequest() {
        super(List.class, VideoSvcApi.class);
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        return getService().getAllVideos();
    }
}
