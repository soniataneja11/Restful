package demo.utilies;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PayloadReader {

    public static <O> O replaceNestedJsonKey(O object, Object key, Object value) {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            Set<String> jsonKeys = jsonObject.keySet();
            for (String jsonKey: jsonKeys) {
                if (key.equals(jsonKey)) {
                    jsonObject.replace(key, value);
                } else {
                    replaceNestedJsonKey(jsonObject.get(jsonKey), key, value);
                }
            }
        } else if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (Object jsonArrayValue: jsonArray) {
                replaceNestedJsonKey(jsonArrayValue, key, value);
            }
        }
        return object;
    }

    public static JSONArray replaceJSONArrayValueAt(JSONArray array, Integer index, Object key, Object value) {
        JSONArray jsonArray = (JSONArray) array;
        Object item = jsonArray.get(index);
        replaceNestedJsonKey(item, key, value);
        return array;
    }

    public static Object getKey(JSONArray array, Object key, Object value) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject item = (JSONObject) array.get(i);
            if (item.keySet().contains(key)) {
                item.replace(key, value);
                return array;
            }
        }
        return array;
    }

    public static <AnyValue> JSONObject getJsonKey(JSONObject jsonobject, String objectArrayKey, String objectKey, AnyValue objectValue) {
        if (objectArrayKey == null || !(jsonobject.keySet().contains(objectArrayKey))) {
            replaceNestedJsonKey(jsonobject, objectKey, objectValue);

        }
        JSONArray jsonarray = new JSONArray();

        if ((null != objectArrayKey) && (jsonobject.keySet().contains(objectArrayKey))) {
            jsonarray = (JSONArray) jsonobject.get(objectArrayKey);
        }
        if (null != jsonarray) {

            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject jsonobject2 = (JSONObject) jsonarray.get(i);
                System.out.println("jsonobject First:::" + jsonobject2);
                if (jsonobject2.containsKey(objectKey)) {
                    replaceNestedJsonKey(jsonobject2, objectKey, objectValue);
                }
                System.out.println("JSONOBJECT Final::" + jsonobject2 + "JSONOBJECTTTT");
            }
        }

        System.out.println("FINAL OBJECT::" + jsonobject );
        return jsonobject;
    }

    public static JSONObject getJsonKey(JSONObject jsonobject, String objectKey, String objectValue, String objectArrayKey, String objectArrayValue) {
        if ((jsonobject.keySet().contains(objectKey))) {
            replaceNestedJsonKey(jsonobject, objectKey, objectValue);
        }
        System.out.println("objectKey:: " + objectKey + "  objectValue::" + objectValue);
        System.out.println("objectArrayKey::" + objectArrayKey);
        JSONArray jsonarray = new JSONArray();
        if ((null != objectArrayKey) && (jsonobject.keySet().contains(objectArrayKey))) {
            jsonarray = (JSONArray) jsonobject.get(objectArrayKey);
        }
        if (null != jsonarray) {
            jsonarray.clear();
            jsonarray.add(objectArrayValue);
            System.out.println("objectArrayValues::" + objectArrayValue);
        }
        System.out.println("JSONARRAYY:::" + jsonarray + "/////////////////////////////");
        System.out.println("FINAL OBJECT::" + jsonobject + "/////////////////////////////");
        return jsonobject;
    }

    public static JSONArray getJsonArrayKey(JSONArray array, Object objectKey, Object objectValue) throws ParseException {
        System.out.println("KEY=" + objectKey + " AND \n VALUE=" + objectValue);
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(array.toJSONString().replaceAll("(?<=\"" + objectKey + "\":\")(\\w+)", String.valueOf(objectValue)));
        System.out.println("FINAL JSONARRAY IS=\n" + jsonArray);
        return jsonArray;
    }

    private File getJsonFile(String fileName) {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
    }

    public JSONObject readJsonFromFile(String fileName) {
        Object jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            jsonObject = parser.parse(new FileReader(getJsonFile(fileName)));
            System.out.println("****** Reading payload from :::: " + fileName);
            
        } catch (Exception ex) {
            //throw some error if you want
        }
        return (JSONObject) jsonObject;
    }

    public JSONObject readJsonFromString(String json) {
        Object jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            jsonObject = parser.parse(json);
        } catch (Exception ex) {
            //throw some error if you want
        }
        return (JSONObject) jsonObject;
    }

    public JSONArray readJsonArrayFromFile(String fileName) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(getJsonFile(fileName)));
            System.out.println("****** Reading payload from :::: " + fileName);
        } catch (Exception ex) {
            //throw some error if you want
        }
        return (JSONArray) jsonArray;
    }

    public JSONArray readJsonArrayFromString(String string) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray)parser.parse(string);
        } catch (Exception ex) {
            //throw some error if you want
        }
        return (JSONArray) jsonArray;
    }

    public static String getJsonObject(JSONArray jsonArray, String keyName, String value) {
        LinkedHashMap<Object, Object> object = null;
        for(int i = 0; i < jsonArray.size(); i++) {
            object = (LinkedHashMap<Object, Object>) jsonArray.get(i);
            if(object.containsKey(keyName) && (object.get(keyName).toString()).equalsIgnoreCase(value)) {
                break;
            }
        }
        LinkedHashMap<Object, Object> finalObject = object;
        String mapAsString = object.keySet().stream()
                .map(key -> key + "=" + finalObject.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public static void replaceKeyValue(JSONArray array, String keyName, Object[] values) {
        for(int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            object.replace(keyName, values[i]);
        }
    }

    public static void removeKey (JSONArray array, String keyName) {
        for(int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            object.remove(keyName);
        }
    }

    public static void addKeyValue (JSONArray array, String keyName, String keyValue) {
        JSONObject row = new JSONObject();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            object.put(keyName, keyValue);
        }
    }

    public static JSONArray copyArray(JSONArray array) {
        JSONArray newArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject newObject;
            newObject = (JSONObject) ((JSONObject) array.get(i)).clone();
            newArray.add(newObject);
        }
        return newArray;
    }

    public static String getValue(JSONArray array, String keyName) {
        String value = "";
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            value = (String) object.get(keyName);
        }


        return value;
    }

}
