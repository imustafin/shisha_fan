package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 07.11.2019
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shishafan.drivers.models.Comment;
import com.project.shishafan.drivers.models.Post;
import com.project.shishafan.services.interfaces.AuthService;
import com.project.shishafan.services.interfaces.WallService;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.enums.WallFilter;

import java.util.ArrayList;
import java.util.List;

public class VkService {

    private VkApiClient vk;
    private AuthService authService;
    private WallService wallService;

    private final int POSTS_FETCH_SIZE = 100;
    private final int COMMENTS_FETCH_SIZE = 100;


    public VkService() {
        this.vk = new VkApiClient(HttpTransportClient.getInstance());
        this.authService = new MainAuthService();
        this.wallService = new MainWallService(vk.wall(), authService.getServiceActor());
    }

    public VkApiClient api() {
        return vk;
    }

    public List<Post> getAllPostsFromGroup(int ownerId) {

        int remains = wallService.getPostsCount(ownerId, WallFilter.OWNER);
        List<Post> wallPosts = new ArrayList<>();

        int iteration = 0;
        int offset;
        do {
            offset = iteration * POSTS_FETCH_SIZE;
            List<Post> subPosts = wallService.getWallPosts(ownerId, POSTS_FETCH_SIZE, offset, WallFilter.OWNER);
            wallPosts.addAll(subPosts);
            remains -= POSTS_FETCH_SIZE;
            iteration++;
        } while (remains > 0);

        return wallPosts;
    }

    public List<Post> getAllPostsFromGroupWithPostsComments(int ownerId) {

        List<Post> wallPosts = getAllPostsFromGroup(ownerId);

        for (Post post : wallPosts) {
            if (post.getCommentsCount() != 0) {
                int remains = post.getCommentsCount();
                List<Comment> postComments = new ArrayList<>();

                int iteration = 0;
                int offset;
                do {
                    offset = iteration * COMMENTS_FETCH_SIZE;
                    int fetchSize = Math.min(remains, COMMENTS_FETCH_SIZE);
                    List<Comment> subComments =
                            wallService.getPostComments(ownerId, post.getId(), true, offset, fetchSize);
                    postComments.addAll(subComments);
                    remains -= fetchSize;
                    iteration++;
                } while (remains > 0);
                post.setComments(postComments);
            }
        }
        return wallPosts;
    }
}
