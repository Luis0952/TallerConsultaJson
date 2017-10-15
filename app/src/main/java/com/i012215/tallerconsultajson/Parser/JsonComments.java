package com.i012215.tallerconsultajson.Parser;

import com.i012215.tallerconsultajson.Models.ModelComments;
import com.i012215.tallerconsultajson.Models.ModelPosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class JsonComments {

    public static List<ModelComments> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ModelComments> commentModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);

            ModelComments commentsModel = new ModelComments();
            commentsModel.setId_comments(item.getInt("id"));
            commentsModel.setPostid(item.getInt("postId"));
            commentsModel.setEmail(item.getString("email"));
            commentsModel.setBody(item.getString("body"));
            commentModelList.add(commentsModel);
        }
        return commentModelList;
    }
}
