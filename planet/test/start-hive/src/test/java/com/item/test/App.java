package com.item.test;

import com.item.hive.entity.DbInfo;
import org.apache.hadoop.hive.metastore.api.MetaException;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> resList  = getAllDataBases();
        System.out.println(resList);
    }

    public static List<String> getAllDataBases(){
        List<String> resList = new ArrayList<>(5);
        try {
            resList.add("e");
            assert resList != null && resList.size() > 0;
            resList.forEach(s->{
                System.err.println(s);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }
}
