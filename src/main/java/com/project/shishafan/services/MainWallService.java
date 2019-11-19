package com.project.shishafan.services;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shishafan.drivers.models.Comment;
import com.project.shishafan.drivers.models.Post;
import com.project.shishafan.services.interfaces.WallService;
import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.ClientResponse;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.enums.WallFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainWallService implements WallService {

    private Wall wall;
    private ServiceActor serviceActor;
    private ObjectMapper mapper;

    public MainWallService(Wall vk, ServiceActor serviceActor) {
        this.wall = vk;
        this.serviceActor = serviceActor;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Integer getPostsCount(int ownerId, WallFilter wallFilter) {

        int postsCount = 0;

        try {
            ClientResponse clientResponse = wall.get(serviceActor).ownerId(ownerId).filter(wallFilter).executeAsRaw();
            String content = clientResponse.getContent();
            postsCount = mapper.readTree(content).get("response").get("count").asInt();
        } catch (ClientException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return postsCount;
    }

    @Override
    public Integer getCommentsCount(int ownerId, int postId) {

        int commentsCount = 0;

        try {
            ClientResponse clientResponse = wall.getComments(serviceActor)
                    .ownerId(ownerId).postId(postId).needLikes(false).offset(0).count(0).executeAsRaw();
            String content = clientResponse.getContent();
            commentsCount = mapper.readTree(content).get("response").get("count").asInt();
        } catch (ClientException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return commentsCount;
    }


    public List<Post> getWallPosts(int ownerId, int count, int offset, WallFilter filter) {

        List<Post> posts = Collections.emptyList();

        try {
            ClientResponse clientResponse = wall.get(serviceActor)
                    .ownerId(ownerId).count(count).offset(offset).filter(filter).executeAsRaw();
            String content = clientResponse.getContent();
            JsonNode items = getItemsFromJsonString(content);
            posts = getPostsFromItemsJson(items);
        } catch (ClientException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return posts;


    }

    public List<Comment> getPostComments(int ownerId, int postId, boolean likes, int offset, int count) {

        List<Comment> comments = Collections.emptyList();

        try {
            ClientResponse clientResponse = wall.getComments(serviceActor)
                    .ownerId(ownerId).postId(postId).needLikes(likes).offset(offset).count(count).executeAsRaw();
            String content = clientResponse.getContent();
            JsonNode items = getItemsFromJsonString(content);
            comments = getCommentsFromItemsJson(items);
        } catch (ClientException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return comments;
    }

    private JsonNode getItemsFromJsonString(String content) throws JsonProcessingException {
        return mapper.readTree(content).get("response").get("items");
    }

    private List<Post> getPostsFromItemsJson(JsonNode items) {

        List<Post> posts = new ArrayList<>();

        for (JsonNode node : items) {
            posts.add(new Post(
                    node.get("id").asInt(),
                    node.get("text").asText(),
                    node.get("likes").get("count").asInt(),
                    node.get("reposts").get("count").asInt(),
                    node.get("comments").get("count").asInt()));
        }

        return posts;

    }

    private List<Comment> getCommentsFromItemsJson(JsonNode items) {

        List<Comment> comments = new ArrayList<>();

        for (JsonNode node : items) {
            JsonNode textNode = node.get("text");
            JsonNode likesNode = node.get("likes");
            JsonNode idNode = node.get("id");

            int id = idNode != null ? idNode.asInt() : -1;
            String text = textNode != null ? textNode.asText() : "";
            int likes = likesNode != null ? (likesNode.get("count") != null ? likesNode.get("count").asInt() : -1) : -1;
            
            comments.add(new Comment(id, text, likes));
        }

        return comments;
    }


}
