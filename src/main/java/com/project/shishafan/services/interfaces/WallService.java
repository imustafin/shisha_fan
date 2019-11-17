package com.project.shishafan.services.interfaces;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 17.11.2019
 */

import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.enums.WallFilter;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

public interface WallService {

    GetResponse getWallPosts(int ownerId, int count, int offset, WallFilter filter);
    GetCommentsResponse getPostComments(int ownerId, int postId, boolean likes, int offset, int count);
}
