package res.reservations;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.security.SecureRandom;

public class NewReservation {
    private static String title;
    private static ChoiceBox<Customer> setCustomer = null;
    private static ChoiceBox<Product> setProduct = null;
    private static TextField address = null;
    private static TextField price = null;
    private static TextField note = null;
    private static Button save = new Button();
    private static Button dontsave = new Button("don't save");
    private static ToolBar buttons = new ToolBar(save,dontsave);
    private static Reservation reservation;
    private static String button;
    private static Label message;
    private static Pane[] empty = {new Pane(), new Pane()};

    //Function for edit exist reservation.
    public static void edit(Button eedit){
        button = "update";
        reservation = (Reservation) eedit.getUserData();
        title = "--Edit reservation--";
        openWindow();
    }

    //Function to add a new reservation.
    public static Reservation add(){
        SecureRandom rand = new SecureRandom();
        button = "add";
        boolean free = false;
        // Initialize new res' number, and check if it is already exist.
        while (!free){
            free = true;
            int id = rand.nextInt(1000000,9999999);
            for(Reservation res : TableController.reservations){
                if (res.getResID() == id) {
                    free = !free;
                    break;
                }
            }
            if(free)
                reservation = new Reservation(id);
        }
        title = "--New reservation--";
        openWindow();
        return reservation;
    }

    //Window component settings.
    private static void openWindow(){
        //Hbox titles to Hbox texts.
        HBox titleForText = new HBox(10,
                new Label("name"), new Label("address"), new Label("product"),
                new Label("price"), new Label("note"));
        for (Node n : titleForText.getChildren()){
            Label l = (Label) n;
            l.setMinWidth(100);
        }

        HBox editLine = editBox();
        setComponents();

        //Set window view
        Stage newWindow = new Stage();
        setSave(newWindow);
        newWindow.setTitle(title);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setMinWidth(550);
        newWindow.setMinHeight(300);
        VBox v = new VBox(10,empty[0], titleForText, editLine, empty[1], buttons);
        v.setAlignment(Pos.CENTER);
        //'dont save' button- to only close the window and don't do anything.
        dontsave.setOnAction(event -> {
            //In new reservation, if not created eventually delete this new.
            if(reservation.getProduct() == null ||
                    reservation.getCustomer() == null)
                        reservation = null;
            message.setText("");
            newWindow.close();
        });
        Scene s = new Scene(v);
        newWindow.setScene(s);
        newWindow.showAndWait();
    }

    //Set 'background' of the window.
    private static void setComponents(){
        for (Pane p : empty){
            p.setMinHeight(100);
            p.setBackground(new Background(
                    new BackgroundFill(Color.rgb(204, 255, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        message = new Label();
        message.setStyle("-fx-font: 14 arial;");
        message.setTextFill(Color.rgb(153,0,0));
        empty[1].getChildren().add(message);

    }

    //All components of Hbox - parts of the reservation to edit and enter new details.
    private static HBox editBox(){

        setCustomer = new ChoiceBox<>(FXCollections.observableArrayList(TableController.customers));
        setProduct = new ChoiceBox<>(FXCollections.observableArrayList(TableController.products));
        address = new TextField(reservation.getAddress());
        price = new TextField(reservation.getProductPrice());
        note = new TextField(reservation.getNote());

        //Set 'choice box' for choosing customer and product.
        if(reservation.getCustomer() != null && reservation.getProduct() != null){
            //Initialize with existing details.
            setCustomer.setValue(reservation.getCustomer());
            setProduct.setValue(reservation.getProduct());
        }
        //Update address with chosen customer address.
        setCustomer.setOnAction(event -> {
            address.setText(setCustomer.getValue().getAddress());
        });
        //Update price with chosen product price.
        setProduct.setOnAction(event -> {
            price.setText(Double.toString(setProduct.getValue().getPrice()));
        });
        //

        //Set Physical settings of Hbox and its components.
        HBox editLine = new HBox(10, setCustomer, address, setProduct, price, note);
        editLine.setMinHeight(60);
        editLine.setMaxWidth(500);

        for (Node n : editLine.getChildren()){
            Control c = (Control) n;
            c.setMinHeight(60);
            c.setMinWidth(100);
        }
        return editLine;
    }

    //Set 'save' button ('update' or 'add', in fact) - update reservation details.
    private static void setSave(Stage window){
        save.setText(button);
        save.setOnAction(event -> {
            try {
                reservation.setCustomer(setCustomer.getValue());
                reservation.setAddressToShip(address.getText());
                if (address.getText() == "")
                    throw new Exception();
                reservation.setProduct(setProduct.getValue());
                reservation.setProductPrice(Double.parseDouble(price.getText()));
                reservation.setNote(note.getText());
                message.setText("");
                window.close();
            }
            catch (NumberFormatException e){
                message.setText("* Please enter number for price.");
            }
            catch (Exception c){
                message.setText("* Please enter all details to those field.");
            }

        });
    }
}
