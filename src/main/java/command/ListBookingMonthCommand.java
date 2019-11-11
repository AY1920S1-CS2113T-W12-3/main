package command;

import booking.ApprovedList;
import inventory.Inventory;
import booking.Booking;
import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.BookingConstants;
import storage.Storage;
import storage.StorageManager;
import ui.Ui;
import user.UserList;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class ListBookingMonthCommand extends Command {

    private LocalDate dateStart;
    private int monthStart;

    /**
     * Show all bookings in a certain month.
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException when entry is ivalid due to format
     */
    public ListBookingMonthCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length <= 1) {
            throw new DukeException(":-( OOPS!!! Please create your booking with the following format: "
                    + "month value");
        }
        try {
            this.monthStart = Integer.parseInt(input.substring(10));
        } catch (NumberFormatException e) {
            throw new DukeException(BookingConstants.MONTHERROR1);
        }
        if (this.monthStart > 12) {
            throw new DukeException(BookingConstants.MONTHERROR2);
        }
    }


    @Override
    public void execute(UserList userList, Inventory inventory, RoomList roomList,
                        BookingList bookingList, ApprovedList approvedList, Ui ui,
                        StorageManager allStorage)
            throws DukeException, IOException, ParseException {
        boolean bookingExists = false;

        ui.addToOutput("Here are the bookings: ");
        for (Booking i : bookingList) {
            if (i.getStartMonth() == this.monthStart) {
                ui.addToOutput((bookingList.indexOf(i) + 1) + ". " + i.toString() + "\n");
                bookingExists = true;
            }
        }
        if (!bookingExists) {
            throw new DukeException("OOPS!! There are no bookings for this month");
        }
    }
}

