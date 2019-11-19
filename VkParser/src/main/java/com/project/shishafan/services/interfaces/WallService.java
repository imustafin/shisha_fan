package com.project.shishafan.services.interfaces;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 17.11.2019
 */

import com.project.shishafan.models.Comment;
import com.project.shishafan.models.Post;
import com.vk.api.sdk.objects.enums.WallFilter;

import java.util.List;

public interface WallService {

    Integer getPostsCount(int ownerId, WallFilter wallFilter);
    Integer getCommentsCount(int ownerId, int postId);
    List<Post> getWallPosts(int ownerId, int count, int offset, WallFilter filter);
    List<Comment> getPostComments(int ownerId, int postId, boolean likes, int offset, int count);
}
