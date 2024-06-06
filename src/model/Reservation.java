package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
    {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "checkInDate=" + checkInDate +
                ", customer=" + customer +
                ", room=" + room +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
