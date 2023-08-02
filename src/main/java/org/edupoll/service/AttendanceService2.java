package org.edupoll.service;

import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class AttendanceService2 {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MoimRepository moimRepository;
	
	public boolean joinByMoim(String moimId, String logonId) {
		
		Moim moim = moimRepository.findById(moimId).orElse(null);
		
		User user = userRepository.findById(logonId).orElse(null);
		
		
		if(user == null || moim == null) {
			return false;
		}
		Attendance result = attendanceRepository.findByMoimIdAndUserId(moimId, logonId);
		if(result != null) {
			return false;
		}
		if (moim.getCurrentPerson() == moim.getMaxPerson()) {
			return false;
		}
		Moim rst = moimRepository.findById(moimId).orElse(null);
		if(rst != null) {
			if(logonId.equals(rst.getManager().getId())) {
				return false;
			}
		}else {
			return false;
		}
		
		Attendance attendance = new Attendance(user, moim);
		
		attendanceRepository.save(attendance);
		
		moim.setCurrentPerson(moim.getCurrentPerson() + 1);
		moimRepository.save(moim);
		
		return true;
	}
	
	public void deleteByAttendance(String moimId, String logonId) {
		
		Attendance attendance = attendanceRepository.findByMoimIdAndUserId(moimId, logonId);
		
		Moim moim = moimRepository.findById(moimId).orElse(null);
		
		moim.setCurrentPerson(moim.getCurrentPerson() - 1);
		moimRepository.save(moim);
		
		attendanceRepository.delete(attendance);
	}
	
	public boolean findByAttendance(String moimId, String logonId) {
		
		Attendance result = attendanceRepository.findByMoimIdAndUserId(moimId, logonId);
		if(result == null) {
			return false;
		}else {
			return true;
		}
	}
}
