package com.ac.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    Company getCompany(Long id);
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);
}
