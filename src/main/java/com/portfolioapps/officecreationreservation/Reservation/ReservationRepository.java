package com.portfolioapps.officecreationreservation.Reservation;

import com.portfolioapps.officecreationreservation.Room.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    @Query(value = "select * from reservations r " +
            "where r.reservation_date=?1 and r.room_id=?2 " +
            "order by r.reservation_time_from asc", nativeQuery = true)
    List<Reservation> findAllReservationsByDateAndRoomID(String date, Integer roomID);
}
