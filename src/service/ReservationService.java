package service;

import model.*;

import java.util.*;

public class ReservationService {

    public static Map<IRoom ,List<Reservation>> listOfReservations = new HashMap<>();
    static List<IRoom>  allRoomsList;
    private static ReservationService reservationService = null;

    private ReservationService()
    {

    }
   public static ReservationService getReservationServiceInstance()
   {
       if(reservationService == null)
       {
           return reservationService = new ReservationService();
       }
       return reservationService;
   }


    public void addRoom(IRoom room)
    {
        //Adds room_obj as key with value as empty list indicating room is available.

//            listOfReservations.computeIfAbsent(room,k->listOfReservations.put(room,new ArrayList<>()));

        if(listOfReservations.containsKey(room) == false)
        {
            listOfReservations.put(room,new ArrayList<>());
            System.out.println(" Rooms added  : " +room.getRoomNumber() );
        }
        else
        {
            System.out.println("Duplicate rooms cannot be added: "+ room.getRoomNumber());
        }

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
        return null;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate , Date checkOutDate)
    {
        //find the room in the key and add the list of reservations as values in the hashmap
        //check in the list of reservation objects if there are any reservations on same date then add
        Reservation reservationOfCustomer = new Reservation(customer,room,checkInDate,checkOutDate);
        List<Reservation> listOfReservationObj = listOfReservations.get(room);

                        listOfReservationObj.add(reservationOfCustomer);
                        listOfReservations.put(room,listOfReservationObj);
        return reservationOfCustomer;
    }


    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate)
    {
        //Finds the available rooms list based on checIn and checkOut and add to list and return list of roomsAvailable on the dates.
//
        List<IRoom> roomsAvailable = new ArrayList<>();
        if(checkInDate.after(checkOutDate))
        {
            System.out.println("Check In date invalid: ");
            return roomsAvailable;
        }

        else
        {
          roomsAvailable =  roomAvailabilityCheck(checkInDate,checkOutDate);
        }

        if(roomsAvailable.isEmpty())
        {
            System.out.println("No rooms on "+ checkInDate+" "+ checkOutDate+" will find alternate dates: ");
            Calendar calenderDate = Calendar.getInstance();
            calenderDate.setTime(checkInDate);
            calenderDate.add(Calendar.DAY_OF_MONTH,7);
            checkInDate.setTime(calenderDate.getTimeInMillis());

            calenderDate.setTime(checkOutDate);
            calenderDate.add(Calendar.DAY_OF_MONTH,7);
            checkOutDate.setTime(calenderDate.getTimeInMillis());
            roomsAvailable = roomAvailabilityCheck(checkInDate,checkOutDate);

            if(roomsAvailable.size() == 0)
            {
                System.out.println("No rooms on alternate dates available: "+ checkInDate + " "+ checkOutDate);
            }


        }

     return roomsAvailable;

    }


    public List<IRoom> roomAvailabilityCheck(Date checkInDate,Date checkOutDate)
    {
        List<IRoom> roomsAvailable = new ArrayList<>();

        for(Map.Entry<IRoom, List<Reservation>> entry : listOfReservations.entrySet() )
        {

            List<Reservation> reservations = entry.getValue();
            if (!reservations.isEmpty())
            {
                boolean checkAvailability = true;
                for (Reservation reservation : reservations)
                {
                    boolean reservationAvailable = checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate());
                    checkAvailability = checkAvailability && reservationAvailable;
                }
                if (checkAvailability)
                {
                    System.out.println("Room available on: "+ checkInDate+ " "+ checkOutDate);
                    IRoom room = entry.getKey();
                    roomsAvailable.add(room);
                }

            }
            else
            {
                roomsAvailable.add(entry.getKey());
            }
        }
        return roomsAvailable;
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
                for(Reservation reservation : reservationDetails)
                {
                    System.out.println("Reservation details are:"+ reservation);
                }
            }
            else
            {
                System.out.println("No reservations found ");
            }
        }
    }


}
