import service.AdminMenu;
import service.MainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        AdminMenu adminMenu = new AdminMenu();
        MainMenu mainMenu = new MainMenu(adminMenu);

         mainMenu.menuList();
        mainMenu.userChoice();
       // AdminMenu adminMenu = new AdminMenu();
      //  adminMenu.adminChoice();


        }
    }
