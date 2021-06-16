package com.item.generate;

import com.item.generate.util.LogGenerator;

/**
 * @author zcm
 */
public class MainApp {

    private static String URL = "http://127.0.0.1:9700/log-server/collect";
    private static String CODE = "123456";

    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("使用默认参数");
        }else{
            System.out.println("使用传入参数");
            URL = args[0];
            CODE = args[1];
        }

        System.out.println(URL);
        System.out.println(CODE);

        try {
            LogGenerator.generator(URL,CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
