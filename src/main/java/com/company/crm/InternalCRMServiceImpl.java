package com.company.crm;

import com.company.generated.InternalLeadDto;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternalCRMServiceImpl implements com.company.generated.InternalCRMService.Iface {

    private List<ModelTO> leads = new ArrayList<>();

    public com.company.generated.InternalLeadDto Convert(ModelTO model){
        InternalLeadDto dto = new InternalLeadDto();
        dto.setName( model.getLastName() + ", " + model.getFirstName());
        dto.setAnnualRevenue(model.getAnnualRevenue());
        dto.setPhone(model.getPhone());
        dto.setStreet(model.getStreet());
        dto.setPostalCode(model.getPostalCode());
        dto.setCity(model.getCity());
        dto.setCountry(model.getCountry());
        dto.setCreationDate(String.valueOf(model.getCreationDate()));
        dto.setCompany(model.getCompany());
        dto.setState(model.getState());
        return dto;
    }

    public ModelTO Convert(InternalLeadDto leadDto){
        ModelTO dto = new ModelTO();
        List<String> Name = List.of(leadDto.getName().split(", "));
        dto.setFirstName(Name.get(0));
        dto.setLastName(Name.get(1));
        dto.setAnnualRevenue(leadDto.getAnnualRevenue());
        dto.setPhone(leadDto.getPhone());
        dto.setStreet(leadDto.getStreet());
        dto.setPostalCode(leadDto.getPostalCode());
        dto.setCity(leadDto.getCity());
        dto.setCountry(leadDto.getCountry());
        dto.setCreationDate(leadDto.getCreationDate());
        dto.setCompany(leadDto.getCompany());
        dto.setState(leadDto.getState());
        return dto;
    }

    @Override
    public List<InternalLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {

        List<InternalLeadDto> result = new ArrayList<>();
        for (ModelTO lead : leads) {
            if (lead.getAnnualRevenue() >= lowAnnualRevenue && lead.getAnnualRevenue() <= highAnnualRevenue && lead.getState().equals(state)) {
                result.add(Convert(lead));
            }
        }
        return result;
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(String startDate, String endDate) throws TException {

        List<InternalLeadDto> result = new ArrayList<>();
        for (ModelTO lead : leads) {
            if (lead.getCreationDate().compareTo(startDate) >= 0 && lead.getCreationDate().compareTo(endDate) <= 0) {
                result.add(Convert(lead));
            }
        }
        return result;
    }

    public List<InternalLeadDto> getLeadList() {
        List<InternalLeadDto> result = new ArrayList<>();
        for (ModelTO lead : leads) {
            result.add(Convert(lead));
        }
        return result;
    }


    @Override
    public void deleteLead(InternalLeadDto lead) {
        int index = leads.indexOf(lead);
        if (index != -1) {
            leads.remove(index);
        }
    }

    @Override
    public void addLead(InternalLeadDto leadDto) {
        leads.add(Convert(leadDto));
    }
}
