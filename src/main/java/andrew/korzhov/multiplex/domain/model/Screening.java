package andrew.korzhov.multiplex.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
@Getter
@Setter
public class Screening extends BaseEntity {

    @Column(name = "movie_title")
    @NotNull
    private String movieTitle;

    @Column(name = "screening_time")
    @NotNull
    private LocalDateTime screeningTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull
    private Room room;


    public Long getRoomId() {
        return this.room.getId();
    }

    public String getRoomName(){
        return this.room.getName();
    }

}
