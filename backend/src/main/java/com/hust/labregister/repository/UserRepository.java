package com.hust.labregister.repository;

import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(
            value = "select r.user from RoomRegistration  r where r.id=:roomRegisId"
    )
    List<User> findByRoomRegistrationId(@Param("roomRegisId") String roomRegisId);
}
