package andrew.korzhov.multiplex.domain.repository;

import andrew.korzhov.multiplex.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
