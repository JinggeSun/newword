package com.test;

import java.util.*;

/**
 * @author zcm
 */
public class LogDataTest {

    /**
     * 获取标题
     * @return
     */
    public static String[] getTitle (){
        return new String[]{"时间", "IP", "方法", "参数", "耗费时间","状态","消息"};
    }

    /**
     * 模拟数据
     * @return
     */
    public static List<Map<String,Object>> getMessage(){

        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> m1 = new HashMap<>(8);
        m1.put("time",new Date());
        m1.put("ip","192.168.1.10");
        m1.put("method","getGolden()");
        m1.put("param","key=vvv");
        m1.put("time_interval",1200);
        m1.put("status",1200);
        m1.put("msg","success");

        Map<String,Object> m2 = new HashMap<>(8);
        m2.put("time",new Date());
        m2.put("ip","192.168.1.10");
        m2.put("method","getGolden()");
        m2.put("param","key=vvv");
        m2.put("time_interval",1200);
        m2.put("status",1200);
        m2.put("msg","success");

        list.add(m1);
        list.add(m2);
        list.add(m1);
        list.add(m2);
        list.add(m1);

        return list;
    }

    public static String[][] getTableContent(){

        List<Map<String,Object>> list = new ArrayList<>();
        list = getMessage();

        String[][] content = new String[list.size()][list.get(0).size()];

        for (int i=0;i<list.size();i++){

            Map<String,Object> map = list.get(i);

            List<String> listMap = new ArrayList<>();
            for (String key : map.keySet()){
                listMap.add(map.get(key).toString());
            }

            for (int j=0;j<listMap.size();j++){
                content[i][j]= listMap.get(j);
            }

        }

        return content;
    }
}
