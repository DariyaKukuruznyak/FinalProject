package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

public class Bookmaker extends User{
    public Bookmaker() {
        this.userRole = UserRole.BOOKMAKER;
    }
}
