package andrew.korzhov.multiplex.service.impl;

import andrew.korzhov.multiplex.domain.model.Reservation;
import andrew.korzhov.multiplex.domain.model.ReservedSeat;
import andrew.korzhov.multiplex.domain.model.Screening;
import andrew.korzhov.multiplex.domain.projection.AvailableSeatsView;
import andrew.korzhov.multiplex.domain.repository.ReservationRepository;
import andrew.korzhov.multiplex.domain.repository.ReservedSeatRepository;
import andrew.korzhov.multiplex.domain.repository.ScreeningRepository;
import andrew.korzhov.multiplex.domain.repository.SeatRepository;
import andrew.korzhov.multiplex.errors.exception.NotFoundException;
import andrew.korzhov.multiplex.errors.exception.ReservationException;
import andrew.korzhov.multiplex.service.ReservationService;
import andrew.korzhov.multiplex.web.mapper.ReservationMapper;
import andrew.korzhov.multiplex.web.request.TicketRequest;
import andrew.korzhov.multiplex.web.response.ReservationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static andrew.korzhov.multiplex.utils.ExceptionMessages.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservedSeatRepository reservedSeatRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    private final ReservationMapper reservationMapper;

    @Transactional
    @Override
    public ReservationInfo reserve(TicketRequest request) {
        log.info("Reservation of {} tickets is started", request.getTickets().size());

        var screening = getScreeningById(request.getScreeningId());

        validateScreening(request);

        Reservation reservation = reservationMapper.toReservation(
                request, screening
        );

        validateSeats(request);

        reservationRepository.save(reservation);

        reservedSeatRepository.saveAll(request.getTickets().stream()
                .map(t -> new ReservedSeat(
                        seatRepository.findBySeatNumberAndRowNumberAndRoom(
                                t.getSeat(), request.getRow(), screening.getRoom()),
                        reservation, screening)
                ).collect(Collectors.toList()));

        log.info("Reservation {} finished successfully", reservation.getId());
        return new ReservationInfo(reservation);
    }

    @Override
    public Screening getScreeningById(long id) {
        return screeningRepository.findById(id).orElseThrow(
                () -> new NotFoundException(SCREENING_NOT_FOUND, id)
        );
    }

    // Available seats in a row
    @Override
    public List<Integer> getAvailableSeats(Long screeningId, int rowNumber) {
        Screening screening = getScreeningById(screeningId);

        var roomId = screening.getRoomId();

        log.info("getAvailableSeats screeningId={}, roomId={}, rowNumber={}",
                screeningId, roomId, rowNumber);

        return seatRepository.findAvailableSeatsByRow(screeningId, roomId, rowNumber).stream()
                .map(AvailableSeatsView::getAvailableSeat)
                .collect(Collectors.toList());
    }

    private void validateSeats(TicketRequest request) {

        List<Integer> requested = request.getRequestedSeats();

        List<Integer> available =
                getAvailableSeats(request.getScreeningId(), request.getRow()).stream()
                        .filter(x -> !requested.contains(x))
                        .collect(Collectors.toList());

        int[] allSeats = new int[Math.max(
                available.get(available.size() - 1),
                requested.get(requested.size() - 1)
        )];

        // There cannot be a single place left over in a row between two already reserved places.
        for (Integer seat : available) {
            var leftSeat = seat - 1;
            var rightSeat = seat + 1;
            if ((!available.contains(leftSeat) && leftSeat > 0) &&
                    (!available.contains(rightSeat) && rightSeat <= allSeats.length)) {
                throw new ReservationException(INCORRECT_SEATS_RESERVATION, requested);
            }
        }
    }

    private void validateScreening(TicketRequest request) {

        var screening = getScreeningById(request.getScreeningId());

        // Seats can be booked at latest 15 minutes before the screening begins.
        var screeningTime = screening.getScreeningTime();
        long minutes = LocalDateTime.now().until(screeningTime, ChronoUnit.MINUTES);
        if (minutes <= 15L) {
            throw new ReservationException(
                    BOOKING_TIME_FINISHED, screening.getMovieTitle(), screeningTime
            );
        }
    }

}
