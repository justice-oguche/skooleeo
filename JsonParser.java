package com.example.skooleeo_gis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<String,String> parseJsonObject(JSONObject object){
        //initialize hash map
        HashMap<String,String> dataList =  new HashMap<>();
        //get name from object
        try {
            String name = object.getString("name");

            //get latitude from object
            String latitude = object.getJSONObject("geometry").getJSONObject("location").getString("lat");
            //get longitude from object
            String longitude = object.getJSONObject("geometry").getJSONObject("location").getString("lng");


            //add all values to hash maps
            dataList.put("name",name);
            dataList.put("lat",latitude);
            dataList.put("lng",longitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;

    }

    private List<HashMap<String,String>> parserJsonArray(JSONArray jsonArray){
        List<HashMap<String,String>> dataList = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            //initialize hash map
            try {
                HashMap<String,String> data = parseJsonObject((JSONObject) jsonArray.get(i));
                dataList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    public List<HashMap<String,String>> parseResult(JSONObject object){
        JSONArray jsonArray = null;
        //get result
        try {
            jsonArray = object.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parserJsonArray(jsonArray);
    }


}
