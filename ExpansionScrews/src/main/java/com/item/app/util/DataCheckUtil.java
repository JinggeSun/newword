package com.item.app.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zcm
 */
public class DataCheckUtil {

    /**
     *
     * @param stringParam
     * @return
     */
    public static boolean checkStringEmpty(String ... stringParam){
        AtomicBoolean flag = new AtomicBoolean(true);

        Arrays.stream(stringParam).forEach(param->{
            if (StringUtils.isEmpty(param)){
                flag.set(false);
            }
        });

        return flag.get();
    }
}
