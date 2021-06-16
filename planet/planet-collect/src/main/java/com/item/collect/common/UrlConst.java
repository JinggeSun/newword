package com.item.collect.common;

/**
 * @author zcm
 */
public class UrlConst {

    private static final String BASE_URL = "http://localhost:50070/jmx?qry=";

    //NameNode
    /**
     * JVM监控项
     */
    private static final String NAMENODE_JVMMETRICS = "Hadoop:service=NameNode,name=JvmMetrics";

    /**
     * FSNamesystem
     */
    private static final String NAMENODE_FSNAMESYSTEM = "Hadoop:service=NameNode,name=FSNamesystem";

    /**
     * FSNamesystemState
     */
    private static final String NAMENODE_FSNAMESYSTEMSTATE = "Hadoop:service=NameNode,name=FSNamesystemState";

    /**
     * RPC
     */
    private static final String NAMENODE_RPC = "Hadoop:service=NameNode,name=RpcActivityForPort8022";

    /**
     * INFO
     */
    private static final String NAMENODE_INFO = "Hadoop:service=NameNode,name=NameNodeInfo";


    public static String getNamenodeFsnamesystem() {
        return BASE_URL + NAMENODE_FSNAMESYSTEM;
    }

    public static String getNamenodeFsnamesystemstate() {
        return BASE_URL + NAMENODE_FSNAMESYSTEMSTATE;
    }

    public static String getNamenodeInfo() {
        return BASE_URL + NAMENODE_INFO;
    }

    public static String getNamenodeJvmmetrics() {
        return BASE_URL + NAMENODE_JVMMETRICS;
    }

    public static String getNamenodeRpc() {
        return BASE_URL + NAMENODE_RPC;
    }
}
