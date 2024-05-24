package com.portfolioapps.officecreationreservation.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Query(value = "select * from rooms r where r.office_id=?1 order by r.room_name asc", nativeQuery = true)
    List<Room> findAllRoomsByForeignKey(Integer id);
}
