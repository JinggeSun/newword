package com.item.manager.util;

import com.item.manager.config.ShiroConfig;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author zcm
 */
public class ShiroUtil {

    /**
     * 盐
     * @return
     */
    public static String getSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * 生成的密文，使用md5算法对明文与盐值的组合进行了三次加密
     * @return
     */
    public static String getAlg(String password,String salt){
        return new Md5Hash(password,salt, ShiroConfig.getHashIterations()).toString();
    }

    public static void main(String[] args) {
        System.out.println(getAlg("12345678","efb5cbbd988ab9f46258a100a77a0fbc"));
    }
}

