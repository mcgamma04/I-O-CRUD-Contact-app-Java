package contactapp.controller;

import java.util.Scanner;
import contactapp.model.contacts.Contacts;
import contactapp.model.database.Database;

public class Controller {

    public void menu(){
        Contacts obj = new Contacts();
        Database obj1 = new Database();

        obj1.createContactFile();

        Scanner a = new Scanner(System.in);
        int choice;


        do {
            System.out.println("\t===========================");
            System.out.println("\t    >>>>Contact App<<<<    ");
            System.out.println("\t===========================");
            System.out.println("\t(1) >> View All Contacts\n\t(2) >> Create new Contact\n\t(3) >> Search contact\n\t" +
                    "(4) >> Delete  contact\n\t(5) >> Update Contact\n\t(6) >> Exit");
            choice = a.nextInt();
            switch(choice){
                case 1:
                    obj.viewAllContacts();
                    break;
                case 2:
                    obj.createNewContact();
                    break;
                case 3:
                    obj.searchForContact();
                    break;
                case 4:
                    obj.deleteContact();
                    break;
                case 5:
                    obj.updateContact();
                    break;
                case 6:
                    System.out.println(" >> Goodbye << ");
                    break;
                default:
                    System.err.println(" >> Enter a valid action please");
            }

        } while(choice !=6);
    }
}
