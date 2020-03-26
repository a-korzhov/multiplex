package andrew.korzhov.multiplex.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat extends BaseEntity {

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "row_number")
    private int rowNumber;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull
    private Room room;


    public Long getRoomId(){
        return this.room.getId();
    }
}
