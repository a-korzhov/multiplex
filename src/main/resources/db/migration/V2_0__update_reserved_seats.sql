ALTER TABLE reserved_seats ADD COLUMN
    screening_id BIGINT REFERENCES screenings(id);

UPDATE reserved_seats
SET screening_id = res.screening_id
FROM reservations res
WHERE reserved_seats.reservation_id = res.id;

TRUNCATE reservations, reserved_seats;

ALTER TABLE reserved_seats ADD CONSTRAINT idx_seat_id_screening_id UNIQUE(seat_id, screening_id);

ALTER TABLE reserved_seats ALTER COLUMN screening_id SET NOT NULL;