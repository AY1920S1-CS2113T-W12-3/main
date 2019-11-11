package command;

import booking.ApprovedList;
import inventory.Inventory;
import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.StorageManager;
import ui.Ui;
import user.UserList;
import java.io.IOException;
import java.text.ParseException;

public class RejectCommand extends Command {

    private int index;

    //@@author AmosChan97
    /**
     * Approve a request.
     * format is reject name roomcode date time
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     * @throws IOException when entry is incorrect
     */
    public RejectCommand(String input, String[] splitStr) throws DukeException, IOException {
        if (splitStr.length <= 1) {
            throw new DukeException("☹ OOPS!!! Please create the booking you want to reject"
                    + " with the following format: reject INDEX");
        }
        String indexString = input.substring("reject".length()).trim();
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a index in integer form!");
        }
        index -= 1;
    }

    @Override
    public void execute(UserList userList, Inventory inventory, RoomList roomList,
                        BookingList bookingList, ApprovedList approvedList, Ui ui,
                        StorageManager allStorage)
            throws DukeException, IOException, ParseException {
        if (!userList.getLoginStatus()) {
            throw new DukeException("Please log in to approve or reject bookings.");
        }
        if (index < 0 || index >= bookingList.size()) {
            throw new DukeException("OOPS!!! The index you have entered is out of bounds");
        }
        bookingList.get(index).rejectStatus(userList.getCurrentUser());
        ui.addToOutput("This request has been rejected");
        ui.addToOutput(bookingList.get(index).toString());
        allStorage.getBookingStorage().saveToFile(bookingList);
    }
}