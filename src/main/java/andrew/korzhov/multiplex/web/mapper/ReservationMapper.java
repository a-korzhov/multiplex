package andrew.korzhov.multiplex.web.mapper;

import andrew.korzhov.multiplex.domain.model.Reservation;
import andrew.korzhov.multiplex.domain.model.Screening;
import andrew.korzhov.multiplex.web.request.TicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mappings({
            @Mapping(target = "firstName", source = "request.firstName"),
            @Mapping(target = "lastName", source = "request.lastName"),
            @Mapping(
                    target = "totalAmount",
                    expression = "java( " +
                            "andrew.korzhov.multiplex.utils.TotalAmountUtil.getTotalAmount(" +
                            "request.getTickets()" +
                            ") )"),
            @Mapping(target = "expireTime", expression = "java( " +
                    "screening.getScreeningTime().minusMinutes(15L)" +
                    " )"),
            @Mapping(target = "screening", source = "screening")
    })
    Reservation toReservation(TicketRequest request, Screening screening);
}
