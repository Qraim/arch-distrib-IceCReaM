package org.example;

import com.company.crm.InternalCRMService;
import com.company.crm.InternalCRMServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class CRMServer {

    public static void main(String[] args) {
        try {
            InternalCRMServiceImpl service = new InternalCRMServiceImpl();

            InternalCRMService.Processor<InternalCRMServiceImpl> processor =
                    new InternalCRMService.Processor<>(service);

            TServerTransport serverTransport = new TServerSocket(9090);

            TServer server = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(processor));

            System.out.println("Serveur CRM démarré sur le port 9090...");

            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
