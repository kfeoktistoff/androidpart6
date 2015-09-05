package org.magnum.mobilecloud.assignment3.app.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;
import org.magnum.mobilecloud.assignment3.app.model.Video;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class AddVideoRequest extends RetrofitSpiceRequest<Video, VideoSvcApi> {
    private Video video;

    public AddVideoRequest(Video video) {
        super(Video.class, VideoSvcApi.class);
        this.video = video;
    }

    @Override
    public Video loadDataFromNetwork() throws Exception {
        return getService().addVideo(video);
    }
}
