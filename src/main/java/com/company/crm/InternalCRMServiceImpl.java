package com.company.crm;

import java.util.ArrayList;
import java.util.List;

public class InternalCRMServiceImpl implements InternalCRMService.Iface {
    @Override
    public List<InternalLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {


        return new ArrayList<>();
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(long startDate, long endDate) {
        return new ArrayList<>();
    }

    @Override
    public void deleteLead(InternalLeadDto lead) {
    }

    @Override
    public void addLead(InternalLeadDto leadDto) {
    }
}
