package org.magnum.mobilecloud.video.repository;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Kirill Feoktistov on 28.08.15
 */
@RepositoryRestResource(path = VideoSvcApi.VIDEO_SVC_PATH)
public interface UserVideoRatingRepository extends CrudRepository<UserVideoRating, Long> {
}
