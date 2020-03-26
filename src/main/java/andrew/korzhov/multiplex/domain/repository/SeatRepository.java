package andrew.korzhov.multiplex.domain.repository;

import andrew.korzhov.multiplex.domain.model.Room;
import andrew.korzhov.multiplex.domain.model.Seat;
import andrew.korzhov.multiplex.domain.projection.AvailableSeatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value =
            "SELECT s.id seatId, s.seat_number availableSeat, " +
                    "s.row_number availableRow, rm.name screeningRoom \n" +
                    "FROM seats s\n" +
                    "JOIN rooms rm on rm.id=s.room_id\n" +
                    "   LEFT JOIN (\n" +
                    "       SELECT r.seat_id FROM reserved_seats r\n" +
                    "       JOIN reservations res ON res.id=r.reservation_id " +
                    "       AND res.screening_id = :screeningId) res ON res.seat_id=s.id\n" +
                    "WHERE res.seat_id IS NULL AND s.room_id=:roomId AND s.row_number=:rowNumber\n" +
                    "ORDER BY s.id;", nativeQuery = true)
    List<AvailableSeatsView> findAvailableSeatsByRow(
            @Param("screeningId") Long screeningId,
            @Param("roomId") Long roomId,
            @Param("rowNumber") int rowNumber
    );

    Seat findBySeatNumberAndRowNumberAndRoom(int seat, int row, Room room);
}
