package com.i012215.tallerconsultajson.Parser;

import com.i012215.tallerconsultajson.Models.ModelPosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class JsonPosts {

    public static List<ModelPosts> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ModelPosts> postModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);

            ModelPosts postModel = new ModelPosts();
            postModel.setId_post(item.getInt("id"));
            postModel.setUserid(item.getInt("userId"));
            postModel.setTitle(item.getString("title"));
            postModel.setBody(item.getString("body"));
            postModelList.add(postModel);
        }
        return postModelList;
    }
}
