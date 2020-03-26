package andrew.korzhov.multiplex.web.response;

import andrew.korzhov.multiplex.domain.model.Screening;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class MovieInfo {

    private String movieTitle;
    private LocalDateTime screeningTime;

    public MovieInfo(Screening screening){
        this.movieTitle = screening.getMovieTitle();
        this.screeningTime = screening.getScreeningTime();
    }

}
