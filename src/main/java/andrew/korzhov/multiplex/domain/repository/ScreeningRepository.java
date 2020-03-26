package andrew.korzhov.multiplex.domain.repository;

import andrew.korzhov.multiplex.domain.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    //Screenings given in point 2. of the scenario should be sorted by title and screening time.
    List<Screening> findByScreeningTimeBetweenOrderByMovieTitleAscScreeningTimeAsc(LocalDateTime min, LocalDateTime max);

}
