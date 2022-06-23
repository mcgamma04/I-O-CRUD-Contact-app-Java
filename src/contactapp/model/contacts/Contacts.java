package contactapp.model.contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Contacts implements IContactAppMethods{
    private String name, email;
    private Long phoneNumber;

    private String searchName;
    private Long phoneNumberDelete,newPhoneNumber;
    private String confirm, newName, newEmail;

    public void setName(){
        Scanner a = new Scanner(System.in);
        System.out.println("Enter name: ");
        this.name = a.nextLine();

    }
    public String getName(){return name;}

    public void setEmail(){
        Scanner a = new Scanner(System.in);
        System.out.println("Enter email: ");
        this.email = a.nextLine();
    }
    public String getEmail(){return email;}

    public void setPhoneNumber(){
        Scanner a = new Scanner(System.in);
        System.out.println("Enter phone number");
        this.phoneNumber = a.nextLong();
    }
    public Long getPhoneNumber(){return phoneNumber;}

    @Override
    public void createNewContact(){
        setName();
        setEmail();
        setPhoneNumber();

        File file = new File("contactList.txt");

        try{
            FileWriter fwrite = new FileWriter(file,true);
            String temp = getName()+ "!" + getEmail()+ "!" + getPhoneNumber().toString()+"\n";
            System.out.println(" ");

            fwrite.write(temp);

            System.out.println(" >> Contact has been added successfully <<" );
            System.out.println();
            fwrite.close();
        }
        catch(IOException ee){
            ee.printStackTrace();
        }
    }

    public void setSearchName(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Enter name: ");
        this.searchName = a.nextLine();
    }

    public String getSearchName(){return searchName;}

    @Override
    public void searchForContact(){
        String[] holder;
        ArrayList<String> nameHolder = new ArrayList<>();
        ArrayList<String> emailHolder = new ArrayList<>();
        ArrayList<String> phoneNumberHolder = new ArrayList<>();

        setSearchName();

        File file = new File("contactList.txt");
        try{
            String lines;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((lines = reader.readLine())!=null){
                holder = lines.split("!");
                nameHolder.add(holder[0]);
                emailHolder.add(holder[1]);
                phoneNumberHolder.add(holder[2]);
            }

            for(int i =0; i<nameHolder.size(); i++){
                String hyperSearch = nameHolder.get(i).substring(0,3);

                if(getSearchName().toLowerCase().contains(nameHolder.get(i).toLowerCase()) || getSearchName().toLowerCase().contains(hyperSearch.toLowerCase()) ){
                    System.out.println();
                    System.out.println(">> Name: " + nameHolder.get(i));
                    System.out.println(">> Email: " + emailHolder.get(i));
                    System.out.println(">> Phone Number: " +"0"+phoneNumberHolder.get(i));
                    System.out.println();
                    break;
                } else {}
            }
        }
        catch (IOException ee){}


    }

    public void setPhoneNumberDelete(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Enter phone number of contact you wish to delete:");
        this.phoneNumberDelete = a.nextLong();
    }

    public Long getPhoneNumberDelete(){
        return phoneNumberDelete;
    }

    public void setConfirm(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Are you sure you wish to delete this contact?");
        System.out.println(">>> Enter 'Y' to continue OR 'N' to cancel");
        this.confirm = a.nextLine();
    }

    public String getConfirm(){return confirm;}

    @Override
    public void deleteContact(){
        String[] holder;
        ArrayList<String> nameHolder = new ArrayList<>();
        ArrayList<String> emailHolder = new ArrayList<>();
        ArrayList<String> phoneNumberHolder = new ArrayList<>();

        setPhoneNumberDelete();

        File file = new File("contactList.txt");
        try{

            String lines;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((lines = reader.readLine())!=null){
                holder = lines.split("!");
                nameHolder.add(holder[0]);
                emailHolder.add(holder[1]);
                phoneNumberHolder.add(holder[2]);
            }

            for(int i =0; i<phoneNumberHolder.size(); i++){

                if(getPhoneNumberDelete().toString().equals(phoneNumberHolder.get(i))){
                    System.out.println("====================================");
                    System.out.println();
                    System.out.println(">>>> Name: " + nameHolder.get(i));
                    System.out.println(">>>> Email: " + emailHolder.get(i));
                    System.out.println(">>>> Phone Number: " + phoneNumberHolder.get(i));
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println(" >> THIS CONTACT WILL BE DELETED << ");
                    System.out.println();
                    setConfirm();

                    if(getConfirm().equalsIgnoreCase("y")){
                        nameHolder.remove(nameHolder.get(i));
                        emailHolder.remove(emailHolder.get(i));
                        phoneNumberHolder.remove(phoneNumberHolder.get(i));

                        FileWriter fwrite = new FileWriter(file,true);
                        FileWriter eraser = new FileWriter(file);
                        eraser.write("");

                        for(int j =0; j< nameHolder.size();j++){
                            String temp = nameHolder.get(j)+ "!" + emailHolder.get(j)+ "!" + phoneNumberHolder.get(j)+"\n";
                            fwrite.write(temp);
                        }
                        fwrite.close();
                        System.out.println();
                        System.out.println(" >> CONTACT HAS BEEN DELETED << ");
                        System.out.println();
                        break;
                    } else if (getConfirm().equalsIgnoreCase("n")){
                        System.out.println(" >> OPERATION ABORTED");
                        break;
                    } else{
                        System.out.println(" Invalid action ");
                    }

                } else {}
            }
        }
        catch (IOException ee){}
    }

    @Override
    public void viewAllContacts(){
        String[] holder;

        File file = new File("contactList.txt");
        try{
            String lines;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            System.out.println();

            while((lines = reader.readLine())!=null){
                holder = lines.split("!");

                System.out.println(" >> Name: " + holder[0]);
                System.out.println(" >> Email: " + holder[1]);
                System.out.println(" >> Phone number: " +("0"+holder[2]) );
                System.out.println();
            }

            reader.close();
        }
        catch (IOException ee){}
    }

    public void setNewName(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Enter new Name:");
        this.newName = a.nextLine();
    }

    public String getNewName(){return newName;}

    public void setNewEmail(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Enter new Email:");
        this.newEmail = a.nextLine();
    }

    public String getNewEmail(){return newEmail;}

    public void setNewPhoneNumber(){
        Scanner a = new Scanner(System.in);
        System.out.println(" >> Enter new Phone Number:");
        this.newPhoneNumber = a.nextLong();
    }

    public Long getNewPhoneNumber(){return newPhoneNumber;}

    @Override
    public void updateContact() {
        String[] holder;
        ArrayList<String> nameHolder = new ArrayList<>();
        ArrayList<String> emailHolder = new ArrayList<>();
        ArrayList<String> phoneNumberHolder = new ArrayList<>();

        setSearchName();

        File file = new File("contactList.txt");
        try{
            String lines;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while((lines = reader.readLine())!=null){
                holder = lines.split("!");
                nameHolder.add(holder[0]);
                emailHolder.add(holder[1]);
                phoneNumberHolder.add(holder[2]);
            }

            for(int i =0; i<nameHolder.size(); i++){
                String hyperSearch = nameHolder.get(i).substring(0,3);

                if(getSearchName().toLowerCase().contains(nameHolder.get(i).toLowerCase()) || getSearchName().toLowerCase().contains(hyperSearch.toLowerCase()) ){
                    System.out.println();
                    System.out.println(" >> Name: " + nameHolder.get(i));
                    System.out.println(" >> Email: " + emailHolder.get(i));
                    System.out.println(" >> Phone Number: " +"0"+phoneNumberHolder.get(i));
                    System.out.println();
                    int option;

                    do{
                        System.out.println("What do you wish to update?");
                        System.out.println(" >> (1) Name\n >> (2) Email\n >> (3) Phone Number\n >> (4) Finish");
                        Scanner c  = new Scanner(System.in);
                         option = c.nextInt();


                        switch(option){
                            case 1:
                                setNewName();
                                nameHolder.add(i,getNewName());
                                nameHolder.remove(i+1);
                                break;
                            case 2:
                                setNewEmail();
                                emailHolder.add(i,getNewEmail());
                                emailHolder.remove(i+1);
                                break;
                            case 3:
                                setNewPhoneNumber();
                                phoneNumberHolder.add(i,getNewPhoneNumber().toString());
                                break;
                            case 4:
                                System.out.println(" >> Update Completed << ");
                                break;
                            default:
                                System.out.println("Please Enter a valid option");
                        }
                    }while(option !=4);
                    FileWriter fwrite = new FileWriter(file,true);
                    FileWriter eraser = new FileWriter(file);
                    eraser.write("");

                    for(int j =0; j< nameHolder.size();j++){
                        String temp = nameHolder.get(j)+ "!" + emailHolder.get(j)+ "!" + phoneNumberHolder.get(j)+"\n";
                        fwrite.write(temp);
                    }
                    fwrite.close();
                    break;
                } else {}
            }
        }
        catch (IOException ee){}
    }
}
