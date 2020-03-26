package andrew.korzhov.multiplex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "total_amount")
    @NotNull
    private BigDecimal totalAmount;

    @Column(name = "expire_time")
    @NotNull
    private LocalDateTime expireTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    @NotNull
    private Screening screening;

    public Long getScreeningId() {
        return this.screening.getId();
    }
}
