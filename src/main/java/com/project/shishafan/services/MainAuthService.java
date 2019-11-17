package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 07.11.2019
 */

import com.project.shishafan.services.interfaces.AuthService;
import com.project.shishafan.utils.PropertiesUtils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;

import java.util.Properties;

public class MainAuthService implements AuthService {

    private ServiceActor serviceActor;

    public MainAuthService() {
        this.serviceActor = setServiceActor();
    }

    private ServiceActor serviceActor(Integer appId, String clientSecret, String accessToken) {
        return new ServiceActor(appId, clientSecret, accessToken);
    }

    private ServiceActor setServiceActor(){
        Properties properties = PropertiesUtils.properties();
        return serviceActor(Integer.parseInt(properties.getProperty("client_id")),
                properties.getProperty("client_secret"), properties.getProperty("access_token"));
    }

    public ServiceActor getServiceActor() {
        return serviceActor;
    }
}
