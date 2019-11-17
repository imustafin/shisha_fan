package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.enums.WallFilter;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

public class WallService {

    private Wall wall;
    private ServiceActor serviceActor;

    public WallService(Wall vk, ServiceActor serviceActor) {
        this.wall = vk;
        this.serviceActor = serviceActor;
    }

    public GetResponse getWallPosts(int ownerId, int count,
                                    int offset, WallFilter filter) throws ClientException, ApiException {
        return wall.get(serviceActor)
                .ownerId(ownerId).count(count).offset(offset).filter(filter).execute();
    }

    public GetCommentsResponse getPostComments(int ownerId, int postId, boolean likes,
                                               int offset, int count) throws ClientException, ApiException {
        return wall.getComments(serviceActor)
                .ownerId(ownerId).postId(postId).needLikes(likes).offset(offset).count(count).execute();
    }


}
