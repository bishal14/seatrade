package com.BishalJustin.dao;

import java.util.List;
import sea.CompanyApp;
public interface CompanyAppDao {
    void addCompanyApp(CompanyApp companyApp );
    CompanyApp getCompanyAppById(int id);

    List<CompanyApp> getAllCompanyApp();
    void  updateCompanyApp(CompanyApp companyApp);
    void  deleteCompanyApp(CompanyApp companyApp);
}
