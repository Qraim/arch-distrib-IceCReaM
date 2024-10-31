package com.company.crm;

import java.util.ArrayList;
import java.util.List;

public class InternalCRMServiceImpl implements InternalCRMService.Iface {

    private List<InternalLeadDto> leads = new ArrayList<>();

    @Override
    public List<InternalLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {

        List<InternalLeadDto> result = new ArrayList<>();
        for (InternalLeadDto lead : leads) {
            if (lead.getAnnualRevenue() >= lowAnnualRevenue && lead.getAnnualRevenue() <= highAnnualRevenue && lead.getState().equals(state)) {
                result.add(lead);
            }
        }
        return result;
    }

    public List<InternalLeadDto> getLeadList() {
        return leads;
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(long startDate, long endDate) {
        List<InternalLeadDto> result = new ArrayList<>();
        for (InternalLeadDto lead : leads) {
            if (lead.getCreationDate() != null && !lead.getCreationDate().isEmpty()) {
                long creationDate = Long.parseLong(lead.getCreationDate());
                if (creationDate >= startDate && creationDate <= endDate) {
                    result.add(lead);
                }
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
        leads.add(leadDto);
    }
}
