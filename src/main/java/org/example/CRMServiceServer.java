package org.example;

import com.company.crm.InternalCRMService;
import com.company.crm.InternalCRMServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class CRMServiceServer {

    public static void main(String[] args) {
        try {
            InternalCRMService.Processor processor = new InternalCRMService.Processor(new InternalCRMServiceImpl());
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the CRM Service Server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
