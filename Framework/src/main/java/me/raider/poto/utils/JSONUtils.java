package me.raider.poto.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JSONUtils {

    private File file;
    private JSONObject json;
    private final JSONParser parser = new JSONParser();

    public JSONUtils(File file) {
        this.file = file;
        reload();
    }

    public void reload() {

        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getRawData(String key) {
        return json.containsKey(key) ? json.get(key).toString() : null;
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getRawData(key));
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public double getInteger(String key) {
        try {
            return Integer.parseInt(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public JSONObject getObject(String key) {
        return json.containsKey(key) ? (JSONObject) json.get(key) : null;
    }

    public JSONArray getArray(String key) {
        return json.containsKey(key) ? (JSONArray) json.get(key) : null;
    }


}
