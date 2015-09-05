package org.magnum.mobilecloud.assignment3.app.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

/**
 * A simple object to represent a video and its URL for viewing.
 * <p/>
 * You probably need to, at a minimum, add some annotations to this
 * class.
 * <p/>
 * You are free to add annotations, members, and methods to this
 * class. However, you probably should not change the existing
 * methods or member variables. If you do change them, you need
 * to make sure that they are serialized into JSON in a way that
 * matches what is expected by the auto-grader.
 *
 * @author mitchell
 */
public class Video {
    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private String url;

    @Expose
    private long duration;

    @Expose
    private long likes;

    @Expose
    private String owner;

    public Video() {
    }

    public Video(String name, String url, long duration, long likes) {
        super();
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Two Videos will generate the same hashcode if they have exactly the same
     * values for their name, url, and duration.
     */
    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(name, url, duration);
    }

    /**
     * Two Videos are considered equal if they have exactly the same values for
     * their name, url, and duration.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Video) {
            Video other = (Video) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(name, other.name)
                    && Objects.equal(url, other.url)
                    && duration == other.duration;
        } else {
            return false;
        }
    }
}
