package com.item;

import com.item.impl.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

/**
 * @author zcm
 */
public class MyThriftServer {

    private static TServer tServer;

    public MyThriftServer(){
        if (tServer == null){
            try {
                TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
                THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
                PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<PersonServiceImpl>(new PersonServiceImpl());

                arg.protocolFactory(new TCompactProtocol.Factory());
                arg.transportFactory(new TFramedTransport.Factory());
                arg.processorFactory(new TProcessorFactory(processor));
                tServer = new THsHaServer(arg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void startThriftServer(){
        System.out.println("server start....");
        tServer.serve();
    }

    public void stopThriftServer(){
        System.out.println("server stop....");
        tServer.stop();
        tServer = null;
    }

    public static void main(String[] args) {
        MyThriftServer myThriftServer = new MyThriftServer();
        myThriftServer.startThriftServer();
    }
}
