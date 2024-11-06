package com.company.crm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternalCRMServiceImpl implements InternalCRMService.Iface {

    private List<ModelTO> leads = new ArrayList<>();


    public InternalLeadDto Convert(ModelTO model){
        InternalLeadDto dto = new InternalLeadDto();
        dto.setName(model.getFirstName() + ", " + model.getLastName());
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
        dto.setCreationDate(Long.parseLong(String.valueOf(leadDto.getCreationDate())));
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

    public List<InternalLeadDto> getLeadList() {
        List<InternalLeadDto> result = new ArrayList<>();
        for (ModelTO lead : leads) {
            result.add(Convert(lead));
        }
        return result;
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(long startDate, long endDate) {
        List<InternalLeadDto> result = new ArrayList<>();
        for (ModelTO lead : leads) {
            if (lead.getCreationDate() >= startDate && lead.getCreationDate() <= endDate) {
                result.add(Convert(lead));
            }
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
