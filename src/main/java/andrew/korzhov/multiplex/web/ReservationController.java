package andrew.korzhov.multiplex.web;

import andrew.korzhov.multiplex.service.ReservationService;
import andrew.korzhov.multiplex.web.request.TicketRequest;
import andrew.korzhov.multiplex.web.response.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    //The system gives back the total amount to pay and reservation expiration time.
    @PostMapping
    public ReservationInfo reserveSeats(@RequestBody @Valid TicketRequest ticketRequest) {
        return reservationService.reserve(ticketRequest);
    }
}

