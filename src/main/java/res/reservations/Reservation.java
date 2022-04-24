package res.reservations;

import res.reservations.Customer;
import res.reservations.Product;

import java.util.Random;
import java.util.UUID;

public class Reservation {
    private final int resID;
    private Customer customer;
    private Product product;
    private String note;
    private String addressToShip;
    private double finalPrice;


    public Reservation(int id){
        resID = id;
    }

    public Reservation(int id, Customer customer, Product product, String note) {
        resID = id;
        this.customer = customer;
        this.product = product;
        this.note = note;
        addressToShip = customer.getAddress();
        finalPrice = product.getPrice();
    }
    public Reservation(int id, Customer customer, Product product) {
        resID = id;
        this.customer = customer;
        this.product = product;
        this.note = "";
        addressToShip = customer.getAddress();
        finalPrice = product.getPrice();
    }

    public int getResID() {

        return resID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAddress() {
        return addressToShip;
    }

    public void setAddressToShip (String address) {
        addressToShip = address;
    }

    public Product getProduct() {
        return product;
    }

    public String getProductPrice() {
        return "" + finalPrice;
    }

    public void setProductPrice(double newPrice) {
        finalPrice = newPrice;
    }

    public String getNote() {
        return note;
    }

    public void setCustomer(Customer cus){
        this.customer = cus;
        addressToShip = cus.getAddress();
    }

    public void setProduct(Product prod){
        this.product = prod;
        finalPrice = prod.getPrice();
    }

    public void setNote(String note) {
        this.note = note;
    }
}
