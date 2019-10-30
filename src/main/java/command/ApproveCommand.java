
package command;

import inventory.Inventory;
import inventory.Item;

import booking.Booking;
import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.Constants;
import storage.Storage;
import ui.Ui;
import user.UserList;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApproveCommand extends Command {
    private int index;

    //@@author Alex-Teo
    /**
     * Approve a request.
     * format is approve name roomcode date time
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     * @throws IOException when entry is incorrect
     */
    public ApproveCommand(String input, String[] splitStr) throws DukeException, IOException {
        if (splitStr.length <= 1) {
            throw new DukeException("☹ OOPS!!! Please create the booking you want to approve with the following format: "
                    + "name, roomcode, start date and time");
        }
        try {
            index = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a index in integer form!");
        }
        index -= 1;
    }

    @Override
    public void execute(UserList userList, Inventory inventory, RoomList roomList, BookingList bookingList, Ui ui,
                        Storage userStorage, Storage inventoryStorage, Storage bookingstorage, Storage roomstorage)
            throws DukeException, IOException, ParseException {
        if (!roomList.checkRoom(roomcode)) {
            throw new DukeException(Constants.UNHAPPY + "OOPS!!! This room doesn't exist!");
                        Storage inventoryStorage, Storage bookingstorage, Storage roomstorage, User user)
            throws DukeException, IOException {
        if (index < 0 || index >= bookingList.size()) {
            throw new DukeException("OOPS!!! The index you have entered is out of bounds");
        }
        bookingList.get(index).approveStatus();
        ui.addToOutput("This request has been approved");
        ui.addToOutput(bookingList.get(index).toString());
        bookingstorage.saveToFile(bookingList);
    }
}
