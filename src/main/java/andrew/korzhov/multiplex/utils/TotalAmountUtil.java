package andrew.korzhov.multiplex.utils;

import andrew.korzhov.multiplex.errors.exception.NotFoundException;
import andrew.korzhov.multiplex.web.request.Ticket;

import java.math.BigDecimal;
import java.util.Set;

public class TotalAmountUtil {

    public static BigDecimal getTotalAmount(Set<Ticket> tickets) {
        return tickets.stream()
                .map(t -> getPrice(t.getTicketType()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    // There are three ticket types: adult (25 PLN), student (18 PLN), child (12.50 PLN).
    private static BigDecimal getPrice(Ticket.TicketType type) {
        switch (type) {
            case ADULT:
                return new BigDecimal("25");
            case STUDENT:
                return new BigDecimal("18");
            case CHILD:
                return new BigDecimal("12.50");
            default:
                throw new NotFoundException(String.format(ExceptionMessages.TICKET_TYPE_NOT_FOUND, type));
        }
    }

}
