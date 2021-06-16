package com.item.hbase.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zcm
 */
@ConfigurationProperties(prefix = "myhbase")
public class MyHbaseProperties {

    private String zookeeperIp;
    private String zookeeperPort;

    public void setZookeeperIp(String zookeeperIp) {
        this.zookeeperIp = zookeeperIp;
    }

    public void setZookeeperPort(String zookeeperPort) {
        this.zookeeperPort = zookeeperPort;
    }

    public String getZookeeperIp() {
        return zookeeperIp;
    }

    public String getZookeeperPort() {
        return zookeeperPort;
    }
}
