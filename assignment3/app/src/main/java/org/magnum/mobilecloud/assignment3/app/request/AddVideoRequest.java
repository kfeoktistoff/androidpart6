package org.magnum.mobilecloud.assignment3.app.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;
import org.magnum.mobilecloud.assignment3.app.model.Video;

import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class AddVideoRequest extends RetrofitSpiceRequest<Video, VideoSvcApi> {
    @Override
    public Video loadDataFromNetwork() throws Exception {
        return null;
    }
}
