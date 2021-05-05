package com.hust.labregister.repository;


import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.EquipmentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRegistrationRepository extends JpaRepository<EquipmentRegistration, Long> {
    @Query(
            value = "select distinct e from EquipmentRegistration e"
    )
    List<EquipmentRegistration> findAll();
}
