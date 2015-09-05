package org.magnum.mobilecloud.assignment3.app.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;
import org.magnum.mobilecloud.assignment3.app.model.Video;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class UnlikeVideoRequest extends RetrofitSpiceRequest<Void, VideoSvcApi> {
    private Video video;

    public UnlikeVideoRequest(Video video) {
        super(Void.class, VideoSvcApi.class);
        this.video = video;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {
        return getService().unlikeVideo(video.getId());
    }
}
