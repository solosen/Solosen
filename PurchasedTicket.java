package com.example.projectuno.t;

import java.util.UUID;

public class PurchasedTicket {
    private String token;
    private CinemaSeat ticket;

    public PurchasedTicket(CinemaSeat ticket) {
        this.ticket = ticket;
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public CinemaSeat getTicket() {
        return ticket;
    }

    public void setTicket(CinemaSeat ticket) {
        this.ticket = ticket;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
