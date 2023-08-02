package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

	Attendance findByMoimIdAndUserId(String moimId, String userId);
	List<Attendance> findByMoimIdIs(String moimId);
	boolean existsByUserIdIsAndMoimIdIs(String moimid, String userId);
}
