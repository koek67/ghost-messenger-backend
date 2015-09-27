package data.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 * Created by koushikkrishnan on 9/27/15.
 */
public class JsonDecoder {

    public static JSONObject toJSONArray(String json) {

        Object object = JSONValue.parse(json);
        JSONObject array = (JSONObject) object;

        return array;
    }

}
