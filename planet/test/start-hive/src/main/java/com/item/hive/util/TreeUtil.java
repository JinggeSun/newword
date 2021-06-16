package com.item.hive.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcm
 */
public class TreeUtil {

    private static List<Map<String,Object>> list = new ArrayList<>();

    static {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("label","root-A");
        map1.put("pid",0);
        list.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("label","root-B");
        map2.put("pid",0);
        list.add(map2);

        Map<String,Object> map1T1 = new HashMap<>();
        map1T1.put("id",3);
        map1T1.put("label","root-A-1");
        map1T1.put("pid",1);
        list.add(map1T1);

    }

    public static void main(String[] args) {
        System.out.println(test(list,0));
    }

    public static List<Map<String,Object>> test(List<Map<String,Object>> treeEntities,Integer cPid){
        List<Map<String,Object>> saveEntities = new ArrayList<>();
        for (Map<String,Object> map : treeEntities){
            Integer id = (Integer) map.get("id");
            String label = (String) map.get("label");
            Integer pid = (Integer) map.get("pid");
            if (pid.equals(cPid)){
                List<Map<String,Object>> saveList = test(treeEntities, id);
                if ( saveList.size() > 0){
                    map.put("children",saveList);
                }
                saveEntities.add(map);
            }
        }
        return saveEntities;
    }

}
