package com.example.guillaume_grand_clement.herowars.data.pojo;

import io.realm.RealmObject;

public class SamplePojo extends RealmObject{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
