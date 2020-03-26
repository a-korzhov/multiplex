package andrew.korzhov.multiplex.utils;

public class ExceptionMessages {
    //NOT FOUND
    public static final String SCREENING_NOT_FOUND = "Screening with %s ID not exists, please choose another one.";
    public static final String TICKET_TYPE_NOT_FOUND = "Ticket type %s not found";
    public static final String ROOM_NOT_FOUND = "Room %s not found";

    //BAD REQUEST
    public static final String BOOKING_TIME_FINISHED = "Booking time on %s at %s is over";

    //INVALID
    public static final String INCORRECT_SEATS_RESERVATION = "You cannot make a reservation of: %s seats, it will leave 1 empty seat between already reserved";

}
