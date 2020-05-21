package com.example.demo.model;


import java.util.HashMap;
import java.util.Map;

//该类型在controller层返回一些复杂变量时使用，比如同时返回一篇文章及该文章对应的所有评论
public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();

    public void set(String key,Object value){
        objs.put(key,value);
    }

    public Object get(String key){
        return objs.get(key);
    }

}
