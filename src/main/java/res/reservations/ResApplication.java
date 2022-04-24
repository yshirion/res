package res.reservations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ResApplication extends Application {
    static Stage s;
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ResApplication.class.getResource("resView.fxml"));
        Scene scene = new Scene(root, 800, 450);
        s=stage;
        stage.setTitle("reservations table");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        update();
        launch();
    }

    //Initalize some reservation for beginning.
    private static void update(){
        TableController.customers.add(new Customer("Avraham","Ivri", "Elon Moreh"));
        TableController.customers.add(new Customer("Ytzhak","Din", "Beersheba"));
        TableController.customers.add(new Customer("Jackov","Israel", "Hevron"));
        TableController.customers.add(new Customer("Moshe","Levi", "Nov"));
        TableController.customers.add(new Customer("Aharon","Choen", "Ziklag"));
        TableController.customers.add(new Customer("David","King", "Jerusalem"));
        TableController.customers.add(new Customer("Yosef","Zadik", "Shehem"));
        TableController.products.add(new Product("MacBook pro", 8000));
        TableController.products.add(new Product("MacBook Air", 6000));
        TableController.products.add(new Product("Airpods pro", 999.9));
        TableController.products.add(new Product("Iphone 12 mini", 2500));
        TableController.reservations.add(new Reservation(
                2345678, TableController.customers.get(1), TableController.products.get(1)));
        TableController.reservations.add(new Reservation(
                8765432, TableController.customers.get(4), TableController.products.get(2)));
        TableController.reservations.add(new Reservation(
                1357986, TableController.customers.get(0), TableController.products.get(3),"quick"));
    }
}