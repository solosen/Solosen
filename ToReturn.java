package com.example.projectuno.t;

public class ToReturn {
    private CinemaSeat returned_ticket;

    public ToReturn(CinemaSeat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public void setReturned_ticket(CinemaSeat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public CinemaSeat getReturned_ticket() {
        return returned_ticket;
    }
}
