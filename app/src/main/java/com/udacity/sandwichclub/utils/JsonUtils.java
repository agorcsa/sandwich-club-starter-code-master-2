package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // Checking if the json is empty
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        Sandwich sandwich = null;

        // JSON parsing - extracting the strings from the JSON objects
        try {
            JSONObject root = new JSONObject(json);
            JSONObject name = root.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            String placeOfOrigin = root.getString("placeOfOrigin");
            String description = root.getString("description");
            String image = root.getString("image");
            JSONArray ingredientsArray = root.getJSONArray("ingredients");

            // Looping in the alsoKnownArray and and creating the list of Strings alsoKnownArrayList
            List<String> alsoKnownAsArrayList = null;
            // Checking if the alsoKnownArray is not empty
            if (alsoKnownAsArray.length() > 0) {
                alsoKnownAsArrayList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAsArrayList.add(alsoKnownAsArray.getString(i));
                }
            }

            // Looping in the ingredientsArray and and creating the list of Strings ingredientsArrayList
            List<String> ingredientsArrayList = null;
            // Checking if the ingredientsArray is not empty
            if (ingredientsArray.length() > 0) {
                ingredientsArrayList = new ArrayList<>();
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredientsArrayList.add(ingredientsArray.getString(i));
                }
            }

            // Creating the sandwich object which has parameters that we have just parsed from JSON
            sandwich = new Sandwich(mainName, alsoKnownAsArrayList, placeOfOrigin, description, image, ingredientsArrayList);

        // Catch exception in case the JSON parsing is unsuccessful and avoid app crash
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

}

