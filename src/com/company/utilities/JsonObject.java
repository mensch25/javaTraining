package com.company.utilities;

public abstract class JsonObject {

    static int id;
    String type;

    public String toJsonString() {
        return new JsonParser().classToJsonString(this);
    }


}
