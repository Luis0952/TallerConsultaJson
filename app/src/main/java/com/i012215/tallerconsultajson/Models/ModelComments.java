package com.i012215.tallerconsultajson.Models;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class ModelComments {
private int postid;
private int id_comments;
private String email;
private String body;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getId_comments() {
        return id_comments;
    }

    public void setId_comments(int id_comments) {
        this.id_comments = id_comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
