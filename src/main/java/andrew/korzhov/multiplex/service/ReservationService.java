package andrew.korzhov.multiplex.service;

import andrew.korzhov.multiplex.domain.model.Screening;
import andrew.korzhov.multiplex.web.request.TicketRequest;
import andrew.korzhov.multiplex.web.response.ReservationInfo;

import java.util.List;

public interface ReservationService {

    ReservationInfo reserve(TicketRequest request);

    Screening getScreeningById(long id);

    List<Integer> getAvailableSeats(Long screeningId, int rowNumber);

}
