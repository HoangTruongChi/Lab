package com.hust.labregister.repository;


import com.hust.labregister.model.Room;
import com.hust.labregister.model.RoomDivisionResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomDivisionResultsRepository extends JpaRepository<RoomDivisionResults, Long> {
    /*@Query(
            value = "select distinct r from RoomDivisionResults r left join fetch RoomRegistration rr on r.roomRegisId = rr.id"
    )
    List findAll();*/

    @Query(
            value = "select distinct rdr from RoomDivisionResults rdr  where rdr.room.id=:roomId"
    )
    List<RoomDivisionResults> findByRoomId(@Param("roomId") Long roomId);

    @Query(
            value = "select distinct rdr from RoomDivisionResults rdr  where rdr.date=:date"
    )
    List<RoomDivisionResults> findByDate(@Param("date") Date date);

    @Query(
            value = "select distinct rdr from RoomDivisionResults rdr  where rdr.room.id=:roomId and rdr.date=:date"
    )
    List<RoomDivisionResults> findByRoomIdAndDate(@Param("roomId") Long roomId,@Param("date") Date date);
}
