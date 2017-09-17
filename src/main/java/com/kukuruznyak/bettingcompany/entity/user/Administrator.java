package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

public class Administrator extends User {
    public Administrator() {
        this.userRole= UserRole.ADMINISTRATOR;
    }
}
