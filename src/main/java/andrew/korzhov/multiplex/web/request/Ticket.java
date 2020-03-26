package andrew.korzhov.multiplex.web.request;

import lombok.Data;

@Data
public class Ticket {
    public enum  TicketType {
        ADULT, STUDENT, CHILD
    }

    private TicketType ticketType;

    private int seat;

}