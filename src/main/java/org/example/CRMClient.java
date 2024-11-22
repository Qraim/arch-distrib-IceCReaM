package org.example;

import com.company.crm.InternalCRMService;
import com.company.crm.InternalLeadDto;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class CRMClient {

    public static void main(String[] args) {
        TTransport transport = null;

        try {
            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            InternalCRMService.Client client = new InternalCRMService.Client(protocol);

            InternalLeadDto newLead = new InternalLeadDto();
            newLead.setName("Jean, Dupont");
            newLead.setAnnualRevenue(50000.0);
            newLead.setPhone("0123456789");
            newLead.setStreet("10 rue des Lilas");
            newLead.setPostalCode("75001");
            newLead.setCity("Paris");
            newLead.setCountry("France");
            newLead.setCreationDate("20241118");
            newLead.setCompany("Exemple S.A.");
            newLead.setState("IDF");

            System.out.println("Ajout d'un nouveau lead...");
            client.addLead(newLead);

            System.out.println("Recherche de leads avec un revenu entre 40000 et 60000...");
            List<InternalLeadDto> leads = client.findLeads(40000, 60000, "IDF");

            for (InternalLeadDto lead : leads) {
                System.out.println("Lead trouvé : " + lead.getName() + ", Revenu annuel : " + lead.getAnnualRevenue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }
}
