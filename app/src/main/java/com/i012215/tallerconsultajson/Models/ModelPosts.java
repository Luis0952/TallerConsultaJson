package com.i012215.tallerconsultajson.Models;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class ModelPosts {

    private int userid;
    private int id_post;
    private String title;
    private String body;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
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
}
