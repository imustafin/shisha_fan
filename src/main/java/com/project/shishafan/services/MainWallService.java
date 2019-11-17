package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

import com.project.shishafan.services.interfaces.WallService;
import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.enums.WallFilter;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

public class MainWallService implements WallService {

    private Wall wall;
    private ServiceActor serviceActor;

    public MainWallService(Wall vk, ServiceActor serviceActor) {
        this.wall = vk;
        this.serviceActor = serviceActor;
    }

    public GetResponse getWallPosts(int ownerId, int count, int offset, WallFilter filter){
        try {
            return wall.get(serviceActor)
                    .ownerId(ownerId).count(count).offset(offset).filter(filter).execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new GetResponse();
    }

    public GetCommentsResponse getPostComments(int ownerId, int postId, boolean likes, int offset, int count){
        try {
            return wall.getComments(serviceActor)
                    .ownerId(ownerId).postId(postId).needLikes(likes).offset(offset).count(count).execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new GetCommentsResponse();
    }


}
