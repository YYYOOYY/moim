package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edupoll.model.dto.response.AttendanceJoinResponseData;
import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MoimRepository moimRepository;

	@Transactional
	public AttendanceJoinResponseData joinByMoim(String moimId, String logonId) {

		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();

		Optional<Moim> moim = moimRepository.findById(moimId);

		Optional<User> user = userRepository.findById(logonId);

		if (user.isEmpty() || moim.isEmpty()) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("유효하지 않은 정보가 전송되었습니다.");
			return ajrd;
		}
		if (attendanceRepository.existsByUserIdIsAndMoimIdIs(moimId, logonId)) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("이미 참가중인 모임입니다.");
			return ajrd;
		}
		if (moim.get().getMaxPerson() != null) {

			if (moim.get().getCurrentPerson() == moim.get().getMaxPerson()
					|| moim.get().getCurrentPerson() > moim.get().getMaxPerson()) {
				ajrd.setResult(false);
				ajrd.setErrorMessage("최대 허용 인원을 초과하였습니다.");
				return ajrd;
			}
		}
		Moim rst = moimRepository.findById(moimId).orElse(null);
		if (rst != null) {
			if (logonId.equals(rst.getManager().getId())) {
				ajrd.setResult(false);
				ajrd.setErrorMessage("모임개설자는 이미 참가중입니다.");
				return ajrd;
			}
		} else {
			ajrd.setResult(false);
			ajrd.setErrorMessage("모임정보가 없습니다.");
			return ajrd;
		}

		Attendance attendance = new Attendance(user.get(), moim.get());

		attendanceRepository.save(attendance);
		moim.get().setCurrentPerson(moim.get().getCurrentPerson() + 1);
		moimRepository.save(moim.get());

		ajrd.setResult(true);
		ajrd.setCurrentPerson(moim.get().getCurrentPerson());
		List<String> list = new ArrayList<>();
		for (Attendance a : attendanceRepository.findByMoimIdIs(moimId)) {
			list.add(a.getUser().getId());
		}
		List<String> alist = attendanceRepository.findByMoimIdIs(moimId).stream().map(t -> t.getUser().getId())
				.toList();
		ajrd.setAttendUserids(alist);

		return ajrd;
	}

	public AttendanceJoinResponseData deleteByAttendance(String moimId, String logonId) {

		Attendance attendance = attendanceRepository.findByMoimIdAndUserId(moimId, logonId);

		Optional<Moim> moim = moimRepository.findById(moimId);

		moim.get().setCurrentPerson(moim.get().getCurrentPerson() - 1);
		moimRepository.save(moim.get());

		attendanceRepository.delete(attendance);

		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();

		ajrd.setResult(true);
		ajrd.setCurrentPerson(moim.get().getCurrentPerson());

		List<String> alist = attendanceRepository.findByMoimIdIs(moimId).stream().map(t -> t.getUser().getId())
				.toList();
		ajrd.setAttendUserids(alist);

		return ajrd;
	}

	public boolean isJoinedAttendance(String moimId, String logonId) {

		Attendance result = attendanceRepository.findByMoimIdAndUserId(moimId, logonId);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}
}
