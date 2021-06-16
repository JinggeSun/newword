package com.item.generate.util;

import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestA {

    public static void main(String[] args) {
        add(null);
    }

    /**
     *
     * @param a
     */
    public static void add( String a){
        if (a == null) {
            throw new NullPointerException();
        }
        SimpleDateFormat sf = new SimpleDateFormat(a);
        String format = sf.format(new Date());
        System.out.println(format);
    }
}
