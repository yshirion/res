package res.reservations;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TableController {

    //Empty list, ready to fill it with reservations.
    //All another components in fxml file.
    @FXML
    private VBox resList = new VBox();

    //Lists to restore customers, products and - exist and new reservation and buttons to them.
    static ArrayList<Customer> customers = new ArrayList<Customer>();
    static ArrayList<Product> products = new ArrayList<Product>();
    static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    ArrayList<Button> reservationsButtons = new ArrayList<Button>();
    //Flag to differ between every two elements in list.
    boolean color = true;

    public void initialize(){
        for(Reservation res: reservations){
            add(res);
        }
    }
    //All reservations represent as buttons to be able to edit them.
    private void add(Reservation res){

        //Fill in Hbox all parts of reservations as label.
        HBox h = new HBox(5, new Label("#"+res.getResID()), new Label(res.getProduct().toString()),
                new Label(res.getProductPrice()), new Label(res.getCustomer().toString()),
                new Label(res.getAddress()), new Label(res.getNote()));
        //Set all labels ( 'i' for size of 'price' width ).
        int i = 0;
        for (Node n : h.getChildren()){
            Label l = (Label) n;
            if( i == 2) l.setPrefWidth(90);
            else  l.setPrefWidth(130);
            l.setStyle("-fx-font: 16 arial;");
            i++;
        }
        //Initialize the button and all its settings.
        Button  preservation = new Button("", h);
        //Set function to handle every button to be able to edit it.
        preservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                updateRes(e);
            }
        });

        setButton(preservation);
        //Bind button to its reservation.
        preservation.setUserData(res);
        reservationsButtons.add(preservation);
    }

    //Function for 'Physical' settings.
    private void setButton(Button b){
        b.setMinWidth(resList.getWidth());
        b.setMinHeight(50);
        b.setMinWidth(500);
        if(color) b.setBackground(new Background(
                new BackgroundFill(Color.rgb(204, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        resList.getChildren().add(b);
        color = !color;
    }

    //Function to handle 'add' button.
    @FXML
    void addRes(){
        Reservation newres = NewReservation.add();
        if(newres != null){
            reservations.add(newres);
            add(newres);
        }
    }

    //Function to handle update exist reservation.
    private void updateRes(ActionEvent event){
        NewReservation.edit((Button) event.getSource());
        Button b = (Button) event.getSource();
        HBox box = (HBox) b.getGraphic();
        Reservation r = (Reservation) b.getUserData();
        Label l = (Label) box.getChildren().get(1);
        l.setText(r.getProduct().toString());
        l = (Label) box.getChildren().get(2);
        l.setText(r.getProductPrice());
        l = (Label) box.getChildren().get(3);
        l.setText(r.getCustomer().toString());
        l = (Label) box.getChildren().get(4);
        l.setText(r.getAddress());
        l = (Label) box.getChildren().get(5);
        l.setText(r.getNote());
    }



}