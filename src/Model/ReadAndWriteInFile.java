package Model;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteInFile {

    public static void createFileToSaveInfo()
    {
        File file = new File("UsersInformation.ser");

        try {

            file.createNewFile();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void WriteObjectToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream(new File("UsersInformation.ser"));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(Person.people);
            objectOut.close();
            fileOut.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void ReadObjectFromFile() {

        try {

            FileInputStream fileIn = new FileInputStream(new File("UsersInformation.ser"));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList<Person> users2 = (ArrayList<Person>) objectIn.readObject();
            Person.people = users2 ;
            objectIn.close();
            fileIn.close();

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}
