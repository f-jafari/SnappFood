package Model;

import java.util.ArrayList;

public class Place {

    public static ArrayList<Place> places = new ArrayList<>();


    private String name ;
    private String address ;

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Place()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
