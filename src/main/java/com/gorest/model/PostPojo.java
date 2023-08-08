package com.gorest.model;

public class PostPojo {

    private String id;
    private String user_id;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static PostPojo getPostsPojo(String id, String user_id, String title, String body) {
        PostPojo postsPojo = new PostPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);
        return postsPojo;
    }
}
