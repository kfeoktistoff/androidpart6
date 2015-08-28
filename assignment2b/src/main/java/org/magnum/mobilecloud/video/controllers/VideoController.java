package org.magnum.mobilecloud.video.controllers;

import com.google.common.collect.Lists;
import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.UserVideoRating;
import org.magnum.mobilecloud.video.repository.UserVideoRatingRepository;
import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Kirill Feoktistov on 27.08.15
 */
@Controller
public class VideoController {

    private static final AtomicLong currentId = new AtomicLong(0L);

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserVideoRatingRepository userVideoRatingRepository;

    // GET /video
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Video> getVideoList() {
        return Lists.newArrayList(videoRepository.findAll());
    }

    // POST /video
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.POST)
    public
    @ResponseBody
    Video addVideo(@RequestBody Video newVideo, HttpServletResponse resp) throws IOException {
        Video similarVideo = tryToFindSimilarVideo(newVideo);

        if (similarVideo == null) {
            newVideo.setOwner(getCurrentUsername());
            return videoRepository.save(newVideo);
        } else {
            if (getCurrentUsername().equals(similarVideo.getOwner())) {
                return videoRepository.save(newVideo);
            } else {
                return similarVideo;
            }
        }
    }

    private Video tryToFindSimilarVideo(Video video) {
        for (Video similarVideo : videoRepository.findAll()) {
            if (video.equals(similarVideo)) {
                return similarVideo;
            }
        }

        return null;
    }

    private String getCurrentUsername() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    // GET /video/{id}
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}", method = RequestMethod.GET)
    public @ResponseBody Video getData(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Video v = videoRepository.findOne(id);

        if (v == null) {
            response.setStatus(404);
            return null;
        }

        return v;
    }

    // POST /video/{id}/like
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}/like", method = RequestMethod.POST)
    public void likeVideo(@PathVariable("id") long id, HttpServletResponse response) {
        Video v = videoRepository.findOne(id);

        if (v == null) {
            response.setStatus(404);
        } else {
            if (isAlreadyLikedBy(v, getCurrentUsername())) {
                response.setStatus(400);
            } else {
                v.setLikes(v.getLikes() + 1);
                videoRepository.save(v);
                userVideoRatingRepository.save(new UserVideoRating(v, getCurrentUsername()));
            }
        }
    }

    private boolean isAlreadyLikedBy(Video video, String username) {
        for (UserVideoRating userVideoRating : userVideoRatingRepository.findAll()) {
            if (username.equals(userVideoRating.getUsername()) && video.equals(userVideoRating.getVideo())) {
                return true;
            }
        }

        return false;
    }

    private UserVideoRating findRatingByUserAndVideo(Video video, String username) {
        for (UserVideoRating userVideoRating : userVideoRatingRepository.findAll()) {
            if (username.equals(userVideoRating.getUsername()) && video.equals(userVideoRating.getVideo())) {
                return userVideoRating;
            }
        }

        return null;
    }

    // POST /video/{id}/unlike
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}/unlike", method = RequestMethod.POST)
    public void unlikeVideo(@PathVariable("id") long id, HttpServletResponse response) {
        Video v = videoRepository.findOne(id);

        if (v == null) {
            response.setStatus(404);
        } else {
            if (isAlreadyLikedBy(v, getCurrentUsername())) {
                v.setLikes(v.getLikes() - 1);
                videoRepository.save(v);
                userVideoRatingRepository.delete(findRatingByUserAndVideo(v, getCurrentUsername()));
            } else {
                response.setStatus(400);
            }
        }
    }

    // GET /video/{id}/likedby
    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}/likedby", method = RequestMethod.GET)
    public @ResponseBody Collection<String> getLikedBy(@PathVariable("id") long id, HttpServletResponse response) {
        Video v = videoRepository.findOne(id);

        List<String> likedBy = new ArrayList<>();

        if (v == null) {
            response.setStatus(404);
        } else {
            for (UserVideoRating userVideoRating : userVideoRatingRepository.findAll()) {
                if (v.equals(userVideoRating.getVideo())) {
                    likedBy.add(userVideoRating.getUsername());
                }
            }
        }

        return likedBy;
    }
}
