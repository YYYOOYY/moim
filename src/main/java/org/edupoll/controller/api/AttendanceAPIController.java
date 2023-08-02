package org.edupoll.controller.api;

import org.edupoll.model.dto.response.AttendanceJoinResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AttendanceAPIController {

	@Autowired
	AttendanceService attendanceService;

	@PostMapping("/attendance/join")
	public AttendanceJoinResponseData attendanceJoinHandle(@AuthenticationPrincipal Account account, String moimId) {
		
		boolean rst = attendanceService.isJoinedAttendance(moimId, account.getUsername());
		if(rst) {
			AttendanceJoinResponseData result = attendanceService.deleteByAttendance(moimId, account.getUsername());
			
			return result;
		}else {
			AttendanceJoinResponseData result = attendanceService.joinByMoim(moimId, account.getUsername());		
			
			return result;
		}
	}
}
