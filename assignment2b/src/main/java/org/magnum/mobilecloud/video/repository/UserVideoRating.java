package org.magnum.mobilecloud.video.repository;

import javax.persistence.*;

/**
 * Created by Kirill Feoktistov on 28.08.15
 */

@Entity
public class UserVideoRating {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    @ManyToOne
    private Video video;

    public UserVideoRating() {}

    public UserVideoRating(Video video, String username) {
        this.video = video;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
