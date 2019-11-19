package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 19.11.2019
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shishafan.services.interfaces.GroupService;
import com.vk.api.sdk.actions.Groups;
import com.vk.api.sdk.client.ClientResponse;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ClientException;

public class MainGroupService implements GroupService {

    private ServiceActor serviceActor;
    private Groups groups;
    private ObjectMapper mapper;

    public MainGroupService(Groups groups, ServiceActor serviceActor) {
        this.serviceActor = serviceActor;
        this.groups = groups;
        this.mapper = new ObjectMapper();

    }

    @Override
    public Integer getGroupIdByName(String name) {

        int groupId = 0;

        try {
            ClientResponse clientResponse = groups.getById(serviceActor).groupId(name).executeAsRaw();
            String content = clientResponse.getContent();
            JsonNode response = mapper.readTree(content).get("response");
            if(response != null){
                groupId = response.get(0).get("id").asInt();
            }

        } catch (ClientException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return groupId;
    }
}
