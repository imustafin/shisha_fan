package com.project.shishafan.models;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 17.11.2019
 */

public class Comment {

    private int id;
    private String text;
    private int likesCount;

    public Comment() {
    }

    public Comment(int id, String text, int likesCount) {
        this.id = id;
        this.text = text;
        this.likesCount = likesCount;
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


}
