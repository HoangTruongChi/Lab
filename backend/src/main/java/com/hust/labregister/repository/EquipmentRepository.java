package com.hust.labregister.repository;

import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query(
            value = "select distinct e from Equipment e"
    )
    List<Equipment> findAll();
}
