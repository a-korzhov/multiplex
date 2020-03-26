package andrew.korzhov.multiplex.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room extends BaseEntity {

    @Column(name = "name")
    @NotNull
    private String name;

}
