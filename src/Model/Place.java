package Model;

public class Place {

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

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}