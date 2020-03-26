package andrew.korzhov.multiplex.web.response;

import andrew.korzhov.multiplex.domain.model.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class ReservationInfo {

    private String totalAmount;
    private LocalDateTime expireTime;

    public ReservationInfo(Reservation reservation) {
        this.totalAmount = reservation.getTotalAmount().toString() + " PLN";
        this.expireTime = reservation.getExpireTime();
    }

}
