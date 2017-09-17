package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

public class RiskController extends User {
    public RiskController() { this.userRole = UserRole.RISK_CONTROLLER;
    }
}
