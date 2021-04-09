package com.steph.api.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class ReferenceOptions {

    public final String key = "ReferenceOptions";

    public final String label = "Reference Options";

    private ArrayList<Options> options;

    public void setOptions(String key, String value) {
        if(options == null) {
            options = new ArrayList<>();
        }
        options.add(new Options(key, value));
    }

    public ArrayList<Options> getOptions() {
        return options;
    }

    static class Options {
        public String uuid;

        public String name;

        public Options(String uuid, String name) {
            this.uuid = uuid;
            this.name = name;
        }
    }
}

