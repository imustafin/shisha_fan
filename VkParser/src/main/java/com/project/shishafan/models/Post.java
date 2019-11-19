package com.project.shishafan.models;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 17.11.2019
 */

import java.util.Collections;
import java.util.List;

public class Post {

    private int id;
    private String text;
    private int likesCount;
    private int repostsCount;
    private int commentsCount;
    private List<Comment> comments;

    public Post(int id, String text, int likesCount, int repostsCount, int commentsCount) {
        this.id = id;
        this.text = text;
        this.likesCount = likesCount;
        this.repostsCount = repostsCount;
        this.commentsCount = commentsCount;
        this.comments = Collections.emptyList();
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
