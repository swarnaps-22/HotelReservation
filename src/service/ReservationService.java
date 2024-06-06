package service;

import model.*;

import java.util.*;

public class ReservationService {

//    public static List<IRoom> listOfRooms = new ArrayList<>();
    public static List<Reservation> listOfReservationObj = new ArrayList<>();
    public static Map<IRoom ,List<Reservation>> listOfReservations = new HashMap<>();
    static List<IRoom>  allRoomsList;


    public void addRoom(IRoom room)
    {
        //Adds room_obj as key with value as empty list indicating room is available.


        listOfReservations.put(room,listOfReservationObj);



    }
    public List<IRoom> getAllRooms()
    {
        allRoomsList = new ArrayList<>();
        for(Map.Entry<IRoom,List<Reservation>> entry: listOfReservations.entrySet())
        {
            IRoom room = entry.getKey();
            //System.out.println(room);
            allRoomsList.add(room);
        }
        return allRoomsList;
    }

    public IRoom getRoomWithId(String roomId)
    {
        //Checks for Roomnumber in list of Reservations and returns room object.

        for(Map.Entry<IRoom,List<Reservation>> entry : listOfReservations.entrySet())
        {
            if(entry.getKey().getRoomNumber().equals(roomId))
            {
                return entry.getKey();
            }
        }
//        for(IRoom room :allRoomsList)
//        {
//            if(roomId.equals(room.getRoomNumber()));
//            return room;
//        }
        return null;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate , Date checkOutDate)
    {
        //find the room in the key and add the list of reservations as values in the hashmap
        // If no dates available  return not available

      //  Reservation reservationOfCustomer = null;
        Reservation reservationOfCustomer = new Reservation(customer,room,checkInDate,checkOutDate);
        listOfReservationObj.add(reservationOfCustomer);
        listOfReservations.put(room,listOfReservationObj);

        return reservationOfCustomer;
    }



    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate)
    {
        //Finds the available rooms list based on checIn and checkOut and add to list and return list of roomsAvailable on the dates.
        List<IRoom> RoomsAvailable = new ArrayList<>();
        for(Map.Entry<IRoom, List<Reservation>> entry : listOfReservations.entrySet() )
        {
            List<Reservation> reservation = entry.getValue();
            if(!reservation.isEmpty())
            {
                for(Reservation reservationobj : reservation)
                {
                    if(reservationobj.getCheckInDate().before(checkOutDate)&& reservationobj.getCheckOutDate().after(checkInDate))
                    {
                        System.out.println("Room available");
                        IRoom room = entry.getKey();
                        RoomsAvailable.add(room);
                    }


                }
            }
            else
            {
                RoomsAvailable.add(entry.getKey());
            }


        }

    return RoomsAvailable;

    }

    public Collection<Reservation> getCustomersReservation(Customer customer)
    {
        //Searches for the customer email in listofreservations and adds the reservation on the customer name to list and returns list
        List<Reservation> customersReservationDetails = new ArrayList<>();
//
        for(Map.Entry<IRoom,List<Reservation>> entry : listOfReservations.entrySet())
        {
            List<Reservation> reservationList = entry.getValue();
            for(Reservation reservation : reservationList )
            {
                if(reservation.getCustomer().getEmail().equals(customer.getEmail()))
                {
                    customersReservationDetails.add(reservation);
                }
                else
                {
                    return customersReservationDetails;
                }
            }
        }
        return customersReservationDetails;
    }
    public void printAllReservation()
    {
        for(Map.Entry<IRoom,List<Reservation>> entry : listOfReservations.entrySet())
        {
            List<Reservation> reservationDetails = entry.getValue();
            if(!entry.getValue().isEmpty())
            {
                for(Reservation reseravtion : reservationDetails)
                {
                    System.out.println("Reservation details are:"+ reseravtion);
                }
            }
            else
            {
                System.out.println("No reservations found ");
            }
        }
    }


}
