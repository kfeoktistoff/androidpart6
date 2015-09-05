package org.magnum.mobilecloud.assignment3.app.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import org.magnum.mobilecloud.assignment3.app.client.VideoSvcApi;
import org.magnum.mobilecloud.assignment3.app.model.Video;

import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class GetVideoLikedBy extends RetrofitSpiceRequest<List, VideoSvcApi> {
    private Video video;

    public GetVideoLikedBy(Video video) {
        super(List.class, VideoSvcApi.class);
        this.video = video;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        return getService().getVideoLikedBy(video.getId());
    }
}
