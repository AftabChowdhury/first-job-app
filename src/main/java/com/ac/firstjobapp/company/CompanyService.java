package com.ac.firstjobapp.company;

import com.ac.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
}
