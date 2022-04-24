package res.reservations;

public class Customer {
    private String firstName;
    private String lastName;
    private String address;

    public Customer(String first, String last, String add){
        firstName = first;
        lastName = last;
        address = add;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
