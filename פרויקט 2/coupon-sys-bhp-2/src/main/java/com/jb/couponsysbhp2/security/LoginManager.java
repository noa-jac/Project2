package com.jb.couponsysbhp2.security;

import com.jb.couponsysbhp2.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {

    private final ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case ADMIN:
                AdminService adminService = ctx.getBean(AdminService.class);
                return ((AdminServicesImpl) adminService).login(email, password) ? (ClientService) adminService : null;
            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                return ((CompanyServiceImpl) companyService).login(email, password) ? (ClientService) companyService : null;
            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                return ((CustomerServiceImpl) customerService).login(email, password) ? (ClientService) customerService : null;
            default:
                break;
        }
        return null;
    }

}