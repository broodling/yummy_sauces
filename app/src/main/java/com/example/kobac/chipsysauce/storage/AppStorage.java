package com.example.kobac.chipsysauce.storage;

import android.content.Context;

import com.example.kobac.chipsysauce.utils.HashMapConversionUtil;
import com.example.kobac.chipsysauce.utils.SharedStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the application data.
 */
public class AppStorage {

    private static final String FAVORITE_KEY = "storage::favorite_key";

    /**
     * Stores the favorite state of the sauce into the list.
     *
     * @param context The application context.
     * @param id      The Id of the favorite sauce.
     * @param state   True if the sauce is favorite, false otherwise.
     */
    public static void storeFavorite(final Context context, final String id, final boolean state) {
        // 1. Get from storage
        Map<String, Object> favorites = getFavorites(context);

        // 2. Save new state
        favorites.put(id, state);

        JSONObject favoriteJson = null;
        try {
            favoriteJson = HashMapConversionUtil.getJsonFromMap(favorites);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 3. Save new list to storage
        SharedStorage.put(context, FAVORITE_KEY, favoriteJson.toString());
    }

    /**
     * Get the list of favorites.
     *
     * @param context
     * @return
     */
    public static Map<String, Object> getFavorites(final Context context) {
        Map<String, Object> favorites = null;
        JSONObject favoriteJson;

        // 1. Get as String
        String favoriteJsonString = SharedStorage.getString(context, FAVORITE_KEY, "");

        // 2. Convert to Json
        try {
            favoriteJson = new JSONObject(favoriteJsonString);

            // 3. Convert Json to HashMap
            favorites = HashMapConversionUtil.jsonToMap(favoriteJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (favorites == null) {
            return new HashMap<>();
        }

        return favorites;
    }

    public static boolean isFavorite(final Context context, final String id) {
        Map<String, Object> favorites = getFavorites(context);

        if (!favorites.containsKey(id)) {
            return false;
        }

        return (boolean) favorites.get(id);
    }


    //public static void saveLikeState
}
