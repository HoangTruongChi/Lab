package com.hust.labregister.repository;


import com.hust.labregister.model.EquipmentDivisionResult;
import com.hust.labregister.model.EquipmentOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDivisionResultRepository extends JpaRepository<EquipmentDivisionResult, Long> {
    @Query(
            value = "select r.equipmentDivisionResults from RoomDivisionResults r where r.roomRegisId=:roomRegisId"
    )
    List<EquipmentDivisionResult> findByRoomId(@Param("roomRegisId") String roomRegisId);

}
