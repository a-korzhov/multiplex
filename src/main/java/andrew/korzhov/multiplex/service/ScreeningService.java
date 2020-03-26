package andrew.korzhov.multiplex.service;

import andrew.korzhov.multiplex.web.response.AvailableSeats;
import andrew.korzhov.multiplex.web.response.MovieInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {

    List<MovieInfo> getScreeningsByInterval(LocalDateTime min, LocalDateTime max);

    List<Long> updateScreening(int day);

    AvailableSeats getAvailableSeats(Long screeningId, int rowNumber);
}