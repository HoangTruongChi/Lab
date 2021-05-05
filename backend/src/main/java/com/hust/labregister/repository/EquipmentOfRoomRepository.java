package com.hust.labregister.repository;


import com.hust.labregister.model.EquipmentOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentOfRoomRepository extends JpaRepository<EquipmentOfRoom, Long> {

    @Query(
            value = "select r.equipmentOfRooms from Room r where r.id=:roomId"
    )
    List<EquipmentOfRoom> findByRoomId(@Param("roomId") Long roomId);

    @Query(
            value = "select distinct e from EquipmentOfRoom e where e.id=:id"
    )
    Optional<EquipmentOfRoom> findById(@Param("id") Long id);


    @Query(
            value = "select distinct e from EquipmentOfRoom e "
    )
    List<EquipmentOfRoom> findAll();

}
