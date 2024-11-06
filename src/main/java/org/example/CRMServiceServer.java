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
            server.serve();
            System.out.println("CRM Service started on port 9090");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
