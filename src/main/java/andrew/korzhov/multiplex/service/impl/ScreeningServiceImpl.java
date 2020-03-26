package andrew.korzhov.multiplex.service.impl;

import andrew.korzhov.multiplex.domain.model.Screening;
import andrew.korzhov.multiplex.domain.projection.AvailableSeatsView;
import andrew.korzhov.multiplex.domain.repository.RoomRepository;
import andrew.korzhov.multiplex.domain.repository.ScreeningRepository;
import andrew.korzhov.multiplex.domain.repository.SeatRepository;
import andrew.korzhov.multiplex.errors.exception.NotFoundException;
import andrew.korzhov.multiplex.service.ScreeningService;
import andrew.korzhov.multiplex.web.response.AvailableSeats;
import andrew.korzhov.multiplex.web.response.MovieInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static andrew.korzhov.multiplex.utils.ExceptionMessages.SCREENING_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<MovieInfo> getScreeningsByInterval(LocalDateTime min, LocalDateTime max) {
        log.info("getScreeningsById min={} max={}", min, max);
        List<Screening> screenings = screeningRepository
                .findByScreeningTimeBetweenOrderByMovieTitleAscScreeningTimeAsc(min, max);

        return screenings.stream()
                .map(MovieInfo::new)
                .collect(Collectors.toList());
    }


    @Override
    public AvailableSeats getAvailableSeats(Long screeningId, int rowNumber) {
        var screening = screeningRepository.findById(screeningId).orElseThrow(
                () -> new NotFoundException(SCREENING_NOT_FOUND, screeningId)
        );
        var roomId = screening.getRoomId();
        var roomName = screening.getRoomName();

        log.info("getAvailableSeats screeningId={}, roomId={}, rowNumber={}",
                screeningId, roomId, rowNumber);

        List<AvailableSeatsView> asr = seatRepository.findAvailableSeatsByRow(screeningId, roomId, rowNumber);

        return new AvailableSeats(rowNumber, roomName, asr);
    }

    @Transactional
    @Override
    public List<Long> updateScreening(int day) {
        List<Screening> screenings = screeningRepository.findAll();
        screenings.forEach(s -> s.setScreeningTime(s.getScreeningTime().withDayOfMonth(day)));

        List<Long> screeningIds = new ArrayList<>();
        for (Screening s : screenings) {
            screeningIds.add(s.getId());
        }

        return screeningIds;
    }
}
