package com.item.generate.util;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 用户随机生成器
 *
 * @author zcm
 */
public class RandomUtil {

    private static final String USER_NAME = "myUser";
    private static List<String> phoneList = new ArrayList<String>();
    private static final Integer COUNT = 100;
    private static List<String> userNameList = new ArrayList<String>();
    private static final Integer TIME_INTERVAL = 1000;
    private static final Integer FLOW = 5000;


    private static List<Map<Integer, List<Integer>>> menuList = new ArrayList<>();

    private static List<Double> versionList = new ArrayList<Double>();


    static {
        for (int i = 0; i < COUNT; i++) {
            String val = "";
            if (i < 10) {
                val = "00" + i;
            } else if (i < 100) {
                val = "0" + i;
            } else {
                val = "" + i;
            }
            userNameList.add(USER_NAME + val);
        }

        phoneList.add("iOS");
        phoneList.add("Android");
        phoneList.add("PC");

        versionList.add(1.0);
        versionList.add(2.0);
        versionList.add(3.0);

        /**
         * 主菜单
         * 1～10
         */
        for (int i = 0; i < 10; i++) {
            Integer menu = i + 1;
            List<Integer> childList = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                Integer child = j + 1;
                childList.add(child + (menu - 1) * 10);
            }

            Map<Integer, List<Integer>> listMap = new HashMap<>();
            listMap.put(menu, childList);

            menuList.add(listMap);
        }

    }

    private static List<Integer> getMenu() {
        Random random = new Random();
        int index = random.nextInt(menuList.size());
        List<Integer> child = menuList.get(index).get(index + 1);
        int indexChild = random.nextInt(child.size());
        Integer childValue = child.get(indexChild);
        return Arrays.asList(index + 1, childValue);
    }


    private static String getUser() {
        Random random = new Random();
        int index = random.nextInt(COUNT);
        return userNameList.get(index);
    }

    private static String getDevice() {
        Random random = new Random();
        int index = random.nextInt(phoneList.size());
        return phoneList.get(index);
    }

    private static Integer getInterval() {
        Random random = new Random();
        int index = random.nextInt(50);
        return index * TIME_INTERVAL;
    }

    private static Integer getFlow() {
        Random random = new Random();
        int index = random.nextInt(10);
        return index * FLOW;
    }

    private static Double getVersion() {
        Random random = new Random();
        int index = random.nextInt(versionList.size());
        return versionList.get(index);
    }

    private static Long getTimeStamp() {
        return System.currentTimeMillis();
    }


    public static String getRandomIp() {

        //ip范围
        //36.56.0.0-36.63.255.255
        //61.232.0.0-61.237.255.255
        //106.80.0.0-106.95.255.255
        //121.76.0.0-121.77.255.255
        //123.232.0.0-123.235.255.255
        //139.196.0.0-139.215.255.255
        //171.8.0.0-171.15.255.255
        //182.80.0.0-182.92.255.255
        //210.25.0.0-210.47.255.255
        //222.16.0.0-222.95.255.255
        int[][] range = {{607649792, 608174079},
                {1038614528, 1039007743},
                {1783627776, 1784676351},
                {2035023872, 2035154943},
                {2078801920, 2079064063},
                {-1950089216, -1948778497},
                {-1425539072, -1425014785},
                {-1236271104, -1235419137},
                {-770113536, -768606209},
                {-569376768, -564133889},
        };

        Random rdInt = new Random();
        int index = rdInt.nextInt(10);
        return num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
    }

    private static String num2ip(int ip) {
        int [] b=new int[4] ;
        String x = "";
        b[0] = (int)((ip >> 24) & 0xff);
        b[1] = (int)((ip >> 16) & 0xff);
        b[2] = (int)((ip >> 8) & 0xff);
        b[3] = (int)(ip & 0xff);
        x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]);
        return x;
    }


    public static String getLog(){
        StringBuilder sb = new StringBuilder();
        //时间戳
        sb.append(getTimeStamp()).append(" ");
        //停留时间
        sb.append(getInterval()).append(" ");
        //ip
        sb.append(getRandomIp()).append(" ");
        //菜单
        List<Integer> menu = getMenu();
        sb.append(menu.get(0)).append(" ");
        sb.append(menu.get(1)).append(" ");
        //用户
        sb.append(getUser()).append(" ");
        //流量
        sb.append(getFlow()).append(" ");
        //版本
        sb.append(getVersion()).append(" ");
        //设备
        sb.append(getDevice());

        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(getLog());
    }

}
