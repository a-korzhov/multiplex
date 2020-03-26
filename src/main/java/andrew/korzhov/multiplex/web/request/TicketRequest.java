package andrew.korzhov.multiplex.web.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TicketRequest {

    /*
       name and surname should each be at least three characters long, starting with a capital letter.
     */
    @Size(min = 3, message = "{app.constraints.firstname.Size.message}")
    @Pattern(regexp = "^[A-ZZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+", message = "{app.constraints.firstname.Pattern.message}")
    private String firstName;

    /*
       surname could consist of two parts separated with a single dash,
       in this case the second part should also start with a capital letter
     */
    @Size(min = 3)
    @Pattern(
            regexp = "^[A-ZZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+(-[A-ZZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+)?",
            message = "{app.constraints.lastname.Pattern.message}")
    private String lastName;

    private int row;

    @NotEmpty(message = "{app.constraints.tickets.NotEmpty.message}")
    Set<Ticket> tickets;

    private Long screeningId;

    public List<Integer> getRequestedSeats() {
        return this.getTickets().stream()
                .map(Ticket::getSeat)
                .sorted()
                .collect(Collectors.toList());
    }

}
