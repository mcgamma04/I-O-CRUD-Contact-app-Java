package contactapp.model.database;

import java.io.File;
import java.io.IOException;

public class Database implements IDatabase{

    @Override
    public void createContactFile(){
        File file = new File("contactList.txt");

        if(file.exists()){
            try{
                file.createNewFile();

            }
            catch(IOException ee){}
        } else{
            System.err.println("File already exists");
        }
    }
}
