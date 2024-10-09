namespace java com.company.crm

struct ModelTO {
  1: string firstName,
  2: string lastName,
  3: double annualRevenue,
  4: string phone,
  5: string street,
  6: string postalCode,
  7: string city,
  8: string country,
  9: i64 creationDate,
  10: string company,
  11: string state
}

struct InternalLeadDto {
   1: string firstName,
   2: string lastName,
   3: double annualRevenue,
   4: string phone,
   5: string street,
   6: string postalCode,
   7: string city,
   8: string country,
   9: string creationDate,
   10: string company,
   11: string state
}

service InternalCRMService {
  list<InternalLeadDto> findLeads(1: double lowAnnualRevenue, 2: double highAnnualRevenue, 3: string state),
  list<InternalLeadDto> findLeadsByDate(1: i64 startDate, 2: i64 endDate),
  void deleteLead(1: InternalLeadDto lead),
  void addLead(1: InternalLeadDto leadDto)
}
