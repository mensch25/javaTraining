package com.utilities;

abstract public class JsonObject {

    static int id;
    String type;

    public String toJsonString() {
        return JsonParser.classToJsonString(this);
    }
}
