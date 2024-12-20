package org.example;

import com.company.generated.InternalLeadDto;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import com.company.generated.InternalCRMService;
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

            client.addLead(newLead);

            List<InternalLeadDto> leads = client.findLeads(40000, 60000, "IDF");

            for (InternalLeadDto lead : leads) {
                System.out.println("Lead : " + lead.getName() + ", Revenu : " + lead.getAnnualRevenue());
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
