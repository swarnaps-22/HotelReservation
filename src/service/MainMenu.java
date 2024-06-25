package service;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu
{

    private AdminMenu adminMenu;
    private static HotelResource hotelResource ;
    Scanner scanner = new Scanner(System.in);
    public MainMenu()
    {

    }

    public MainMenu(AdminMenu adminmenu)
    {
        this.adminMenu = adminmenu;
        hotelResource = new HotelResource();

    }

    private static final  List<String> menuItems = Arrays.asList("Find and reserve a room", "See my reservations",
            "Create an account", "Admin", "Exit");

    public void menuList()
    {

        int i = 0;
        for (String option : menuItems)
        {
            System.out.println( i+1 +". " + option);
            i++;
        }

    }
    public void userChoice()
        {
            //1.calls the function based on the user input
            System.out.println("This is MainMenu: ");

                int choice = scanner.nextInt();
                while (choice > 0 && choice < menuItems.size()) {
                    try {


                        switch (choice) {
                            case 1:
                                findAndReserveRoom();
                                break;
                            case 2:

                                seeMyReservation();
                                break;
                            case 3:
                                createACustomer();
                                break;

                            case 4:
                                showAdminMenu();
                                break;
                            case 5:
                                exitMenu();
                                break;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    menuList();
                    System.out.println("Enter your choice: ");
                    choice = scanner.nextInt();
                }

        }

    public void createACustomer()
    {

        System.out.println("Enter your Email:");
        String email = scanner.next();
        System.out.println("Enter your firstName:");
        String firstName = scanner.next();;
        System.out.println("Enter your lastName");
        String lastName = scanner.next();;

        hotelResource.createACustomer(firstName,lastName,email);

    }
    public IRoom getRoomObjForAdmin(String roomNumber)
    {
        IRoom roomObj = hotelResource.getRoom(roomNumber);
        return roomObj;
    }
    public void seeMyReservation()
    {
        System.out.println("Enter email :");
        String customerEmail = scanner.next();
        Collection<Reservation> reservationList = hotelResource.getCustomerReservation(customerEmail);
        if(reservationList == null)
        {
            System.out.println("Customer reservation doesn't exist: ");
        }
        System.out.println(reservationList);

    }
    public void findAndReserveRoom()
    {

        System.out.println("Enter your checkIn (yyyy/MM/dd): ");
        Date checkIn = parseCheckInDate();
        System.out.println("Enter your checkOut (yyyy/MM/dd): ");
        Date checkOut = parseCheckOutDate();
       Collection<IRoom> roomList = hotelResource.findARoom(checkIn, checkOut);

        System.out.println(roomList);
        System.out.println("checkIn  "+checkIn +" "+ "checkOut "+checkOut);

       System.out.println("Enter the room you want to select: ");
       String roomNum = scanner.next();
       boolean foundRoomInList = false;
       if(foundRoomInList == false)
       {
           for(IRoom room : roomList)
           {
               if(roomNum.equals(room.getRoomNumber()))
               {
                   foundRoomInList =true;
                   System.out.println("Enter your firstName: ");
                   String firstName = scanner.next();
                   System.out.println("Enter your lastName: ");
                   String lastName = scanner.next();
                   System.out.println("Enter your email: ");
                   String email = scanner.next();
                   if(hotelResource.getCustomer(email) == null)
                   {
                       System.out.println("customer account doesnt exist please create customer account :");
                       return;
                   }

                   IRoom roomToReserve = hotelResource.getRoom(roomNum);

                   Reservation reservationObj = hotelResource.bookARoom(email,roomToReserve,checkIn,checkOut);
                   System.out.println("Your room has been reserved: "+ reservationObj);
               }

           }
       }


       if(foundRoomInList== false)
       {
           System.out.println("Please pick from available rooms list");
       }



    }

    public Date parseCheckInDate()
    {

        Date checkIn = null;

        SimpleDateFormat checkInSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String checkInDate = scanner.next();
            checkIn = checkInSimpleDateFormat.parse(checkInDate);
            return checkIn;

        }

        catch(ParseException e)
        {
            System.out.println(" You have entered wrong date"+ e);
        }

        return null;

    }
    public Date parseCheckOutDate()
    {

        Date checkOut = null;
        SimpleDateFormat checkInSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try
        {
            String checkOutDate = scanner.next();
            checkOut = checkInSimpleDateFormat.parse(checkOutDate);
            return checkOut;

        }
        catch(ParseException e)
        {
            System.out.println("Invalid checkout date"+ e);
        }
        return null;
    }

    public void showAdminMenu()
    {


        adminMenu.adminMenuDisplay();
        adminMenu.adminChoice();
    }
    public void exitMenu()
    {
        System.out.println("Thank you");

    }
    //hotelResource.bookARoom();

    }


