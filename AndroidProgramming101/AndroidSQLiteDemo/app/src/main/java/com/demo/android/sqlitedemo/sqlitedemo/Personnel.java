package com.demo.android.sqlitedemo.sqlitedemo;

public class Personnel {
    private String NAME = "";
    private String JOB = "";

    public Personnel (String name, String job) {
        NAME = name;
        JOB = job;
    }

    public void setName(String name) {
        NAME = name;
    }

    public void setJob(String job) {
        JOB = job;
    }

    public String getName() {
        return NAME;
    }

    public String getJob() {
        return JOB;
    }
}
