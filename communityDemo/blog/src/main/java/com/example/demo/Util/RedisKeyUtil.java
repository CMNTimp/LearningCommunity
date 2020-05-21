package com.example.demo.Util;


//该类用于生成REDIS中的KEY，保证每一个KEY的名字有规律性，并能反应该KEY的基本信息
public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";

    //之所以加entityType是因为article和comment都是可以被点赞的
    public static String getLikeKey(int entityType,int entityId){
        return BIZ_LIKE + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
}
