package andrew.korzhov.multiplex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reserved_seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedSeat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "seat_id")
    @NotNull
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @NotNull
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    @NotNull
    private Screening screening;

}
