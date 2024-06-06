package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    CustomerService customerService = new CustomerService();
    ReservationService reservationService = new ReservationService();
    public Customer getCustomer(String email)
    {
        return customerService.getCustomer(email);

    }

    public void addRoom(List<IRoom> rooms)
    {
        for(IRoom room: rooms)
        {
            if(room != null)
            {
                reservationService.addRoom(room);
            }

        }

    }

    public Collection<IRoom> getAllRooms()
    {
        return reservationService.getAllRooms();
    }


    public Collection<Customer> getAllCustomers()
    {
        return customerService.getAllCustomer();
    }
    public void displayAllReservations()
    {
        reservationService.printAllReservation();
    }
}
