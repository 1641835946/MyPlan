package com.example.myapp.model;

/**
 * Created by Administrator on 2016/4/30.
 */
public class Summary {

    private long time;

    private String conclusion;

    public void setTime(long time) {
        this.time = time;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public long getTime() {
        return time;
    }

    public String getConclusion() {
        return conclusion;
    }
}

