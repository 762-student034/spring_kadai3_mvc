package com.example.demo.model;

import java.util.List;

public class RandomResult {
    private List<String> list;
    private String errMessage;

    // constructor
    public RandomResult(List<String> list, String errMessage) {
        this.list = list;
        this.errMessage = errMessage;
    }

    // getters
    public List<String> getList() { return list; }
    public String getErrMessage() { return errMessage; }
}

