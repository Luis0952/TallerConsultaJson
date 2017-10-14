package com.i012215.tallerconsultajson.Parser;

import com.i012215.tallerconsultajson.Models.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class JsonUser {

    public static List<ModelUser> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ModelUser> UserList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            JSONArray company = item.getJSONArray("company");
            JSONObject companyItem = company.getJSONObject(0);

            JSONArray address = item.getJSONArray("address");
            JSONObject addressItem = address.getJSONObject(0);

            ModelUser userDetail = new ModelUser();

            userDetail.setId_user(item.getInt("id"));
            userDetail.setName(item.getString("name"));
            userDetail.setAddress(addressItem.getString("address"));
            userDetail.setUsername(companyItem.getString("name"));


            UserList.add(userDetail);
        }
        return UserList;
    }
}
