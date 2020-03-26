package andrew.korzhov.multiplex.web.response;

import andrew.korzhov.multiplex.domain.projection.AvailableSeatsView;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AvailableSeats {
    private int row;
    private String room;
    private List<Integer> seats;

    public AvailableSeats(int row, String room, List<AvailableSeatsView> availableSeatsViews) {
        this.row = row;
        this.room = room;
        seats = availableSeatsViews.stream()
                .map(AvailableSeatsView::getAvailableSeat)
                .collect(Collectors.toList());
    }
}
