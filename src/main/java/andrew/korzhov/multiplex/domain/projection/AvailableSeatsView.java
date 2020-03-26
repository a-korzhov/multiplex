package andrew.korzhov.multiplex.domain.projection;

public interface AvailableSeatsView {

    Long getSeatId();

    Integer getAvailableSeat();

    Integer getAvailableRow();

    String getScreeningRoom();

}
