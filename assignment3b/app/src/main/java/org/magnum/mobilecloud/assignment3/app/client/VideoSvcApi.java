package org.magnum.mobilecloud.assignment3.app.client;

import org.magnum.mobilecloud.assignment3.app.model.Video;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public interface VideoSvcApi {
    String URL = "https://192.168.1.38:8443";
    String TOKEN_PATH = "/oauth/token";
    String VIDEO_SVC_PATH = "/video";

    @GET(VIDEO_SVC_PATH)
    List<Video> getAllVideos();

    @POST(VIDEO_SVC_PATH + "/{id}/like")
    Void likeVideo(@Path("id") long id);

    @POST(VIDEO_SVC_PATH + "/{id}/unlike")
    Void unlikeVideo(@Path("id") long id);

    @POST(VIDEO_SVC_PATH)
    Video addVideo(@Body Video video);

    @GET(VIDEO_SVC_PATH + "/{id}/likedby")
    List<String> getVideoLikedBy(@Path("id") long id);
}
