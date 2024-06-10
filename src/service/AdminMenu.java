package service;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {

        Scanner scanner = new Scanner(System.in);
        private static AdminResource adminResource;

        private MainMenu mainMenu;

        public AdminMenu()
        {
            adminResource = new AdminResource();
        }

        public MainMenu getMainMenu()
        {
            if(mainMenu == null)
           {
               mainMenu = new MainMenu();
            }
            return mainMenu;
        }
        public static List<String> adminMenuList = Arrays.asList("1.See all Customers", "2.See all Rooms","3.See all Reservations","4.Add a Room","5.Back to Main");

        public void adminMenuDisplay()
        {
            for(String adminListItem : adminMenuList)
            {
                System.out.println(adminListItem);
            }
        }

        public void adminChoice()
        {
            //1.calls the function based on the user input
            System.out.println("This is AdminMenu: ");
            int choice = scanner.nextInt();
            while (choice > 0 && choice < adminMenuList.size())
            {
                switch (choice)
                {
                    case 1:
                        seeAllCustomers();
                        break;
                    case 2:
                        seeAllRooms();
                        break;
                    case 3:
                        seeAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        backToMain();
                        break;


                }
                adminMenuDisplay();
                System.out.println("Enter your choice : ");
                choice = scanner.nextInt();

            }
        }

        public void seeAllCustomers()
        {
            Collection<Customer> customerList = adminResource.getAllCustomers();
            if(customerList.isEmpty())
            {
                System.out.println(customerList);
                System.out.println("No customers available :");
            }
            else
            {
                System.out.println(customerList);
            }
        }
        public void seeAllRooms()
        {
            Collection<IRoom> roomsList = adminResource.getAllRooms();
            if(roomsList.isEmpty())
            {
                System.out.print("Rooms list is empty: ");
            }
            else
            {
                System.out.println(roomsList);
            }
        }
        public void seeAllReservations()
        {
            adminResource.displayAllReservations();
        }

        public void addARoom()
        {
            // create roomobj
            Set<IRoom> roomObjList = new HashSet<>();
            System.out.println("How many rooms do you want to add");
            int numberOfRooms = scanner.nextInt();
            int i =1;
            while(i<=numberOfRooms)
            {
                System.out.println("Enter Room Number: ");
                String roomNumber = scanner.next();
                System.out.println("Enter Price:");
                Double price = scanner.nextDouble();
                System.out.println("Enter RoomType singleroom/doubleroom(roomtype is enum ) :");
                String room  = scanner.next().toUpperCase();
                RoomType roomType = RoomType.valueOf(room);
               IRoom roomObj = new Room(roomNumber,price,roomType);

                if(false == roomObjList.add(roomObj))
                {
                    System.out.println("duplicate room skipped: ");
                }
                i++;
            }


            adminResource.addRoom(roomObjList.stream().toList());

        }

        public void backToMain()
        {
            MainMenu mainMenuObj = getMainMenu();
            mainMenuObj.menuList();
            mainMenuObj.userChoice();
        }
}
