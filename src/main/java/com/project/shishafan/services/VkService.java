package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 07.11.2019
 */

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkService {

    private VkApiClient vk;
    private AuthService authService;
    private WallService wallService;


    public VkService() {
        vk = new VkApiClient(HttpTransportClient.getInstance());
        authService = new AuthService();
        wallService = new WallService(vk.wall(),authService.getServiceActor());
    }

    public VkApiClient api(){
        return vk;
    }

    public void getAllPostsFromGroup(){

    }

    public void getAllPostsFromGroupWithPostsComments(){

    }
}
