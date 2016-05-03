package com.example.myapp.model;

/**
 * Created by Administrator on 2016/4/30.
 */
public class Item {

    private boolean isCheck;

    private String content;

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public String getContent() {
        return  content;
    }
}

