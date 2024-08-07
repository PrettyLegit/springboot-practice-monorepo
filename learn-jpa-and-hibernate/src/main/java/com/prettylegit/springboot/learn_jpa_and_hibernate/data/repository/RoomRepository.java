package com.prettylegit.springboot.learn_jpa_and_hibernate.data.repository;


import com.prettylegit.springboot.learn_jpa_and_hibernate.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
}
