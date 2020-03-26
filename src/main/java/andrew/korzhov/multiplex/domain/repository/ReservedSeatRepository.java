package andrew.korzhov.multiplex.domain.repository;

import andrew.korzhov.multiplex.domain.model.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

}
