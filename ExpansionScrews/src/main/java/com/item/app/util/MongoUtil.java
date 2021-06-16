package com.item.app.util;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author zcm
 */
public class MongoUtil {
    /**
     * MongoDB连接对象
     */
    private MongoClient client;

    /**
     * 关闭连接对象
     */
    public void closeDB(){
        if(client != null){
            client.close();
        }
        client = null;
    }

    /**
     * 不需要认证获取连接对象
     */
    public void mongoClient(String host,int port){
        try {
            //获取mongodb连接对象
            client = new MongoClient(host,port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要认证获取连接对象
     */
    public void certifyMongoClient(String host,int port,String userName,String password,String db){
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress(host,port);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential(userName, db, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            //通过连接认证获取MongoDB连接
            client = new MongoClient(addrs,credentials);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库对象
     * @param databaseName  数据库名
     * @return  MongoDatabase
     */
    public MongoDatabase getDatabase(String databaseName){
        return client.getDatabase(databaseName);
    }





    public static void main(String[] args) {
        MongoUtil mongoUtil = new MongoUtil();
        mongoUtil.certifyMongoClient("localhost",27017,"admin","123456","admin");

        MongoDatabase mongoDatabase = mongoUtil.getDatabase("mating");
        MongoIterable<String> collectionNames = mongoDatabase.listCollectionNames();

        System.out.println(mongoDatabase.getName());

        for (String it: collectionNames){
            System.out.println(it);

            //获取指定集合对象
            MongoCollection<Document> haha = mongoDatabase.getCollection(it);
            //获取指定集合中所有文档(数据)
            System.out.println(haha.getNamespace().getCollectionName());
            System.out.println(haha.getNamespace().getDatabaseName());
            System.out.println(haha.getReadPreference().getName());


            Field[] fields = haha.getDocumentClass().getFields();
            for (Field field : fields) {
                System.out.println(field.getName());
            }

            FindIterable<Document> documents = haha.find();
            documents.forEach((Consumer<? super Document>) s->{
                System.out.println(s.toJson());
            });

            Document first = haha.find().first();
            assert first != null;
            Set<String> strings = first.keySet();
            System.err.println(strings.toString());
        }

        //获取表的表结构



        mongoUtil.closeDB();
    }
}
