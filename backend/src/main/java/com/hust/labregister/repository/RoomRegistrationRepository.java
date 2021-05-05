package com.hust.labregister.repository;


import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.Room;
import com.hust.labregister.model.RoomRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRegistrationRepository extends JpaRepository<RoomRegistration, Long> {

    @Query(
            value = "select r from RoomRegistration r where r.user.id = :userId and r.status not in (0, 1)"
    )
    List<RoomRegistration> findByStatusAndUserId(@Param("userId") Long userId);

    @Query(
            value = "select distinct r from RoomRegistration r left join fetch r.equipmentRegistration where r.user.id = :userId order by r.createdAt desc"
    )
    List<RoomRegistration> findByUserId(@Param("userId") Long userId);

    @Query(
            value = "select r from RoomRegistration r left join fetch r.equipmentRegistration join fetch r.user where r.status=2 order by (case r.user.role when 'ADMIN' then 0 else 1 end), r.createdAt"
    )
    List<RoomRegistration> findUnHandle();

    @Query(
            value = "select distinct r from RoomRegistration r left join fetch r.equipmentRegistration left join fetch r.user "
    )
    List<RoomRegistration> findAll();

}
