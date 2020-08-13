package com.item;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author zcm
 */
public class MyThriftClient {

    public static void main(String[] args) {
        TTransport tTransport = new TFastFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            tTransport.open();

            //使用client
            Person person = client.getPersonByUserName("张三");
            System.err.println(person);

            Person person1 = new Person();
            person1.setUsername("张三");
            person1.setAge(23);
            person1.setMarried(false);
            client.savePerson(person1);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            tTransport.close();
        }
    }
}
