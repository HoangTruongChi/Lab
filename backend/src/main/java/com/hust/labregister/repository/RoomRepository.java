package com.hust.labregister.repository;


import com.hust.labregister.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(
            value = "select distinct r from Room r left join fetch r.equipmentOfRooms"
    )
    List<Room> findAll();

    @Query(
            value = "select r from Room r where (r.maxPeople - :groupSize) >=" +
                    "(select coalesce(sum(rdr.groupSize),0) from RoomDivisionResults rdr where rdr.status=1 and rdr.date=:date " +
                    "and ((:startTime < rdr.startTime and :endTime > rdr.startTime) or (:startTime < rdr.endTime and :endTime > rdr.endTime) " +
                    "or (:startTime >= rdr.startTime and :endTime <= rdr.endTime))) " +
                    "and r.openTime <= :startTime and r.closeTime >= :endTime and r.roomType=1"
    )
    List<Room> findRoomAvailableType1(@Param("groupSize") Integer groupSize, @Param("date") Date date, @Param("startTime") Double startTime, @Param("endTime") Double endTime);

    @Query(
            value = "SELECT\n" +
                    "        r.*    \n" +
                    "    FROM\n" +
                    "        room r left join\n" +
                    "        (SELECT\n" +
                    "            rdr.room_id,\n" +
                    "            sum(rdr.group_size) as total_people         \n" +
                    "        FROM\n" +
                    "            room_division_results rdr          \n" +
                    "        INNER JOIN\n" +
                    "            room_registration rr                  \n" +
                    "                ON rdr.room_registration_id = rr.id          \n" +
                    "        WHERE\n" +
                    "            1 = 1              \n" +
                    "            AND rdr.status = 1              \n" +
                    "            AND rr.room_type = 2              \n" +
                    "            AND rdr.date = :p_date              \n" +
                    "            AND (\n" +
                    "                (\n" +
                    "                    :p_start_time < rdr.start_time                      \n" +
                    "                    AND :p_end_time > rdr.start_time                 \n" +
                    "                )                  \n" +
                    "                OR (\n" +
                    "                    :p_start_time < rdr.end_time                      \n" +
                    "                    AND :p_end_time > rdr.end_time                 \n" +
                    "                )                  \n" +
                    "                OR (\n" +
                    "                    :p_start_time >= rdr.start_time                      \n" +
                    "                    AND :p_end_time <= rdr.end_time                 \n" +
                    "                )             \n" +
                    "            )          \n" +
                    "        GROUP BY\n" +
                    "            rdr.room_id) x on r.id = x.room_id left join\n" +
                    "        (SELECT\n" +
                    "            rdr.room_id,\n" +
                    "            edr.equipment_id,\n" +
                    "            sum(edr.amount) AS total_equiments          \n" +
                    "        FROM\n" +
                    "            equipment_division_result edr          \n" +
                    "        INNER JOIN\n" +
                    "            room_division_results rdr                  \n" +
                    "                ON edr.room_registration_id = rdr.room_registration_id          \n" +
                    "        WHERE\n" +
                    "            EXISTS (\n" +
                    "                SELECT\n" +
                    "                    1                  \n" +
                    "                FROM\n" +
                    "                    room_division_results rdr                  \n" +
                    "                INNER JOIN\n" +
                    "                    room_registration rr                          \n" +
                    "                        ON rdr.room_registration_id = rr.id                  \n" +
                    "                WHERE\n" +
                    "                    (\n" +
                    "                        1 = 1                          \n" +
                    "                        AND rdr.status = 1                          \n" +
                    "                        AND rr.room_type = 2                          \n" +
                    "                        AND rdr.date = :p_date                          \n" +
                    "                        AND (\n" +
                    "                            (\n" +
                    "                                :p_start_time < rdr.start_time                                  \n" +
                    "                                AND :p_end_time > rdr.start_time                             \n" +
                    "                            )                              \n" +
                    "                            OR (\n" +
                    "                                :p_start_time < rdr.end_time                                  \n" +
                    "                                AND :p_end_time > rdr.end_time                             \n" +
                    "                            )                              \n" +
                    "                            OR (\n" +
                    "                                :p_start_time >= rdr.start_time                                  \n" +
                    "                                AND :p_end_time <= rdr.end_time                             \n" +
                    "                            )                         \n" +
                    "                        )                     \n" +
                    "                    )                      \n" +
                    "                    AND (\n" +
                    "                        edr.room_registration_id = rdr.room_registration_id                     \n" +
                    "                    )             \n" +
                    "            )              \n" +
                    "            AND 1 = 1         \n" +
                    "            and edr.equipment_id = :p_equipment_id\n" +
                    "        GROUP BY\n" +
                    "            rdr.room_id,\n" +
                    "            edr.equipment_id) y on x.room_id = y.room_id         \n" +
                    "        where\n" +
                    "        \tr.room_type = 2     \n" +
                    "            and (\n" +
                    "                r.max_people - :p_group_size\n" +
                    "            ) >= coalesce(x.total_people,0)             \n" +
                    "            and (\n" +
                    "                (\n" +
                    "                    select\n" +
                    "                        eor.amount                 \n" +
                    "                    from\n" +
                    "                        equipment_of_room eor                  \n" +
                    "                    where\n" +
                    "                        eor.room_id = r.id                      \n" +
                    "                        and eor.equipment_id = :p_equipment_id              \n" +
                    "                ) - :p_amount_device          \n" +
                    "            ) >= coalesce(y.total_equiments,0)\n",
            nativeQuery = true
    )
    List<Room> findRoomAvailableType2(@Param("p_group_size") Integer groupSize, @Param("p_date") Date date, @Param("p_start_time") Double startTime,
                                      @Param("p_end_time") Double endTime, @Param("p_equipment_id") Long equipmentId, @Param("p_amount_device") Integer amountDevice);



}