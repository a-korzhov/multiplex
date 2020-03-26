package andrew.korzhov.multiplex.web;

import andrew.korzhov.multiplex.service.ScreeningService;
import andrew.korzhov.multiplex.web.response.AvailableSeats;
import andrew.korzhov.multiplex.web.response.MovieInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    //The system lists movies available in the given time interval - title and screening times.
    @GetMapping
    public List<MovieInfo> getScreeningsByTimeInterval(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime min,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime max
    ) {
        return screeningService.getScreeningsByInterval(min, max);
    }

    // The system gives information regarding screening room and available seats.
    @GetMapping("/{screeningId}")
    public AvailableSeats getParticularScreening(
            @PathVariable Long screeningId,
            @RequestParam int row
    ) {
        return screeningService.getAvailableSeats(screeningId, row);
    }

    @PutMapping("/update-day/{screeningDay}")
    public List<Long> update(@PathVariable int screeningDay) {
        return screeningService.updateScreening(screeningDay);
    }

}