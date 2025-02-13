import control.Duke;
import exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import storage.BookingConstants;
import storage.Constants;
import ui.Ui;

import java.io.IOException;

/**
 * A GUI for control.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke(BookingConstants.FILENAME, Constants.ROOMFILENAME,
            Constants.INVENTORYFILENAME, Constants.USERFILENAME);

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Hall Booker");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            System.out.println("Application cannot be run");
            e.printStackTrace();
        }
    }
}
