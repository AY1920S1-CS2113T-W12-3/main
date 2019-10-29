
package command;

import inventory.Inventory;
import inventory.Item;

import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.Constants;
import storage.Storage;
import ui.Ui;
import user.Login;
import user.User;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class LoginCommand extends Command {
    private String[] splitL;

    //@@ AmirAzhar
    /**
     * User login.
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     */
    public  LoginCommand(String input, String[] splitStr) throws DukeException, IOException {
        File membersFile = new File("data\\members.txt");
        membersFile.createNewFile(); //if file already exists, won't create
        if (splitStr.length == 1) {
            throw new DukeException(Constants.UNHAPPY + " OOPS!!! Please login with NUS email and password");
        }
        this.splitL = input.split(" ");
        if (!splitL[1].contains("@u.nus.edu")) {
            throw new DukeException(Constants.UNHAPPY
                    + " OOPS!!! Please use your NUS email, ending with u.nus.edu, for login!");
        }
    }

    @Override
    public void execute(Inventory inventory, RoomList roomList, BookingList bookingList, Ui ui, Storage inventoryStorage, Storage bookingstorage, Storage roomstorage, User user) throws DukeException, IOException, ParseException {
        boolean isVerified = Login.verifyLogin(splitL[1], splitL[2], "data\\members.txt");
        if (isVerified) {
            Login.setCurrentUser(splitL[1]);
            ui.addToOutput("You have successfully logged in!");
        } else {
            ui.addToOutput(Constants.UNHAPPY + " OOPS!!! You have entered your email/password incorrectly.");
        }
    }
}
