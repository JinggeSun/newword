package com.item.manager.util;

import java.util.UUID;

/**
 * @author zcm
 */
public class UUIDUtil {

    /**
     * 使用uuid作为唯一嘛
     * @return
    */
    public static String getUniqueId (){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUniqueId());
    }
}
