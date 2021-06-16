package demo.pwd;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 加密
 */
public class DruidPwd {

    /**
     * 私钥，禁止修改
     */
    public static final String PRIVATE_KEY = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA6+4avFnQKP+O7bu5YnxWoOZjv3no4aFV558HTPDoXs6EGD0HP7RzzhGPOKmpLQ1BbA5viSht+aDdaxXp6SvtMQIDAQABAkAeQt4fBo4SlCTrDUcMANLDtIlax/I87oqsONOg5M2JS0jNSbZuAXDv7/YEGEtMKuIESBZh7pvVG8FV531/fyOZAiEA+POkE+QwVbUfGyeugR6IGvnt4yeOwkC3bUoATScsN98CIQDynBXC8YngDNwZ62QPX+ONpqCel6g8NO9VKC+ETaS87wIhAKRouxZL38PqfqV/WlZ5ZGd0YS9gA360IK8zbOmHEkO/AiEAsES3iuvzQNYXFL3x9Tm2GzT1fkSx9wx+12BbJcVD7AECIQCD3Tv9S+AgRhQoNcuaSDNluVrL/B/wOmJRLqaOVJLQGg==";

    /**
     * 公钥，禁止修改。跟application-dev.yml 的public-key 一致
     */
    public static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOvuGrxZ0Cj/ju27uWJ8VqDmY7956OGhVeefB0zw6F7OhBg9Bz+0c84RjzipqS0NQWwOb4kobfmg3WsV6ekr7TECAwEAAQ==";

    /**
     * 启动类
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        /**
         * 明文密码
         */
        String enablePwd = "youpassword";


        /**
         * 对密码进行加密
         */
        String pwd1 = encrypt(enablePwd);
        System.out.println("加密前密码：" + enablePwd);
        System.out.println("加密后密码：" + pwd1);

        /**
         * 对密码进行解密
         */
        String pwd2 = decrypt("XO1875h7nJQNpX/znrL9GAhszhc7Gx4DzmNL7OPHNw5DN/proxEZr8SnB80BF/aHlFtegSPzJIFZAUFce7WCPA==");
        System.err.println("*****************");
        System.err.println("加密前密码：" + pwd1);
        System.err.println("加密后密码：" + pwd2);

    }

    /**
     * 加密
     * @param password 明文密码
     * @return
     * @throws Exception
     */
    public static String encrypt (String password)throws Exception{
        return ConfigTools.encrypt(PRIVATE_KEY,password);
    }

    /**
     * 解密
     * @param password 加密密码
     * @return
     * @throws Exception
     */
    public static String decrypt (String password)throws Exception{
        return ConfigTools.decrypt(PUBLIC_KEY, password);
    }



}
