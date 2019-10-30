
package command;

import inventory.Inventory;
import inventory.Item;

import booking.Booking;
import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.Storage;
import ui.Ui;
import user.User;

import java.io.IOException;
import java.text.ParseException;

public class FindBookingIndexCommand extends Command {
    private int index;

    //@@author  Alex-Teo
    /**
     * Find booking via index.
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException input error
     */
    public FindBookingIndexCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length <= 1) {
            throw new DukeException("☹ OOPS!!! Please create your booking with the following format: "
                    + "index");
        }
        index = Integer.valueOf(input.substring(10)) - 1;

    }

    @Override
    public void execute(Inventory inventory, RoomList roomList, BookingList bookingList, Ui ui,
                        Storage inventoryStorage, Storage bookingstorage, Storage roomstorage, User user)
            throws DukeException, IOException, ParseException {
        if (index >= bookingList.size()) {
            throw new DukeException("OOPS!!!  No such entry exist!");
        } else {
            Booking result = bookingList.get(index);
            ui.addToOutput((index + 1) + ". " + result.toString());
        }
    }
}
